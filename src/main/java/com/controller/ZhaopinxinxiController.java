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
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
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
        EntityWrapper<ZhaopinxinxiEntity> ew = new EntityWrapper<ZhaopinxinxiEntity>();



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
        EntityWrapper<ZhaopinxinxiEntity> ew = new EntityWrapper<ZhaopinxinxiEntity>();

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
       	EntityWrapper<ZhaopinxinxiEntity> ew = new EntityWrapper<ZhaopinxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhaopinxinxi, "zhaopinxinxi")); 
        return R.ok().put("data", zhaopinxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhaopinxinxiEntity zhaopinxinxi){
        EntityWrapper< ZhaopinxinxiEntity> ew = new EntityWrapper< ZhaopinxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhaopinxinxi, "zhaopinxinxi")); 
		ZhaopinxinxiView zhaopinxinxiView =  zhaopinxinxiService.selectView(ew);
		return R.ok("查询招聘信息成功").put("data", zhaopinxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhaopinxinxiEntity zhaopinxinxi = zhaopinxinxiService.selectById(id);
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
        ZhaopinxinxiEntity zhaopinxinxi = zhaopinxinxiService.selectById(id);
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
        zhaopinxinxiService.insert(zhaopinxinxi);
        return R.ok().put("data",zhaopinxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhaopinxinxiEntity zhaopinxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhaopinxinxi);
        zhaopinxinxiService.insert(zhaopinxinxi);
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
            ZhaopinxinxiEntity zhaopinxinxi = zhaopinxinxiService.selectById(id);
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
        zhaopinxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ZhaopinxinxiEntity zhaopinxinxi, HttpServletRequest request,String pre){
        EntityWrapper<ZhaopinxinxiEntity> ew = new EntityWrapper<ZhaopinxinxiEntity>();
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
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        Integer neighborLimit = params.get("neighborLimit")==null?Math.max(limit * 3, 20):Integer.parseInt(params.get("neighborLimit").toString());
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("tablename", "zhaopinxinxi"));
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
        // 创建协同过滤对象
        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering(ratings);

        // 为指定用户推荐物品
        String targetUser = userId;
        List<String> recommendations = filter.recommendItems(targetUser, neighborLimit, limit);

        List<ZhaopinxinxiEntity> pageList = new ArrayList<ZhaopinxinxiEntity>();
        Set<String> excludedIds = new LinkedHashSet<>(recommendations);
        if(recommendations!=null && recommendations.size()>0) {
            EntityWrapper<ZhaopinxinxiEntity> ew = new EntityWrapper<ZhaopinxinxiEntity>();
            ew.in("id", recommendations);
            ew.last("order by FIELD(id, "+String.join(",", recommendations)+")");
            pageList.addAll(zhaopinxinxiService.selectList(ew));
        }
        if(pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            EntityWrapper<ZhaopinxinxiEntity> hotWrapper = new EntityWrapper<ZhaopinxinxiEntity>();
            if(!excludedIds.isEmpty()) {
                hotWrapper.notIn("id", excludedIds);
            }
            hotWrapper.eq("sfsh", "是");
            hotWrapper.orderBy("storeupnum", false);
            hotWrapper.orderBy("clicktime", false);
            hotWrapper.orderBy("id", false);
            hotWrapper.last("limit "+toAddNum);
            pageList.addAll(zhaopinxinxiService.selectList(hotWrapper));
        }
        if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        int currPage = params.get("page")==null?1:Integer.parseInt(params.get("page").toString());
        PageUtils page = new PageUtils(pageList, pageList.size(), limit, currPage);

        return R.ok().put("data", page);
    }









}
