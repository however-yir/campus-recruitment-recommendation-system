package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.annotation.IgnoreAuth;
import com.entity.QiuzhijianliEntity;
import com.entity.QiuzhixinxiEntity;
import com.entity.QiyexinxiEntity;
import com.service.QiuzhijianliService;
import com.service.QiuzhixinxiService;
import com.service.QiyexinxiService;
import com.utils.HybridJobRecommender;
import com.utils.UserBasedCollaborativeFiltering;

import com.entity.ZhaopinxinxiEntity;
import com.entity.view.ZhaopinxinxiView;

import com.service.ZhaopinxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 招聘信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
@RestController
@RequestMapping("/zhaopinxinxi")
public class ZhaopinxinxiController {
    @Autowired
    private ZhaopinxinxiService zhaopinxinxiService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private QiyexinxiService qiyexinxiService;

    @Autowired
    private QiuzhijianliService qiuzhijianliService;

    @Autowired
    private QiuzhixinxiService qiuzhixinxiService;


    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhaopinxinxiEntity zhaopinxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("qiyexinxi")) {
			zhaopinxinxi.setQiyezhanghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<ZhaopinxinxiEntity> ew = new QueryWrapper<ZhaopinxinxiEntity>();



		PageUtils page = zhaopinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhaopinxinxi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhaopinxinxiEntity zhaopinxinxi, 
		HttpServletRequest request){
        QueryWrapper<ZhaopinxinxiEntity> ew = new QueryWrapper<ZhaopinxinxiEntity>();

		PageUtils page = zhaopinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhaopinxinxi), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhaopinxinxiEntity zhaopinxinxi){
       	QueryWrapper<ZhaopinxinxiEntity> ew = new QueryWrapper<ZhaopinxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhaopinxinxi, "zhaopinxinxi")); 
        return R.ok().put("data", zhaopinxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhaopinxinxiEntity zhaopinxinxi){
        QueryWrapper< ZhaopinxinxiEntity> ew = new QueryWrapper< ZhaopinxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhaopinxinxi, "zhaopinxinxi")); 
		ZhaopinxinxiView zhaopinxinxiView =  zhaopinxinxiService.selectView(ew);
		return R.ok("查询招聘信息成功").put("data", zhaopinxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhaopinxinxiEntity zhaopinxinxi = zhaopinxinxiService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(zhaopinxinxi,deSens);
        return R.ok().put("data", zhaopinxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhaopinxinxiEntity zhaopinxinxi = zhaopinxinxiService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(zhaopinxinxi,deSens);
        return R.ok().put("data", zhaopinxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhaopinxinxiEntity zhaopinxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhaopinxinxi);
        zhaopinxinxiService.save(zhaopinxinxi);
        return R.ok().put("data",zhaopinxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhaopinxinxiEntity zhaopinxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhaopinxinxi);
        zhaopinxinxiService.save(zhaopinxinxi);
        return R.ok().put("data",zhaopinxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhaopinxinxiEntity zhaopinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhaopinxinxi);
        //全部更新
        zhaopinxinxiService.updateById(zhaopinxinxi);

        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZhaopinxinxiEntity> list = new ArrayList<ZhaopinxinxiEntity>();
        for(Long id : ids) {
            ZhaopinxinxiEntity zhaopinxinxi = zhaopinxinxiService.getById(id);
            zhaopinxinxi.setSfsh(sfsh);
            zhaopinxinxi.setShhf(shhf);
            list.add(zhaopinxinxi);
        }
        zhaopinxinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhaopinxinxiService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ZhaopinxinxiEntity zhaopinxinxi, HttpServletRequest request,String pre){
        QueryWrapper<ZhaopinxinxiEntity> ew = new QueryWrapper<ZhaopinxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = zhaopinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhaopinxinxi), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（基于用户收藏的协同算法）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,ZhaopinxinxiEntity zhaopinxinxi, HttpServletRequest request){
        Object userIdObj = request.getSession().getAttribute("userId");
        if(userIdObj==null) {
            return autoSort(params, zhaopinxinxi, request, "");
        }
        String userId = userIdObj.toString();
        String username = request.getSession().getAttribute("username")==null?"":request.getSession().getAttribute("username").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        Integer neighborLimit = params.get("neighborLimit")==null?Math.max(limit * 3, 20):Integer.parseInt(params.get("neighborLimit").toString());
        List<StoreupEntity> storeups = storeupService.list(new QueryWrapper<StoreupEntity>().eq("type", 1).eq("tablename", "zhaopinxinxi"));
        Map<String, Map<String, Double>> ratings = new HashMap<>();
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity storeup : storeups) {
                Map<String, Double> userRatings = null;
                if(ratings.containsKey(storeup.getUserid().toString())) {
                    userRatings = ratings.get(storeup.getUserid().toString());
                } else {
                    userRatings = new HashMap<>();
                    ratings.put(storeup.getUserid().toString(), userRatings);
                }

                if(userRatings.containsKey(storeup.getRefid().toString())) {
                    userRatings.put(storeup.getRefid().toString(), userRatings.get(storeup.getRefid().toString())+1.0);
                } else {
                    userRatings.put(storeup.getRefid().toString(), 1.0);
                }
            }
        }

        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering(ratings);
        Map<String, Double> collaborativeScores = filter.recommendItemScores(userId, neighborLimit, Math.max(limit * 5, 50));

        QueryWrapper<ZhaopinxinxiEntity> candidateWrapper = new QueryWrapper<ZhaopinxinxiEntity>();
        candidateWrapper.eq("sfsh", "是");
        addLikeIfPresent(candidateWrapper, "qiyemingcheng", zhaopinxinxi.getQiyemingcheng());
        addLikeIfPresent(candidateWrapper, "zhiweimingcheng", zhaopinxinxi.getZhiweimingcheng());
        addLikeIfPresent(candidateWrapper, "xinzidaiyu", zhaopinxinxi.getXinzidaiyu());
        addEqIfPresent(candidateWrapper, "zhiweileixing", zhaopinxinxi.getZhiweileixing());
        candidateWrapper.orderBy(true, false, "storeupnum");
        candidateWrapper.orderBy(true, false, "clicktime");
        candidateWrapper.orderBy(true, false, "id");
        List<ZhaopinxinxiEntity> candidates = zhaopinxinxiService.list(candidateWrapper);
        if(candidates==null) {
            candidates = Collections.emptyList();
        }

        Map<String, QiyexinxiEntity> companies = loadCompanies(candidates);
        HybridJobRecommender.StudentProfile studentProfile = buildStudentProfile(userId, username, storeups, candidates);
        List<HybridJobRecommender.JobFeature> jobFeatures = buildJobFeatures(candidates, companies);
        Map<String, Double> hotScores = buildHotScores(candidates);

        HybridJobRecommender recommender = new HybridJobRecommender();
        List<HybridJobRecommender.RecommendationItem> recommendationItems =
                recommender.recommend(studentProfile, jobFeatures, collaborativeScores, hotScores, limit);

        Map<String, ZhaopinxinxiEntity> candidateMap = new HashMap<>();
        for(ZhaopinxinxiEntity candidate : candidates) {
            if(candidate.getId()!=null) {
                candidateMap.put(candidate.getId().toString(), candidate);
            }
        }
        List<ZhaopinxinxiEntity> pageList = new ArrayList<ZhaopinxinxiEntity>();
        for(HybridJobRecommender.RecommendationItem item : recommendationItems) {
            ZhaopinxinxiEntity entity = candidateMap.get(item.getItemId());
            if(entity!=null) {
                entity.setReason(item.getReason());
                entity.setScoreBreakdown(item.getScoreBreakdown());
                pageList.add(entity);
            }
        }
        int currPage = params.get("page")==null?1:Integer.parseInt(params.get("page").toString());
        PageUtils page = new PageUtils(pageList, pageList.size(), limit, currPage);

        return R.ok().put("data", page);
    }

    private void addLikeIfPresent(QueryWrapper<ZhaopinxinxiEntity> wrapper, String column, String value) {
        if(StringUtils.isNotBlank(value)) {
            wrapper.like(column, value.replace("%", ""));
        }
    }

    private void addEqIfPresent(QueryWrapper<ZhaopinxinxiEntity> wrapper, String column, String value) {
        if(StringUtils.isNotBlank(value)) {
            wrapper.eq(column, value.replace("%", ""));
        }
    }

    private Map<String, QiyexinxiEntity> loadCompanies(List<ZhaopinxinxiEntity> jobs) {
        Map<String, QiyexinxiEntity> result = new HashMap<>();
        Set<String> accounts = new LinkedHashSet<>();
        for(ZhaopinxinxiEntity job : jobs) {
            if(StringUtils.isNotBlank(job.getQiyezhanghao())) {
                accounts.add(job.getQiyezhanghao());
            }
        }
        if(accounts.isEmpty()) {
            return result;
        }
        List<QiyexinxiEntity> companies = qiyexinxiService.list(new QueryWrapper<QiyexinxiEntity>().in("qiyezhanghao", accounts));
        if(companies!=null) {
            for(QiyexinxiEntity company : companies) {
                result.put(company.getQiyezhanghao(), company);
            }
        }
        return result;
    }

    private HybridJobRecommender.StudentProfile buildStudentProfile(
            String userId,
            String username,
            List<StoreupEntity> storeups,
            List<ZhaopinxinxiEntity> candidates
    ) {
        Set<String> favoriteIds = new LinkedHashSet<>();
        if(storeups!=null) {
            for(StoreupEntity storeup : storeups) {
                if(storeup.getUserid()!=null && storeup.getRefid()!=null && userId.equals(storeup.getUserid().toString())) {
                    favoriteIds.add(storeup.getRefid().toString());
                }
            }
        }

        String resumeText = "";
        String expectedPosition = "";
        if(StringUtils.isNotBlank(username)) {
            List<QiuzhijianliEntity> resumes = qiuzhijianliService.list(
                    new QueryWrapper<QiuzhijianliEntity>().eq("yonghuzhanghao", username).orderBy(true, false, "addtime")
            );
            if(resumes!=null && !resumes.isEmpty()) {
                QiuzhijianliEntity resume = resumes.get(0);
                expectedPosition = resume.getQiuzhiyixiang();
                resumeText = joinText(
                        resume.getQiuzhiyixiang(),
                        resume.getJiaoyujingli(),
                        resume.getPeixunjingli(),
                        resume.getJinglishijian(),
                        resume.getYuyannengli(),
                        resume.getZiwopingjia(),
                        resume.getHuodezhengshu()
                );
            }
        }
        if(StringUtils.isBlank(expectedPosition)) {
            expectedPosition = favoriteCategory(favoriteIds, candidates);
        }

        Set<String> appliedIds = appliedJobIds(username, candidates);
        return new HybridJobRecommender.StudentProfile()
                .setExpectedPosition(expectedPosition)
                .setSkills(HybridJobRecommender.extractSkills(resumeText))
                .setCity(HybridJobRecommender.extractCity(resumeText))
                .setEducation(HybridJobRecommender.extractEducation(resumeText))
                .setExperience(HybridJobRecommender.extractExperience(resumeText))
                .setFavoriteJobIds(favoriteIds)
                .setAppliedJobIds(appliedIds);
    }

    private Set<String> appliedJobIds(String username, List<ZhaopinxinxiEntity> candidates) {
        Set<String> appliedIds = new LinkedHashSet<>();
        if(StringUtils.isBlank(username)) {
            return appliedIds;
        }
        List<QiuzhixinxiEntity> applications = qiuzhixinxiService.list(new QueryWrapper<QiuzhixinxiEntity>().eq("yonghuzhanghao", username));
        if(applications==null || applications.isEmpty()) {
            return appliedIds;
        }
        Set<String> companyAccounts = new LinkedHashSet<>();
        Set<String> covers = new LinkedHashSet<>();
        for(QiuzhixinxiEntity application : applications) {
            if(StringUtils.isNotBlank(application.getQiyezhanghao())) {
                companyAccounts.add(application.getQiyezhanghao());
            }
            if(StringUtils.isNotBlank(application.getZhaopinfengmian())) {
                covers.add(application.getZhaopinfengmian());
            }
        }
        for(ZhaopinxinxiEntity job : candidates) {
            if(job.getId()==null) {
                continue;
            }
            boolean sameCompany = StringUtils.isNotBlank(job.getQiyezhanghao()) && companyAccounts.contains(job.getQiyezhanghao());
            boolean sameCover = StringUtils.isNotBlank(job.getZhaopinfengmian()) && covers.contains(job.getZhaopinfengmian());
            if(sameCompany || sameCover) {
                appliedIds.add(job.getId().toString());
            }
        }
        return appliedIds;
    }

    private String favoriteCategory(Set<String> favoriteIds, List<ZhaopinxinxiEntity> candidates) {
        Map<String, Integer> categoryCounts = new HashMap<>();
        for(ZhaopinxinxiEntity candidate : candidates) {
            if(candidate.getId()!=null && favoriteIds.contains(candidate.getId().toString()) && StringUtils.isNotBlank(candidate.getZhiweileixing())) {
                categoryCounts.put(candidate.getZhiweileixing(), categoryCounts.getOrDefault(candidate.getZhiweileixing(), 0) + 1);
            }
        }
        String bestCategory = "";
        int bestCount = 0;
        for(Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            if(entry.getValue() > bestCount) {
                bestCategory = entry.getKey();
                bestCount = entry.getValue();
            }
        }
        return bestCategory;
    }

    private List<HybridJobRecommender.JobFeature> buildJobFeatures(List<ZhaopinxinxiEntity> jobs, Map<String, QiyexinxiEntity> companies) {
        List<HybridJobRecommender.JobFeature> features = new ArrayList<>();
        for(ZhaopinxinxiEntity job : jobs) {
            if(job.getId()==null) {
                continue;
            }
            QiyexinxiEntity company = companies.get(job.getQiyezhanghao());
            String companyText = company==null ? "" : joinText(company.getQiyedizhi(), company.getQiyejianjie());
            String jobText = joinText(
                    job.getZhiweimingcheng(),
                    job.getZhiweileixing(),
                    job.getSuoshuxingye(),
                    job.getXinzidaiyu(),
                    companyText
            );
            features.add(new HybridJobRecommender.JobFeature(job.getId().toString())
                    .setTitle(job.getZhiweimingcheng())
                    .setCategory(job.getZhiweileixing())
                    .setCity(HybridJobRecommender.extractCity(companyText))
                    .setSalary(job.getXinzidaiyu())
                    .setEducation(HybridJobRecommender.extractEducation(jobText))
                    .setSkills(HybridJobRecommender.extractSkills(jobText))
                    .setExperience(HybridJobRecommender.extractExperience(jobText))
                    .setIndustry(job.getSuoshuxingye()));
        }
        return features;
    }

    private Map<String, Double> buildHotScores(List<ZhaopinxinxiEntity> jobs) {
        Map<String, Double> hotScores = new HashMap<>();
        for(int i=0; i<jobs.size(); i++) {
            ZhaopinxinxiEntity job = jobs.get(i);
            if(job.getId()==null) {
                continue;
            }
            double storeupScore = job.getStoreupnum()==null ? 0.0 : job.getStoreupnum().doubleValue();
            double rankScore = (jobs.size() - i) / 10000.0;
            hotScores.put(job.getId().toString(), storeupScore + rankScore);
        }
        return hotScores;
    }

    private String joinText(String... values) {
        StringBuilder builder = new StringBuilder();
        for(String value : values) {
            if(StringUtils.isNotBlank(value)) {
                if(builder.length()>0) {
                    builder.append(' ');
                }
                builder.append(value);
            }
        }
        return builder.toString();
    }









}
