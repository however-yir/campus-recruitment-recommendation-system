package com.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 混合岗位推荐：User-CF 分、内容匹配分、热门兜底分融合排序。
 */
public class HybridJobRecommender {
    private static final double CF_WEIGHT = 0.50;
    private static final double CONTENT_WEIGHT = 0.35;
    private static final double HOT_WEIGHT = 0.15;

    private static final List<String> KNOWN_SKILLS = Arrays.asList(
            "java", "spring", "springboot", "mysql", "redis", "vue", "react", "python",
            "javascript", "typescript", "html", "css", "linux", "docker", "kubernetes",
            "go", "golang", "c++", "算法", "数据分析", "机器学习", "深度学习", "测试",
            "产品", "运营", "设计", "前端", "后端", "全栈"
    );

    private static final List<String> KNOWN_CITIES = Arrays.asList(
            "北京", "上海", "广州", "深圳", "杭州", "南京", "苏州", "成都", "重庆", "武汉",
            "西安", "长沙", "郑州", "天津", "青岛", "厦门", "福州", "合肥", "宁波", "无锡",
            "济南", "大连", "沈阳", "长春", "哈尔滨", "南昌", "昆明", "贵阳", "南宁", "海口"
    );

    private static final List<String> EDUCATION_ORDER = Arrays.asList("高中", "专科", "大专", "本科", "硕士", "博士");

    public List<RecommendationItem> recommend(
            StudentProfile profile,
            List<JobFeature> jobs,
            Map<String, Double> collaborativeScores,
            Map<String, Double> hotScores,
            int limit
    ) {
        if (limit <= 0 || jobs == null || jobs.isEmpty()) {
            return Collections.emptyList();
        }

        StudentProfile safeProfile = profile == null ? new StudentProfile() : profile;
        Set<String> excluded = new HashSet<>();
        excluded.addAll(safeProfile.getFavoriteJobIds());
        excluded.addAll(safeProfile.getAppliedJobIds());

        double maxCf = maxScore(collaborativeScores);
        double maxHot = maxScore(hotScores);
        List<RecommendationItem> items = new ArrayList<>();
        for (JobFeature job : jobs) {
            if (job == null || isBlank(job.getId()) || excluded.contains(job.getId())) {
                continue;
            }
            ContentMatch contentMatch = calculateContentMatch(safeProfile, job);
            double cfScore = normalize(collaborativeScores, job.getId(), maxCf);
            double hotScore = normalize(hotScores, job.getId(), maxHot);
            double total = cfScore * CF_WEIGHT + contentMatch.total * CONTENT_WEIGHT + hotScore * HOT_WEIGHT;

            Map<String, Double> scoreBreakdown = new LinkedHashMap<>();
            scoreBreakdown.put("collaborative", round(cfScore));
            scoreBreakdown.put("content", round(contentMatch.total));
            scoreBreakdown.put("hot", round(hotScore));
            scoreBreakdown.put("skill", round(contentMatch.skill));
            scoreBreakdown.put("city", round(contentMatch.city));
            scoreBreakdown.put("total", round(total));

            items.add(new RecommendationItem(job.getId(), round(total), buildReason(cfScore, hotScore, contentMatch), scoreBreakdown));
        }

        items.sort(new Comparator<RecommendationItem>() {
            @Override
            public int compare(RecommendationItem left, RecommendationItem right) {
                int totalCompare = Double.compare(right.getScore(), left.getScore());
                if (totalCompare != 0) {
                    return totalCompare;
                }
                return left.getItemId().compareTo(right.getItemId());
            }
        });
        if (items.size() > limit) {
            return new ArrayList<>(items.subList(0, limit));
        }
        return items;
    }

    private ContentMatch calculateContentMatch(StudentProfile profile, JobFeature job) {
        ContentMatch match = new ContentMatch();
        match.category = textMatches(profile.getExpectedPosition(), job.getTitle(), job.getCategory(), job.getIndustry()) ? 1.0 : 0.0;
        match.skill = overlapScore(profile.getSkills(), job.getSkills());
        match.city = sameText(profile.getCity(), job.getCity()) ? 1.0 : 0.0;
        match.education = educationMatches(profile.getEducation(), job.getEducation()) ? 1.0 : 0.0;
        match.experience = experienceMatches(profile.getExperience(), job.getExperience()) ? 1.0 : 0.0;
        match.total = round(match.category * 0.30 + match.skill * 0.35 + match.city * 0.20 + match.education * 0.10 + match.experience * 0.05);
        return match;
    }

