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

import com.entity.QiuzhijianliEntity;
import com.entity.view.QiuzhijianliView;

import com.service.QiuzhijianliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 求职简历
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
@RestController
@RequestMapping("/qiuzhijianli")
public class QiuzhijianliController {
    @Autowired
    private QiuzhijianliService qiuzhijianliService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QiuzhijianliEntity qiuzhijianli,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			qiuzhijianli.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<QiuzhijianliEntity> ew = new QueryWrapper<QiuzhijianliEntity>();



		PageUtils page = qiuzhijianliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiuzhijianli), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QiuzhijianliEntity qiuzhijianli, 
		HttpServletRequest request){
        QueryWrapper<QiuzhijianliEntity> ew = new QueryWrapper<QiuzhijianliEntity>();

		PageUtils page = qiuzhijianliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiuzhijianli), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QiuzhijianliEntity qiuzhijianli){
       	QueryWrapper<QiuzhijianliEntity> ew = new QueryWrapper<QiuzhijianliEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qiuzhijianli, "qiuzhijianli")); 
        return R.ok().put("data", qiuzhijianliService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QiuzhijianliEntity qiuzhijianli){
        QueryWrapper< QiuzhijianliEntity> ew = new QueryWrapper< QiuzhijianliEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qiuzhijianli, "qiuzhijianli")); 
		QiuzhijianliView qiuzhijianliView =  qiuzhijianliService.selectView(ew);
		return R.ok("查询求职简历成功").put("data", qiuzhijianliView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QiuzhijianliEntity qiuzhijianli = qiuzhijianliService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(qiuzhijianli,deSens);
        return R.ok().put("data", qiuzhijianli);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QiuzhijianliEntity qiuzhijianli = qiuzhijianliService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(qiuzhijianli,deSens);
        return R.ok().put("data", qiuzhijianli);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QiuzhijianliEntity qiuzhijianli, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qiuzhijianli);
        qiuzhijianliService.save(qiuzhijianli);
        return R.ok().put("data",qiuzhijianli.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QiuzhijianliEntity qiuzhijianli, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qiuzhijianli);
        qiuzhijianliService.save(qiuzhijianli);
        return R.ok().put("data",qiuzhijianli.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QiuzhijianliEntity qiuzhijianli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qiuzhijianli);
        //全部更新
        qiuzhijianliService.updateById(qiuzhijianli);

        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<QiuzhijianliEntity> list = new ArrayList<QiuzhijianliEntity>();
        for(Long id : ids) {
            QiuzhijianliEntity qiuzhijianli = qiuzhijianliService.getById(id);
            qiuzhijianli.setSfsh(sfsh);
            qiuzhijianli.setShhf(shhf);
            list.add(qiuzhijianli);
        }
        qiuzhijianliService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qiuzhijianliService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
	











}
