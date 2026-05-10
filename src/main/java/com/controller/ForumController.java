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

import com.entity.ForumEntity;
import com.entity.view.ForumView;

import com.service.ForumService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 交流论坛
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
@RestController
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ForumEntity forum,
		HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            forum.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        QueryWrapper<ForumEntity> ew = new QueryWrapper<ForumEntity>();



		PageUtils page = forumService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forum), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ForumEntity forum, 
		HttpServletRequest request){
    	if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		forum.setUserid((Long)request.getSession().getAttribute("userId"));
    	}
        QueryWrapper<ForumEntity> ew = new QueryWrapper<ForumEntity>();

		PageUtils page = forumService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forum), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @IgnoreAuth
    @RequestMapping("/flist")
    public R flist(@RequestParam Map<String, Object> params,ForumEntity forum, HttpServletRequest request){
        QueryWrapper<ForumEntity> ew = new QueryWrapper<ForumEntity>();
    	PageUtils page = forumService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forum), params), params));
        return R.ok().put("data", page);
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ForumEntity forum){
        QueryWrapper< ForumEntity> ew = new QueryWrapper< ForumEntity>();
 		ew.allEq(MPUtil.allEQMapPre( forum, "forum")); 
		ForumView forumView =  forumService.selectView(ew);
		return R.ok("查询交流论坛成功").put("data", forumView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ForumEntity forum = forumService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(forum,deSens);
        return R.ok().put("data", forum);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ForumEntity forum = forumService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(forum,deSens);
        return R.ok().put("data", forum);
    }
    
	/**
     * 论坛详情
     */
	@IgnoreAuth
    @RequestMapping("/list/{id}")
    public R list(@PathVariable("id") String id){
        ForumEntity forum = forumService.getById(id);
        getChilds(forum);
        return R.ok().put("data", forum);
    }
    
	private ForumEntity getChilds(ForumEntity forum) {
    	List<ForumEntity> childs = new ArrayList<ForumEntity>();
    	childs = forumService.list(new QueryWrapper<ForumEntity>().eq("parentid", forum.getId()));
    	if(childs == null || childs.size()==0) {
    		return null;
    	}
    	forum.setChilds(childs);
    	for(ForumEntity forumEntity : childs) {
    		getChilds(forumEntity);
    	}
    	return forum;
    }



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ForumEntity forum, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(forum);
    	forum.setUserid((Long)request.getSession().getAttribute("userId"));
        forumService.save(forum);
        return R.ok().put("data",forum.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ForumEntity forum, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(forum);
    	forum.setUserid((Long)request.getSession().getAttribute("userId"));
        forumService.save(forum);
        return R.ok().put("data",forum.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        ForumEntity forum = forumService.getOne(new QueryWrapper<ForumEntity>().eq("", username));
        return R.ok().put("data", forum);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody ForumEntity forum, HttpServletRequest request){
        //ValidatorUtils.validateEntity(forum);
        //全部更新
        forumService.updateById(forum);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        forumService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ForumEntity forum, HttpServletRequest request,String pre){
        QueryWrapper<ForumEntity> ew = new QueryWrapper<ForumEntity>();
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
		PageUtils page = forumService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forum), params), params));
        return R.ok().put("data", page);
    }











}
