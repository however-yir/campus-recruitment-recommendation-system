package com.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class HybridJobRecommenderTest {

    @Test
    void shouldUseHotFallbackForColdStartUser() {
        HybridJobRecommender recommender = new HybridJobRecommender();
        List<HybridJobRecommender.JobFeature> jobs = Arrays.asList(
                new HybridJobRecommender.JobFeature("job-hot").setTitle("运营管培生"),
                new HybridJobRecommender.JobFeature("job-cold").setTitle("测试实习生")
        );
        Map<String, Double> hotScores = new HashMap<>();
        hotScores.put("job-hot", 8.0);
        hotScores.put("job-cold", 1.0);

        List<HybridJobRecommender.RecommendationItem> result = recommender.recommend(
                new HybridJobRecommender.StudentProfile(),
                jobs,
                Collections.<String, Double>emptyMap(),
                hotScores,
                1
        );

        Assertions.assertEquals("job-hot", result.get(0).getItemId());
        Assertions.assertTrue(result.get(0).getReason().contains("热门兜底"));
    }

    @Test
    void shouldReturnEmptyForEmptyJobs() {
        HybridJobRecommender recommender = new HybridJobRecommender();

        List<HybridJobRecommender.RecommendationItem> result = recommender.recommend(
                new HybridJobRecommender.StudentProfile(),
                Collections.<HybridJobRecommender.JobFeature>emptyList(),
                Collections.<String, Double>emptyMap(),
                Collections.<String, Double>emptyMap(),
                5
        );

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void shouldRankByHybridScoreAndExposeExplanationFields() {
        HybridJobRecommender recommender = new HybridJobRecommender();
        HybridJobRecommender.StudentProfile profile = new HybridJobRecommender.StudentProfile()
                .setExpectedPosition("后端")
                .setSkills(set("java", "springboot"))
                .setCity("上海")
                .setEducation("本科");
        List<HybridJobRecommender.JobFeature> jobs = Arrays.asList(
                new HybridJobRecommender.JobFeature("job-match")
                        .setTitle("Java后端开发")
                        .setCategory("后端开发")
                        .setCity("上海")
                        .setEducation("本科")
                        .setSkills(set("java", "springboot")),
                new HybridJobRecommender.JobFeature("job-hot")
                        .setTitle("内容运营")
                        .setCategory("运营")
                        .setCity("北京")
                        .setEducation("本科")
                        .setSkills(set("运营"))
        );
        Map<String, Double> cfScores = new HashMap<>();
        cfScores.put("job-match", 0.6);
        Map<String, Double> hotScores = new HashMap<>();
        hotScores.put("job-match", 1.0);
        hotScores.put("job-hot", 10.0);

        List<HybridJobRecommender.RecommendationItem> result = recommender.recommend(profile, jobs, cfScores, hotScores, 2);

        Assertions.assertEquals("job-match", result.get(0).getItemId());
        Assertions.assertTrue(result.get(0).getReason().contains("相似用户推荐"));
        Assertions.assertTrue(result.get(0).getReason().contains("技能匹配"));
        Assertions.assertTrue(result.get(0).getReason().contains("城市匹配"));
        Assertions.assertTrue(result.get(0).getScoreBreakdown().containsKey("collaborative"));
        Assertions.assertTrue(result.get(0).getScoreBreakdown().containsKey("content"));
        Assertions.assertTrue(result.get(0).getScoreBreakdown().containsKey("hot"));
        Assertions.assertTrue(result.get(0).getScoreBreakdown().containsKey("total"));
    }

    private Set<String> set(String... values) {
        return new LinkedHashSet<>(Arrays.asList(values));
    }
}
