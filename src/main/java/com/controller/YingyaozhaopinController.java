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

import com.entity.YingyaozhaopinEntity;
import com.entity.view.YingyaozhaopinView;

import com.service.YingyaozhaopinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 应邀招聘
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
@RestController
@RequestMapping("/yingyaozhaopin")
public class YingyaozhaopinController {
    @Autowired
    private YingyaozhaopinService yingyaozhaopinService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YingyaozhaopinEntity yingyaozhaopin,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			yingyaozhaopin.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("qiyexinxi")) {
			yingyaozhaopin.setQiyezhanghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<YingyaozhaopinEntity> ew = new QueryWrapper<YingyaozhaopinEntity>();



		PageUtils page = yingyaozhaopinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yingyaozhaopin), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YingyaozhaopinEntity yingyaozhaopin, 
		HttpServletRequest request){
        QueryWrapper<YingyaozhaopinEntity> ew = new QueryWrapper<YingyaozhaopinEntity>();

		PageUtils page = yingyaozhaopinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yingyaozhaopin), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YingyaozhaopinEntity yingyaozhaopin){
       	QueryWrapper<YingyaozhaopinEntity> ew = new QueryWrapper<YingyaozhaopinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yingyaozhaopin, "yingyaozhaopin")); 
        return R.ok().put("data", yingyaozhaopinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YingyaozhaopinEntity yingyaozhaopin){
        QueryWrapper< YingyaozhaopinEntity> ew = new QueryWrapper< YingyaozhaopinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yingyaozhaopin, "yingyaozhaopin")); 
		YingyaozhaopinView yingyaozhaopinView =  yingyaozhaopinService.selectView(ew);
		return R.ok("查询应邀招聘成功").put("data", yingyaozhaopinView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YingyaozhaopinEntity yingyaozhaopin = yingyaozhaopinService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(yingyaozhaopin,deSens);
        return R.ok().put("data", yingyaozhaopin);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YingyaozhaopinEntity yingyaozhaopin = yingyaozhaopinService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(yingyaozhaopin,deSens);
        return R.ok().put("data", yingyaozhaopin);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YingyaozhaopinEntity yingyaozhaopin, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yingyaozhaopin);
        yingyaozhaopinService.save(yingyaozhaopin);
        return R.ok().put("data",yingyaozhaopin.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YingyaozhaopinEntity yingyaozhaopin, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yingyaozhaopin);
        yingyaozhaopinService.save(yingyaozhaopin);
        return R.ok().put("data",yingyaozhaopin.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YingyaozhaopinEntity yingyaozhaopin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yingyaozhaopin);
        //全部更新
        yingyaozhaopinService.updateById(yingyaozhaopin);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yingyaozhaopinService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
	











}
