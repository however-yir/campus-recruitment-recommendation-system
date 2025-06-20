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

import com.entity.YonghuEntity;
import com.entity.view.YonghuView;

import com.service.YonghuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 用户
 * 后端接口
 * @author
 * @email
 * @date 2025-02-07 12:22:16
 */
@RestController
@RequestMapping("/yonghu")
public class YonghuController {
    @Autowired
    private YonghuService yonghuService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", username));
		if(u==null || !u.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}

        if(!"是".equals(u.getSfsh())) return R.error("账号已锁定，请联系管理员审核。");
		String token = tokenService.generateToken(u.getId(), username,"yonghu",  "用户" );
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
		@IgnoreAuth
	    @RequestMapping("/register")
    public R register(@RequestBody YonghuEntity yonghu){
		//ValidatorUtils.validateEntity(yonghu);
		YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
			if(u!=null) {
				return R.error("注册用户已存在");
			}
			yonghu.setId(null);
	        yonghuService.insert(yonghu);
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
        YonghuEntity u = yonghuService.selectById(id);
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
		YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", username));
		if(u==null) {
			return R.error("账号不存在");
		}
	        u.setMima("123456");
        yonghuService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YonghuEntity yonghu,
		HttpServletRequest request){
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();



		PageUtils page = yonghuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YonghuEntity yonghu,
		HttpServletRequest request){
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();

		PageUtils page = yonghuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params));

				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YonghuEntity yonghu){
	EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();
	ew.allEq(MPUtil.allEQMapPre( yonghu, "yonghu"));
        return R.ok().put("data", yonghuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YonghuEntity yonghu){
        EntityWrapper< YonghuEntity> ew = new EntityWrapper< YonghuEntity>();
		ew.allEq(MPUtil.allEQMapPre( yonghu, "yonghu"));
		YonghuView yonghuView =  yonghuService.selectView(ew);
		return R.ok("查询用户成功").put("data", yonghuView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YonghuEntity yonghu = yonghuService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(yonghu,deSens);
        return R.ok().put("data", yonghu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YonghuEntity yonghu = yonghuService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(yonghu,deSens);
        return R.ok().put("data", yonghu);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
	    yonghu.setId(null);
	    //ValidatorUtils.validateEntity(yonghu);
	    YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
        yonghuService.insert(yonghu);
        return R.ok().put("data",yonghu.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
	    yonghu.setId(null);
	    //ValidatorUtils.validateEntity(yonghu);
	    YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
        yonghuService.insert(yonghu);
        return R.ok().put("data",yonghu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yonghu);
        if(yonghu.getId() == null) {
            return R.error("用户ID不能为空");
        }
        YonghuEntity existing = yonghuService.selectById(yonghu.getId());
        if(existing == null) {
            return R.error("用户不存在");
        }
        if(StringUtils.isNotBlank(yonghu.getYonghuzhanghao())
                && yonghuService.selectCount(new EntityWrapper<YonghuEntity>().ne("id", yonghu.getId()).eq("yonghuzhanghao", yonghu.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
        String oldUsername = existing.getYonghuzhanghao();
        if(StringUtils.isNotBlank(yonghu.getYonghuzhanghao())) {
            existing.setYonghuzhanghao(yonghu.getYonghuzhanghao());
        }
        if(StringUtils.isNotBlank(yonghu.getMima())) {
            existing.setMima(yonghu.getMima());
        }
        if(StringUtils.isNotBlank(yonghu.getYonghuxingming())) {
            existing.setYonghuxingming(yonghu.getYonghuxingming());
        }
        if(StringUtils.isNotBlank(yonghu.getXingbie())) {
            existing.setXingbie(yonghu.getXingbie());
        }
        if(yonghu.getTouxiang() != null) {
            existing.setTouxiang(yonghu.getTouxiang());
        }
        if(StringUtils.isNotBlank(yonghu.getLianxifangshi())) {
            existing.setLianxifangshi(yonghu.getLianxifangshi());
        }
        yonghuService.updateById(existing);
        if(StringUtils.isNotBlank(existing.getYonghuzhanghao()) && !existing.getYonghuzhanghao().equals(oldUsername)) {
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(existing.getYonghuzhanghao());
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
        List<YonghuEntity> list = new ArrayList<YonghuEntity>();
        for(Long id : ids) {
            YonghuEntity yonghu = yonghuService.selectById(id);
            yonghu.setSfsh(sfsh);
            yonghu.setShhf(shhf);
            list.add(yonghu);
        }
        yonghuService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yonghuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    private boolean isAdmin(HttpServletRequest request) {
        Object role = request.getSession().getAttribute("role");
        return role != null && "管理员".equals(role.toString());
    }













}
