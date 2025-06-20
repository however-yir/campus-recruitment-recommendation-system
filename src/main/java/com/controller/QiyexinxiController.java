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
import com.entity.TokenEntity;
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

import com.entity.QiyexinxiEntity;
import com.entity.view.QiyexinxiView;

import com.service.QiyexinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 企业信息
 * 后端接口
 * @author
 * @email
 * @date 2025-02-07 12:22:17
 */
@RestController
@RequestMapping("/qiyexinxi")
public class QiyexinxiController {
    @Autowired
    private QiyexinxiService qiyexinxiService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		QiyexinxiEntity u = qiyexinxiService.selectOne(new EntityWrapper<QiyexinxiEntity>().eq("qiyezhanghao", username));
		if(u==null || !u.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}

        if(!"是".equals(u.getSfsh())) return R.error("账号已锁定，请联系管理员审核。");
		String token = tokenService.generateToken(u.getId(), username,"qiyexinxi",  "企业信息" );
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
		@IgnoreAuth
	    @RequestMapping("/register")
    public R register(@RequestBody QiyexinxiEntity qiyexinxi){
		//ValidatorUtils.validateEntity(qiyexinxi);
		QiyexinxiEntity u = qiyexinxiService.selectOne(new EntityWrapper<QiyexinxiEntity>().eq("qiyezhanghao", qiyexinxi.getQiyezhanghao()));
			if(u!=null) {
				return R.error("注册用户已存在");
			}
			qiyexinxi.setId(null);
	        qiyexinxiService.insert(qiyexinxi);
	        return R.ok();
    }


	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
	Long id = (Long)request.getSession().getAttribute("userId");
        QiyexinxiEntity u = qiyexinxiService.selectById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
		@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
		if(!isAdmin(request)) {
			return R.error(403, "仅管理员可重置密码");
		}
		QiyexinxiEntity u = qiyexinxiService.selectOne(new EntityWrapper<QiyexinxiEntity>().eq("qiyezhanghao", username));
		if(u==null) {
			return R.error("账号不存在");
		}
	        u.setMima("123456");
        qiyexinxiService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QiyexinxiEntity qiyexinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("qiyexinxi")) {
			qiyexinxi.setQiyezhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<QiyexinxiEntity> ew = new EntityWrapper<QiyexinxiEntity>();



		PageUtils page = qiyexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiyexinxi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QiyexinxiEntity qiyexinxi,
		HttpServletRequest request){
        EntityWrapper<QiyexinxiEntity> ew = new EntityWrapper<QiyexinxiEntity>();

		PageUtils page = qiyexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiyexinxi), params), params));

				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QiyexinxiEntity qiyexinxi){
	EntityWrapper<QiyexinxiEntity> ew = new EntityWrapper<QiyexinxiEntity>();
	ew.allEq(MPUtil.allEQMapPre( qiyexinxi, "qiyexinxi"));
        return R.ok().put("data", qiyexinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QiyexinxiEntity qiyexinxi){
        EntityWrapper< QiyexinxiEntity> ew = new EntityWrapper< QiyexinxiEntity>();
		ew.allEq(MPUtil.allEQMapPre( qiyexinxi, "qiyexinxi"));
		QiyexinxiView qiyexinxiView =  qiyexinxiService.selectView(ew);
		return R.ok("查询企业信息成功").put("data", qiyexinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QiyexinxiEntity qiyexinxi = qiyexinxiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(qiyexinxi,deSens);
        return R.ok().put("data", qiyexinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QiyexinxiEntity qiyexinxi = qiyexinxiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(qiyexinxi,deSens);
        return R.ok().put("data", qiyexinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QiyexinxiEntity qiyexinxi, HttpServletRequest request){
        if(qiyexinxiService.selectCount(new EntityWrapper<QiyexinxiEntity>().eq("qiyezhanghao", qiyexinxi.getQiyezhanghao()))>0) {
            return R.error("企业账号已存在");
        }
	    qiyexinxi.setId(null);
	    //ValidatorUtils.validateEntity(qiyexinxi);
	    QiyexinxiEntity u = qiyexinxiService.selectOne(new EntityWrapper<QiyexinxiEntity>().eq("qiyezhanghao", qiyexinxi.getQiyezhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
        qiyexinxiService.insert(qiyexinxi);
        return R.ok().put("data",qiyexinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QiyexinxiEntity qiyexinxi, HttpServletRequest request){
        if(qiyexinxiService.selectCount(new EntityWrapper<QiyexinxiEntity>().eq("qiyezhanghao", qiyexinxi.getQiyezhanghao()))>0) {
            return R.error("企业账号已存在");
        }
	    qiyexinxi.setId(null);
	    //ValidatorUtils.validateEntity(qiyexinxi);
	    QiyexinxiEntity u = qiyexinxiService.selectOne(new EntityWrapper<QiyexinxiEntity>().eq("qiyezhanghao", qiyexinxi.getQiyezhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
        qiyexinxiService.insert(qiyexinxi);
        return R.ok().put("data",qiyexinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QiyexinxiEntity qiyexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qiyexinxi);
        if(qiyexinxi.getId() == null) {
            return R.error("企业ID不能为空");
        }
        QiyexinxiEntity existing = qiyexinxiService.selectById(qiyexinxi.getId());
        if(existing == null) {
            return R.error("企业不存在");
        }
        if(StringUtils.isNotBlank(qiyexinxi.getQiyezhanghao())
                && qiyexinxiService.selectCount(new EntityWrapper<QiyexinxiEntity>().ne("id", qiyexinxi.getId()).eq("qiyezhanghao", qiyexinxi.getQiyezhanghao()))>0) {
            return R.error("企业账号已存在");
        }
        String oldUsername = existing.getQiyezhanghao();
        if(StringUtils.isNotBlank(qiyexinxi.getQiyezhanghao())) {
            existing.setQiyezhanghao(qiyexinxi.getQiyezhanghao());
        }
        if(StringUtils.isNotBlank(qiyexinxi.getQiyemingcheng())) {
            existing.setQiyemingcheng(qiyexinxi.getQiyemingcheng());
        }
        if(StringUtils.isNotBlank(qiyexinxi.getMima())) {
            existing.setMima(qiyexinxi.getMima());
        }
        if(qiyexinxi.getFengmian() != null) {
            existing.setFengmian(qiyexinxi.getFengmian());
        }
        if(StringUtils.isNotBlank(qiyexinxi.getFarendaibiao())) {
            existing.setFarendaibiao(qiyexinxi.getFarendaibiao());
        }
        if(StringUtils.isNotBlank(qiyexinxi.getLianxifangshi())) {
            existing.setLianxifangshi(qiyexinxi.getLianxifangshi());
        }
        if(StringUtils.isNotBlank(qiyexinxi.getQiyedizhi())) {
            existing.setQiyedizhi(qiyexinxi.getQiyedizhi());
        }
        if(StringUtils.isNotBlank(qiyexinxi.getQiyejianjie())) {
            existing.setQiyejianjie(qiyexinxi.getQiyejianjie());
        }
        qiyexinxiService.updateById(existing);
        if(StringUtils.isNotBlank(existing.getQiyezhanghao()) && !existing.getQiyezhanghao().equals(oldUsername)) {
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(existing.getQiyezhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", existing.getId()));
        }


        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<QiyexinxiEntity> list = new ArrayList<QiyexinxiEntity>();
        for(Long id : ids) {
            QiyexinxiEntity qiyexinxi = qiyexinxiService.selectById(id);
            qiyexinxi.setSfsh(sfsh);
            qiyexinxi.setShhf(shhf);
            list.add(qiyexinxi);
        }
        qiyexinxiService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qiyexinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    private boolean isAdmin(HttpServletRequest request) {
        Object role = request.getSession().getAttribute("role");
        return role != null && "管理员".equals(role.toString());
    }













}
