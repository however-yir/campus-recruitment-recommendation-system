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
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ForumtypeEntity;
import com.entity.view.ForumtypeView;

import com.service.ForumtypeService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 交流论坛类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
@RestController
@RequestMapping("/forumtype")
public class ForumtypeController {
    @Autowired
    private ForumtypeService forumtypeService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ForumtypeEntity forumtype,
		HttpServletRequest request){
        QueryWrapper<ForumtypeEntity> ew = new QueryWrapper<ForumtypeEntity>();



		PageUtils page = forumtypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forumtype), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ForumtypeEntity forumtype, 
		HttpServletRequest request){
        QueryWrapper<ForumtypeEntity> ew = new QueryWrapper<ForumtypeEntity>();

		PageUtils page = forumtypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forumtype), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ForumtypeEntity forumtype){
       	QueryWrapper<ForumtypeEntity> ew = new QueryWrapper<ForumtypeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( forumtype, "forumtype")); 
        return R.ok().put("data", forumtypeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ForumtypeEntity forumtype){
        QueryWrapper< ForumtypeEntity> ew = new QueryWrapper< ForumtypeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( forumtype, "forumtype")); 
		ForumtypeView forumtypeView =  forumtypeService.selectView(ew);
		return R.ok("查询交流论坛类型成功").put("data", forumtypeView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ForumtypeEntity forumtype = forumtypeService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(forumtype,deSens);
        return R.ok().put("data", forumtype);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ForumtypeEntity forumtype = forumtypeService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(forumtype,deSens);
        return R.ok().put("data", forumtype);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ForumtypeEntity forumtype, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(forumtype);
        forumtypeService.save(forumtype);
        return R.ok().put("data",forumtype.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ForumtypeEntity forumtype, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(forumtype);
        forumtypeService.save(forumtype);
        return R.ok().put("data",forumtype.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        ForumtypeEntity forumtype = forumtypeService.getOne(new QueryWrapper<ForumtypeEntity>().eq("", username));
        return R.ok().put("data", forumtype);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody ForumtypeEntity forumtype, HttpServletRequest request){
        //ValidatorUtils.validateEntity(forumtype);
        //全部更新
        forumtypeService.updateById(forumtype);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        forumtypeService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ForumtypeEntity forumtype, HttpServletRequest request,String pre){
        QueryWrapper<ForumtypeEntity> ew = new QueryWrapper<ForumtypeEntity>();
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
		PageUtils page = forumtypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forumtype), params), params));
        return R.ok().put("data", page);
    }











}
