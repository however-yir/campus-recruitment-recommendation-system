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

import com.entity.QiuzhixinxiEntity;
import com.entity.view.QiuzhixinxiView;

import com.service.QiuzhixinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 求职信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
@RestController
@RequestMapping("/qiuzhixinxi")
public class QiuzhixinxiController {
    @Autowired
    private QiuzhixinxiService qiuzhixinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QiuzhixinxiEntity qiuzhixinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("qiyexinxi")) {
			qiuzhixinxi.setQiyezhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			qiuzhixinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<QiuzhixinxiEntity> ew = new QueryWrapper<QiuzhixinxiEntity>();



		PageUtils page = qiuzhixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiuzhixinxi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QiuzhixinxiEntity qiuzhixinxi, 
		HttpServletRequest request){
        QueryWrapper<QiuzhixinxiEntity> ew = new QueryWrapper<QiuzhixinxiEntity>();

		PageUtils page = qiuzhixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiuzhixinxi), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QiuzhixinxiEntity qiuzhixinxi){
       	QueryWrapper<QiuzhixinxiEntity> ew = new QueryWrapper<QiuzhixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qiuzhixinxi, "qiuzhixinxi")); 
        return R.ok().put("data", qiuzhixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QiuzhixinxiEntity qiuzhixinxi){
        QueryWrapper< QiuzhixinxiEntity> ew = new QueryWrapper< QiuzhixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qiuzhixinxi, "qiuzhixinxi")); 
		QiuzhixinxiView qiuzhixinxiView =  qiuzhixinxiService.selectView(ew);
		return R.ok("查询求职信息成功").put("data", qiuzhixinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QiuzhixinxiEntity qiuzhixinxi = qiuzhixinxiService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(qiuzhixinxi,deSens);
        return R.ok().put("data", qiuzhixinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QiuzhixinxiEntity qiuzhixinxi = qiuzhixinxiService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(qiuzhixinxi,deSens);
        return R.ok().put("data", qiuzhixinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QiuzhixinxiEntity qiuzhixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qiuzhixinxi);
        qiuzhixinxiService.save(qiuzhixinxi);
        return R.ok().put("data",qiuzhixinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QiuzhixinxiEntity qiuzhixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qiuzhixinxi);
        qiuzhixinxiService.save(qiuzhixinxi);
        return R.ok().put("data",qiuzhixinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QiuzhixinxiEntity qiuzhixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qiuzhixinxi);
        //全部更新
        qiuzhixinxiService.updateById(qiuzhixinxi);

        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<QiuzhixinxiEntity> list = new ArrayList<QiuzhixinxiEntity>();
        for(Long id : ids) {
            QiuzhixinxiEntity qiuzhixinxi = qiuzhixinxiService.getById(id);
            qiuzhixinxi.setSfsh(sfsh);
            qiuzhixinxi.setShhf(shhf);
            list.add(qiuzhixinxi);
        }
        qiuzhixinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qiuzhixinxiService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
	











}
