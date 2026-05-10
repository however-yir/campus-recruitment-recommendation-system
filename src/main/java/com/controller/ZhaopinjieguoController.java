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

import com.entity.ZhaopinjieguoEntity;
import com.entity.view.ZhaopinjieguoView;

import com.service.ZhaopinjieguoService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 招聘结果
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
@RestController
@RequestMapping("/zhaopinjieguo")
public class ZhaopinjieguoController {
    @Autowired
    private ZhaopinjieguoService zhaopinjieguoService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhaopinjieguoEntity zhaopinjieguo,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("qiyexinxi")) {
			zhaopinjieguo.setQiyezhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			zhaopinjieguo.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<ZhaopinjieguoEntity> ew = new QueryWrapper<ZhaopinjieguoEntity>();



		PageUtils page = zhaopinjieguoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhaopinjieguo), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhaopinjieguoEntity zhaopinjieguo, 
		HttpServletRequest request){
        QueryWrapper<ZhaopinjieguoEntity> ew = new QueryWrapper<ZhaopinjieguoEntity>();

		PageUtils page = zhaopinjieguoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhaopinjieguo), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhaopinjieguoEntity zhaopinjieguo){
       	QueryWrapper<ZhaopinjieguoEntity> ew = new QueryWrapper<ZhaopinjieguoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhaopinjieguo, "zhaopinjieguo")); 
        return R.ok().put("data", zhaopinjieguoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhaopinjieguoEntity zhaopinjieguo){
        QueryWrapper< ZhaopinjieguoEntity> ew = new QueryWrapper< ZhaopinjieguoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhaopinjieguo, "zhaopinjieguo")); 
		ZhaopinjieguoView zhaopinjieguoView =  zhaopinjieguoService.selectView(ew);
		return R.ok("查询招聘结果成功").put("data", zhaopinjieguoView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhaopinjieguoEntity zhaopinjieguo = zhaopinjieguoService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(zhaopinjieguo,deSens);
        return R.ok().put("data", zhaopinjieguo);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhaopinjieguoEntity zhaopinjieguo = zhaopinjieguoService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(zhaopinjieguo,deSens);
        return R.ok().put("data", zhaopinjieguo);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhaopinjieguoEntity zhaopinjieguo, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhaopinjieguo);
        zhaopinjieguoService.save(zhaopinjieguo);
        return R.ok().put("data",zhaopinjieguo.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhaopinjieguoEntity zhaopinjieguo, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhaopinjieguo);
        zhaopinjieguoService.save(zhaopinjieguo);
        return R.ok().put("data",zhaopinjieguo.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhaopinjieguoEntity zhaopinjieguo, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhaopinjieguo);
        //全部更新
        zhaopinjieguoService.updateById(zhaopinjieguo);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhaopinjieguoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
	











}
