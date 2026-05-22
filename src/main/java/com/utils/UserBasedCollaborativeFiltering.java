package com.utils;

/**
* 类说明 : 基于用户的协同过滤算法
*/

import java.util.*;
import java.util.stream.Collectors;

public class UserBasedCollaborativeFiltering {
	
    private Map<String, Map<String, Double>> userRatings;
    private Map<String, List<String>> itemUsers;
    private Map<String, Integer> userIndex;
    private Map<Integer, String> indexUser;
    private Long[][] sparseMatrix;

    public UserBasedCollaborativeFiltering(Map<String, Map<String, Double>> userRatings) {
        this.userRatings = userRatings == null ? Collections.emptyMap() : userRatings;
        this.itemUsers = new HashMap<>();
        
        this.userIndex = new HashMap<>();//辅助存储每一个用户的用户索引index映射:user->index
        this.indexUser = new HashMap<>();//辅助存储每一个索引index对应的用户映射:index->user

        // 构建物品-用户倒排表
        int keyIndex = 0;
        for (String user : this.userRatings.keySet()) {
            Map<String, Double> ratings = this.userRatings.get(user);
            for (String item : ratings.keySet()) {
                if (!itemUsers.containsKey(item)) {
                    itemUsers.put(item, new ArrayList<>());
                }
                itemUsers.get(item).add(user);
            }
          //用户ID与稀疏矩阵建立对应关系
            this.userIndex.put(user,keyIndex);
            this.indexUser.put(keyIndex,user);
            keyIndex++;
        }
        
        int N = this.userRatings.size();
        this.sparseMatrix=new Long[N][N];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
            	this.sparseMatrix[i][j]=(long)0;
        }
        for(String item : itemUsers.keySet()) {
        	List<String> userList = itemUsers.get(item);
        	for(String u1 : userList) {
        		for(String u2 : userList) {
        			if(u1.equals(u2)){
                        continue;
                    }
        			this.sparseMatrix[this.userIndex.get(u1)][this.userIndex.get(u2)]+=1;
        		}
        	}
        }
        
    }

    public double calculateSimilarity(String user1, String user2) {
        //计算用户之间的相似度【余弦相似性】
        Integer id1 = this.userIndex.get(user1);
        Integer id2 = this.userIndex.get(user2);
        if(id1==null || id2==null) return 0.0;
        return this.sparseMatrix[id1][id2]/Math.sqrt(userRatings.get(indexUser.get(id1)).size()*userRatings.get(indexUser.get(id2)).size());
    }

    public List<String> recommendItems(String targetUser, int numRecommendations) {
        int neighborCount = Math.max(numRecommendations, 1);
        return recommendItems(targetUser, neighborCount, numRecommendations);
    }

    public List<String> recommendItems(String targetUser, int neighborCount, int numRecommendations) {
        if (!userRatings.containsKey(targetUser) || numRecommendations <= 0) {
            return Collections.emptyList();
        }
        return new ArrayList<String>(recommendItemScores(targetUser, neighborCount, numRecommendations).keySet());
    }

    public LinkedHashMap<String, Double> recommendItemScores(String targetUser, int neighborCount, int numRecommendations) {
        if (!userRatings.containsKey(targetUser) || numRecommendations <= 0) {
            return new LinkedHashMap<>();
        }
        Map<String, Double> recommendations = collaborativeScores(targetUser, neighborCount);
        int numItems = Math.min(numRecommendations, recommendations.size());
        return recommendations.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Double>comparingByValue().reversed())).limit(numItems)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Map<String, Double> collaborativeScores(String targetUser, int neighborCount) {
        if (!userRatings.containsKey(targetUser)) {
            return Collections.emptyMap();
        }
        // 计算目标用户与其他用户的相似度
        Map<String, Double> userSimilarities = new HashMap<>();
        for (String user : userRatings.keySet()) {
            if (!user.equals(targetUser)) {
                double similarity = calculateSimilarity(targetUser, user);
                if (similarity > 0) {
                    userSimilarities.put(user, similarity);
                }
            }
        }

        // 根据相似度进行排序
        List<Map.Entry<String, Double>> sortedSimilarities = new ArrayList<>(userSimilarities.entrySet());
        sortedSimilarities.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 选择相似度最高的K个用户
        List<String> similarUsers = new ArrayList<>();
        for (int i = 0; i < neighborCount; i++) {
            if (i < sortedSimilarities.size()) {
                similarUsers.add(sortedSimilarities.get(i).getKey());
            } else {
                break;
            }
        }

        // 获取相似用户喜欢的物品，并进行推荐
        Map<String, Double> recommendations = new HashMap<>();
        for (String user : similarUsers) {
            Map<String, Double> ratings = userRatings.get(user);
            Double similarity = userSimilarities.get(user);
            for (String item : ratings.keySet()) {
                if (userRatings.get(targetUser)!=null && !userRatings.get(targetUser).containsKey(item)) {
                    recommendations.merge(item, ratings.get(item) * similarity, Double::sum);
                }
            }
        }
        return recommendations;
    }

    public RecommendationResult recommendItemsWithHotFallback(
            String targetUser,
            int neighborCount,
            int numRecommendations,
            List<String> hotItems
    ) {
        if (numRecommendations <= 0) {
            return new RecommendationResult(Collections.emptyList(), Collections.emptyMap());
        }

        List<String> cfItems = recommendItems(targetUser, neighborCount, numRecommendations);
        LinkedHashSet<String> merged = new LinkedHashSet<>(cfItems);
        if (hotItems != null) {
            for (String hotItem : hotItems) {
                if (merged.size() >= numRecommendations) {
                    break;
                }
                merged.add(hotItem);
            }
        }

        List<String> finalItems = new ArrayList<>(merged);
        if (finalItems.size() > numRecommendations) {
            finalItems = finalItems.subList(0, numRecommendations);
        }

        Map<String, String> explanations = new LinkedHashMap<>();
        for (String item : finalItems) {
            if (cfItems.contains(item)) {
                explanations.put(item, "由相似用户行为推荐");
            } else {
                explanations.put(item, "热门岗位兜底推荐");
            }
        }
        return new RecommendationResult(finalItems, explanations);
    }

    public RecommendationResult recommendItemsWithHybridFallback(
            String targetUser,
            int neighborCount,
            int numRecommendations,
            List<String> hotItems,
            Map<String, Double> contentScores
    ) {
        if (numRecommendations <= 0) {
            return new RecommendationResult(Collections.emptyList(), Collections.emptyMap());
        }

        Map<String, Double> collaborativeScores = normalizeScores(collaborativeScores(targetUser, neighborCount));
        Map<String, Double> normalizedContentScores = normalizeScores(contentScores);
        Map<String, Double> hotScores = hotFallbackScores(hotItems);

        LinkedHashSet<String> candidates = new LinkedHashSet<>();
        candidates.addAll(collaborativeScores.keySet());
        candidates.addAll(normalizedContentScores.keySet());
        candidates.addAll(hotScores.keySet());

        Map<String, ScoreBreakdown> breakdown = new LinkedHashMap<>();
        for (String item : candidates) {
            double collaborative = collaborativeScores.getOrDefault(item, 0.0);
            double content = normalizedContentScores.getOrDefault(item, 0.0);
            double hot = hotScores.getOrDefault(item, 0.0);
            double finalScore = collaborative * 0.60 + content * 0.30 + hot * 0.10;
            breakdown.put(
                    item,
                    new ScoreBreakdown(
                            collaborative,
                            content,
                            hot,
                            finalScore,
                            explainHybrid(collaborative, content, hot)
                    )
            );
        }

        List<String> finalItems = breakdown.entrySet()
                .stream()
                .sorted((a, b) -> {
                    int scoreCompare = Double.compare(b.getValue().getFinalScore(), a.getValue().getFinalScore());
                    if (scoreCompare != 0) {
                        return scoreCompare;
                    }
                    return a.getKey().compareTo(b.getKey());
                })
                .limit(numRecommendations)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Map<String, String> explanations = new LinkedHashMap<>();
        Map<String, ScoreBreakdown> selectedBreakdown = new LinkedHashMap<>();
        for (String item : finalItems) {
            ScoreBreakdown itemBreakdown = breakdown.get(item);
            explanations.put(item, itemBreakdown.getExplanation());
            selectedBreakdown.put(item, itemBreakdown);
        }

        return new RecommendationResult(finalItems, explanations, selectedBreakdown);
    }

    private Map<String, Double> normalizeScores(Map<String, Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return Collections.emptyMap();
        }
        double max = scores.values().stream().filter(Objects::nonNull).mapToDouble(Double::doubleValue).max().orElse(0.0);
        if (max <= 0) {
            return Collections.emptyMap();
        }
        Map<String, Double> normalized = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : scores.entrySet()) {
            if (entry.getValue() != null && entry.getValue() > 0) {
                normalized.put(entry.getKey(), entry.getValue() / max);
            }
        }
        return normalized;
    }

    private Map<String, Double> hotFallbackScores(List<String> hotItems) {
        if (hotItems == null || hotItems.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Double> scores = new LinkedHashMap<>();
        int size = hotItems.size();
        for (int i = 0; i < hotItems.size(); i++) {
            scores.put(hotItems.get(i), (double) (size - i) / size);
        }
        return scores;
    }

    private String explainHybrid(double collaborative, double content, double hot) {
        if (collaborative >= content && collaborative >= hot && collaborative > 0) {
            return "由相似用户行为推荐";
        }
        if (content >= collaborative && content >= hot && content > 0) {
            return "由岗位内容与用户画像匹配推荐";
        }
        return "热门岗位兜底推荐";
    }

    public static class RecommendationResult {
        private final List<String> items;
        private final Map<String, String> explanations;
        private final Map<String, ScoreBreakdown> scoreBreakdown;

        public RecommendationResult(List<String> items, Map<String, String> explanations) {
            this(items, explanations, Collections.emptyMap());
        }

        public RecommendationResult(List<String> items, Map<String, String> explanations, Map<String, ScoreBreakdown> scoreBreakdown) {
            this.items = items;
            this.explanations = explanations;
            this.scoreBreakdown = scoreBreakdown;
        }

        public List<String> getItems() {
            return items;
        }

        public Map<String, String> getExplanations() {
            return explanations;
        }

        public Map<String, ScoreBreakdown> getScoreBreakdown() {
            return scoreBreakdown;
        }
    }

    public static class ScoreBreakdown {
        private final double collaborativeScore;
        private final double contentScore;
        private final double hotScore;
        private final double finalScore;
        private final String explanation;

        public ScoreBreakdown(double collaborativeScore, double contentScore, double hotScore, double finalScore, String explanation) {
            this.collaborativeScore = collaborativeScore;
            this.contentScore = contentScore;
            this.hotScore = hotScore;
            this.finalScore = finalScore;
            this.explanation = explanation;
        }

        public double getCollaborativeScore() {
            return collaborativeScore;
        }

        public double getContentScore() {
            return contentScore;
        }

        public double getHotScore() {
            return hotScore;
        }

        public double getFinalScore() {
            return finalScore;
        }

        public String getExplanation() {
            return explanation;
        }
    }
    
    
}


