package com.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class UserBasedCollaborativeFilteringTest {

    @Test
    void shouldAppendHotFallbackWhenCfResultInsufficient() {
        Map<String, Map<String, Double>> userRatings = new HashMap<>();
        Map<String, Double> userA = new HashMap<>();
        userA.put("job-1", 5.0);
        userRatings.put("u1", userA);

        Map<String, Double> userB = new HashMap<>();
        userB.put("job-1", 5.0);
        userB.put("job-2", 4.0);
        userRatings.put("u2", userB);

        UserBasedCollaborativeFiltering filtering = new UserBasedCollaborativeFiltering(userRatings);
        UserBasedCollaborativeFiltering.RecommendationResult result =
                filtering.recommendItemsWithHotFallback("u1", 2, 3, Arrays.asList("job-3", "job-4"));

        Assertions.assertEquals(3, result.getItems().size());
        Assertions.assertEquals("由相似用户行为推荐", result.getExplanations().get("job-2"));
        Assertions.assertEquals("热门岗位兜底推荐", result.getExplanations().get("job-3"));
    }

    @Test
    void shouldReturnEmptyWhenRecommendationCountNotPositive() {
        Map<String, Map<String, Double>> userRatings = new HashMap<>();
        UserBasedCollaborativeFiltering filtering = new UserBasedCollaborativeFiltering(userRatings);

        UserBasedCollaborativeFiltering.RecommendationResult result =
                filtering.recommendItemsWithHotFallback("u1", 1, 0, Arrays.asList("job-1"));
        Assertions.assertTrue(result.getItems().isEmpty());
        Assertions.assertTrue(result.getExplanations().isEmpty());
    }

    @Test
    void shouldExposeCollaborativeScoresForHybridRanking() {
        Map<String, Map<String, Double>> userRatings = new HashMap<>();
        Map<String, Double> targetUser = new HashMap<>();
        targetUser.put("job-1", 1.0);
        userRatings.put("u1", targetUser);

        Map<String, Double> similarUser = new HashMap<>();
        similarUser.put("job-1", 1.0);
        similarUser.put("job-2", 3.0);
        userRatings.put("u2", similarUser);

        UserBasedCollaborativeFiltering filtering = new UserBasedCollaborativeFiltering(userRatings);
        LinkedHashMap<String, Double> scores = filtering.recommendItemScores("u1", 2, 2);

        Assertions.assertTrue(scores.containsKey("job-2"));
        Assertions.assertTrue(scores.get("job-2") > 0);
    }

    @Test
    void shouldBlendCollaborativeContentAndHotSignals() {
        Map<String, Map<String, Double>> userRatings = new HashMap<>();
        Map<String, Double> userA = new HashMap<>();
        userA.put("job-1", 5.0);
        userRatings.put("u1", userA);

        Map<String, Double> userB = new HashMap<>();
        userB.put("job-1", 5.0);
        userB.put("job-2", 4.0);
        userRatings.put("u2", userB);

        Map<String, Double> contentScores = new LinkedHashMap<>();
        contentScores.put("job-3", 0.95);
        contentScores.put("job-4", 0.30);

        UserBasedCollaborativeFiltering filtering = new UserBasedCollaborativeFiltering(userRatings);
        UserBasedCollaborativeFiltering.RecommendationResult result =
                filtering.recommendItemsWithHybridFallback(
                        "u1",
                        2,
                        3,
                        Arrays.asList("job-5", "job-6"),
                        contentScores
                );

        Assertions.assertEquals(Arrays.asList("job-2", "job-3", "job-5"), result.getItems());
        Assertions.assertEquals("由相似用户行为推荐", result.getExplanations().get("job-2"));
        Assertions.assertEquals("由岗位内容与用户画像匹配推荐", result.getExplanations().get("job-3"));
        Assertions.assertEquals("热门岗位兜底推荐", result.getExplanations().get("job-5"));
        Assertions.assertTrue(result.getScoreBreakdown().get("job-3").getContentScore() > 0);
    }
}
