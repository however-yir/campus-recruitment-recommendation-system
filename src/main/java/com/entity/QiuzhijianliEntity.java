package com.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;


/**
 * 求职简历
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
@TableName("qiuzhijianli")
public class QiuzhijianliEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public QiuzhijianliEntity() {
		
	}
	
	public QiuzhijianliEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 用户账号
	 */
					
	private String yonghuzhanghao;
	
	/**
	 * 用户姓名
	 */
					
	private String yonghuxingming;
	
	/**
	 * 头像
	 */
					
	private String touxiang;
	
	/**
	 * 教育经历
	 */
					
	private String jiaoyujingli;
	
	/**
	 * 培训经历
	 */
					
	private String peixunjingli;
	
	/**
	 * 经历实践
	 */
					
	private String jinglishijian;
	
	/**
	 * 语言能力
	 */
					
	private String yuyannengli;
	
	/**
	 * 自我评价
	 */
					
	private String ziwopingjia;
	
	/**
	 * 求职意向
	 */
					
	private String qiuzhiyixiang;
	
	/**
	 * 获得证书
	 */
					
	private String huodezhengshu;
	
	/**
	 * 投递日期
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date toudiriqi;
	
	/**
	 * 是否审核
	 */
					
	private String sfsh;
	
	/**
	 * 审核回复
	 */
					
	private String shhf;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：用户账号
	 */
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setYonghuxingming(String yonghuxingming) {
		this.yonghuxingming = yonghuxingming;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getYonghuxingming() {
		return yonghuxingming;
	}
	/**
	 * 设置：头像
	 */
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	/**
	 * 获取：头像
	 */
	public String getTouxiang() {
		return touxiang;
	}
	/**
	 * 设置：教育经历
	 */
	public void setJiaoyujingli(String jiaoyujingli) {
		this.jiaoyujingli = jiaoyujingli;
	}
	/**
	 * 获取：教育经历
	 */
	public String getJiaoyujingli() {
		return jiaoyujingli;
	}
	/**
	 * 设置：培训经历
	 */
	public void setPeixunjingli(String peixunjingli) {
		this.peixunjingli = peixunjingli;
	}
	/**
	 * 获取：培训经历
	 */
	public String getPeixunjingli() {
		return peixunjingli;
	}
	/**
	 * 设置：经历实践
	 */
	public void setJinglishijian(String jinglishijian) {
		this.jinglishijian = jinglishijian;
	}
	/**
	 * 获取：经历实践
	 */
	public String getJinglishijian() {
		return jinglishijian;
	}
	/**
	 * 设置：语言能力
	 */
	public void setYuyannengli(String yuyannengli) {
		this.yuyannengli = yuyannengli;
	}
	/**
	 * 获取：语言能力
	 */
	public String getYuyannengli() {
		return yuyannengli;
	}
	/**
	 * 设置：自我评价
	 */
	public void setZiwopingjia(String ziwopingjia) {
		this.ziwopingjia = ziwopingjia;
	}
	/**
	 * 获取：自我评价
	 */
	public String getZiwopingjia() {
		return ziwopingjia;
	}
	/**
	 * 设置：求职意向
	 */
	public void setQiuzhiyixiang(String qiuzhiyixiang) {
		this.qiuzhiyixiang = qiuzhiyixiang;
	}
	/**
	 * 获取：求职意向
	 */
	public String getQiuzhiyixiang() {
		return qiuzhiyixiang;
	}
	/**
	 * 设置：获得证书
	 */
	public void setHuodezhengshu(String huodezhengshu) {
		this.huodezhengshu = huodezhengshu;
	}
	/**
	 * 获取：获得证书
	 */
	public String getHuodezhengshu() {
		return huodezhengshu;
	}
	/**
	 * 设置：投递日期
	 */
	public void setToudiriqi(Date toudiriqi) {
		this.toudiriqi = toudiriqi;
	}
	/**
	 * 获取：投递日期
	 */
	public Date getToudiriqi() {
		return toudiriqi;
	}
	/**
	 * 设置：是否审核
	 */
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
	/**
	 * 设置：审核回复
	 */
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}

}