    private String buildReason(double cfScore, double hotScore, ContentMatch contentMatch) {
        List<String> reasons = new ArrayList<>();
        if (cfScore > 0) {
            reasons.add("相似用户推荐");
        }
        if (contentMatch.skill > 0) {
            reasons.add("技能匹配");
        }
        if (contentMatch.city > 0) {
            reasons.add("城市匹配");
        }
        if (contentMatch.category > 0 && contentMatch.skill <= 0) {
            reasons.add("岗位偏好匹配");
        }
        if (hotScore > 0 || reasons.isEmpty()) {
            reasons.add("热门兜底");
        }
        return join(reasons, "、");
    }

    private static double maxScore(Map<String, Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return 0.0;
        }
        double max = 0.0;
        for (Double value : scores.values()) {
            if (value != null && value > max) {
                max = value;
            }
        }
        return max;
    }

    private static double normalize(Map<String, Double> scores, String id, double max) {
        if (scores == null || max <= 0 || !scores.containsKey(id) || scores.get(id) == null) {
            return 0.0;
        }
        return round(scores.get(id) / max);
    }

    private static boolean textMatches(String expected, String... fields) {
        if (isBlank(expected)) {
            return false;
        }
        String normalizedExpected = normalizeText(expected);
        for (String field : fields) {
            if (isBlank(field)) {
                continue;
            }
            String normalizedField = normalizeText(field);
            if (normalizedField.contains(normalizedExpected) || normalizedExpected.contains(normalizedField)) {
                return true;
            }
        }
        return false;
    }

    private static double overlapScore(Set<String> profileSkills, Set<String> jobSkills) {
        if (profileSkills == null || profileSkills.isEmpty() || jobSkills == null || jobSkills.isEmpty()) {
            return 0.0;
        }
        int hit = 0;
        for (String skill : profileSkills) {
            if (jobSkills.contains(skill)) {
                hit++;
            }
        }
        return round(hit / (double) profileSkills.size());
    }

    private static boolean sameText(String left, String right) {
        if (isBlank(left) || isBlank(right)) {
            return false;
        }
        String a = normalizeText(left);
        String b = normalizeText(right);
        return a.contains(b) || b.contains(a);
    }

    private static boolean educationMatches(String profileEducation, String jobEducation) {
        if (isBlank(profileEducation) || isBlank(jobEducation)) {
            return false;
        }
        if (jobEducation.contains("不限")) {
            return true;
        }
        int profileIndex = educationIndex(profileEducation);
        int jobIndex = educationIndex(jobEducation);
        return profileIndex >= 0 && jobIndex >= 0 && profileIndex >= jobIndex;
    }

    private static boolean experienceMatches(String profileExperience, String jobExperience) {
        if (isBlank(jobExperience)) {
            return false;
        }
        if (jobExperience.contains("不限") || jobExperience.contains("应届") || jobExperience.contains("校招")) {
            return true;
        }
        return !isBlank(profileExperience) && (profileExperience.contains("实习") || profileExperience.contains("项目"));
    }

    private static int educationIndex(String text) {
        for (int i = EDUCATION_ORDER.size() - 1; i >= 0; i--) {
            if (text.contains(EDUCATION_ORDER.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static Set<String> extractSkills(String text) {
        if (isBlank(text)) {
            return Collections.emptySet();
        }
        String lower = text.toLowerCase(Locale.ROOT);
        Set<String> skills = new LinkedHashSet<>();
        for (String skill : KNOWN_SKILLS) {
            if (lower.contains(skill.toLowerCase(Locale.ROOT))) {
                skills.add(skill.toLowerCase(Locale.ROOT));
            }
        }
        return skills;
    }

    public static String extractCity(String text) {
        if (isBlank(text)) {
            return "";
        }
        for (String city : KNOWN_CITIES) {
            if (text.contains(city)) {
                return city;
            }
        }
        return "";
    }

    public static String extractEducation(String text) {
        if (isBlank(text)) {
            return "";
        }
        for (int i = EDUCATION_ORDER.size() - 1; i >= 0; i--) {
            String education = EDUCATION_ORDER.get(i);
            if (text.contains(education)) {
                return education;
            }
        }
        return "";
    }

    public static String extractExperience(String text) {
        if (isBlank(text)) {
            return "";
        }
        if (text.contains("应届")) {
            return "应届";
        }
        if (text.contains("不限")) {
            return "不限";
        }
        if (text.contains("实习")) {
            return "实习";
        }
        if (text.contains("经验")) {
            return "经验";
        }
        return "";
    }

    public static double round(double value) {
        return Math.round(value * 10000.0) / 10000.0;
    }

    private static String normalizeText(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static String join(List<String> values, String separator) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) {
                builder.append(separator);
            }
            builder.append(values.get(i));
        }
        return builder.toString();
    }

    private static class ContentMatch {
        private double category;
        private double skill;
        private double city;
        private double education;
        private double experience;
        private double total;
    }

    public static class StudentProfile {
        private String expectedPosition;
        private Set<String> skills = new LinkedHashSet<>();
        private String city;
        private String education;
        private String experience;
        private Set<String> favoriteJobIds = new LinkedHashSet<>();
        private Set<String> appliedJobIds = new LinkedHashSet<>();

        public String getExpectedPosition() {
            return expectedPosition;
        }

        public StudentProfile setExpectedPosition(String expectedPosition) {
            this.expectedPosition = expectedPosition;
            return this;
        }

        public Set<String> getSkills() {
            return skills;
        }

        public StudentProfile setSkills(Set<String> skills) {
            this.skills = skills == null ? new LinkedHashSet<String>() : skills;
            return this;
        }

        public String getCity() {
            return city;
        }

        public StudentProfile setCity(String city) {
            this.city = city;
            return this;
        }

        public String getEducation() {
            return education;
        }

        public StudentProfile setEducation(String education) {
            this.education = education;
            return this;
        }

        public String getExperience() {
            return experience;
        }

        public StudentProfile setExperience(String experience) {
            this.experience = experience;
            return this;
        }

        public Set<String> getFavoriteJobIds() {
            return favoriteJobIds;
        }

        public StudentProfile setFavoriteJobIds(Set<String> favoriteJobIds) {
            this.favoriteJobIds = favoriteJobIds == null ? new LinkedHashSet<String>() : favoriteJobIds;
            return this;
        }

        public Set<String> getAppliedJobIds() {
            return appliedJobIds;
        }

        public StudentProfile setAppliedJobIds(Set<String> appliedJobIds) {
            this.appliedJobIds = appliedJobIds == null ? new LinkedHashSet<String>() : appliedJobIds;
            return this;
        }
    }

    public static class JobFeature {
        private String id;
        private String title;
        private String category;
        private String city;
        private String salary;
        private String education;
        private Set<String> skills = new LinkedHashSet<>();
        private String experience;
        private String industry;

        public JobFeature(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public JobFeature setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getCategory() {
            return category;
        }

        public JobFeature setCategory(String category) {
            this.category = category;
            return this;
        }

        public String getCity() {
            return city;
        }

        public JobFeature setCity(String city) {
            this.city = city;
            return this;
        }

        public String getSalary() {
            return salary;
        }

        public JobFeature setSalary(String salary) {
            this.salary = salary;
            return this;
        }

        public String getEducation() {
            return education;
        }

        public JobFeature setEducation(String education) {
            this.education = education;
            return this;
        }

        public Set<String> getSkills() {
            return skills;
        }

        public JobFeature setSkills(Set<String> skills) {
            this.skills = skills == null ? new LinkedHashSet<String>() : skills;
            return this;
        }

        public String getExperience() {
            return experience;
        }

        public JobFeature setExperience(String experience) {
            this.experience = experience;
            return this;
        }

        public String getIndustry() {
            return industry;
        }

        public JobFeature setIndustry(String industry) {
            this.industry = industry;
            return this;
        }
    }

    public static class RecommendationItem {
        private final String itemId;
        private final double score;
        private final String reason;
        private final Map<String, Double> scoreBreakdown;

        public RecommendationItem(String itemId, double score, String reason, Map<String, Double> scoreBreakdown) {
            this.itemId = itemId;
            this.score = score;
            this.reason = reason;
            this.scoreBreakdown = new LinkedHashMap<>(scoreBreakdown);
        }

        public String getItemId() {
            return itemId;
        }

        public double getScore() {
            return score;
        }

        public String getReason() {
            return reason;
        }

        public Map<String, Double> getScoreBreakdown() {
            return scoreBreakdown;
        }
    }
}
