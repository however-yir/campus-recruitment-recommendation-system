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

import com.entity.DiscusszhaopinxinxiEntity;
import com.entity.view.DiscusszhaopinxinxiView;

import com.service.DiscusszhaopinxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 招聘信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-07 12:22:19
 */
@RestController
@RequestMapping("/discusszhaopinxinxi")
public class DiscusszhaopinxinxiController {
    @Autowired
    private DiscusszhaopinxinxiService discusszhaopinxinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusszhaopinxinxiEntity discusszhaopinxinxi,
		HttpServletRequest request){
        QueryWrapper<DiscusszhaopinxinxiEntity> ew = new QueryWrapper<DiscusszhaopinxinxiEntity>();



		PageUtils page = discusszhaopinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusszhaopinxinxi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusszhaopinxinxiEntity discusszhaopinxinxi, 
		HttpServletRequest request){
        QueryWrapper<DiscusszhaopinxinxiEntity> ew = new QueryWrapper<DiscusszhaopinxinxiEntity>();

		PageUtils page = discusszhaopinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusszhaopinxinxi), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusszhaopinxinxiEntity discusszhaopinxinxi){
       	QueryWrapper<DiscusszhaopinxinxiEntity> ew = new QueryWrapper<DiscusszhaopinxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusszhaopinxinxi, "discusszhaopinxinxi")); 
        return R.ok().put("data", discusszhaopinxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusszhaopinxinxiEntity discusszhaopinxinxi){
        QueryWrapper< DiscusszhaopinxinxiEntity> ew = new QueryWrapper< DiscusszhaopinxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusszhaopinxinxi, "discusszhaopinxinxi")); 
		DiscusszhaopinxinxiView discusszhaopinxinxiView =  discusszhaopinxinxiService.selectView(ew);
		return R.ok("查询招聘信息评论表成功").put("data", discusszhaopinxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusszhaopinxinxiEntity discusszhaopinxinxi = discusszhaopinxinxiService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(discusszhaopinxinxi,deSens);
        return R.ok().put("data", discusszhaopinxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusszhaopinxinxiEntity discusszhaopinxinxi = discusszhaopinxinxiService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(discusszhaopinxinxi,deSens);
        return R.ok().put("data", discusszhaopinxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscusszhaopinxinxiEntity discusszhaopinxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusszhaopinxinxi);
        discusszhaopinxinxiService.save(discusszhaopinxinxi);
        return R.ok().put("data",discusszhaopinxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscusszhaopinxinxiEntity discusszhaopinxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusszhaopinxinxi);
        discusszhaopinxinxiService.save(discusszhaopinxinxi);
        return R.ok().put("data",discusszhaopinxinxi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscusszhaopinxinxiEntity discusszhaopinxinxi = discusszhaopinxinxiService.getOne(new QueryWrapper<DiscusszhaopinxinxiEntity>().eq("", username));
        return R.ok().put("data", discusszhaopinxinxi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscusszhaopinxinxiEntity discusszhaopinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusszhaopinxinxi);
        //全部更新
        discusszhaopinxinxiService.updateById(discusszhaopinxinxi);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discusszhaopinxinxiService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscusszhaopinxinxiEntity discusszhaopinxinxi, HttpServletRequest request,String pre){
        QueryWrapper<DiscusszhaopinxinxiEntity> ew = new QueryWrapper<DiscusszhaopinxinxiEntity>();
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
		PageUtils page = discusszhaopinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusszhaopinxinxi), params), params));
        return R.ok().put("data", page);
    }











}
