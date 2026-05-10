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

import com.entity.QiyexuanjiangEntity;
import com.entity.view.QiyexuanjiangView;

import com.service.QiyexuanjiangService;
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
 * 企业宣讲
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
@RestController
@RequestMapping("/qiyexuanjiang")
public class QiyexuanjiangController {
    @Autowired
    private QiyexuanjiangService qiyexuanjiangService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QiyexuanjiangEntity qiyexuanjiang,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("qiyexinxi")) {
			qiyexuanjiang.setQiyezhanghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<QiyexuanjiangEntity> ew = new QueryWrapper<QiyexuanjiangEntity>();



		PageUtils page = qiyexuanjiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiyexuanjiang), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QiyexuanjiangEntity qiyexuanjiang, 
		HttpServletRequest request){
        QueryWrapper<QiyexuanjiangEntity> ew = new QueryWrapper<QiyexuanjiangEntity>();

		PageUtils page = qiyexuanjiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiyexuanjiang), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QiyexuanjiangEntity qiyexuanjiang){
       	QueryWrapper<QiyexuanjiangEntity> ew = new QueryWrapper<QiyexuanjiangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qiyexuanjiang, "qiyexuanjiang")); 
        return R.ok().put("data", qiyexuanjiangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QiyexuanjiangEntity qiyexuanjiang){
        QueryWrapper< QiyexuanjiangEntity> ew = new QueryWrapper< QiyexuanjiangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qiyexuanjiang, "qiyexuanjiang")); 
		QiyexuanjiangView qiyexuanjiangView =  qiyexuanjiangService.selectView(ew);
		return R.ok("查询企业宣讲成功").put("data", qiyexuanjiangView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QiyexuanjiangEntity qiyexuanjiang = qiyexuanjiangService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(qiyexuanjiang,deSens);
        return R.ok().put("data", qiyexuanjiang);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QiyexuanjiangEntity qiyexuanjiang = qiyexuanjiangService.getById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(qiyexuanjiang,deSens);
        return R.ok().put("data", qiyexuanjiang);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QiyexuanjiangEntity qiyexuanjiang, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qiyexuanjiang);
        qiyexuanjiangService.save(qiyexuanjiang);
        return R.ok().put("data",qiyexuanjiang.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QiyexuanjiangEntity qiyexuanjiang, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qiyexuanjiang);
        qiyexuanjiangService.save(qiyexuanjiang);
        return R.ok().put("data",qiyexuanjiang.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QiyexuanjiangEntity qiyexuanjiang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qiyexuanjiang);
        //全部更新
        qiyexuanjiangService.updateById(qiyexuanjiang);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qiyexuanjiangService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    
	











}
