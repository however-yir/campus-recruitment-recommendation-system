package com.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
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
}
