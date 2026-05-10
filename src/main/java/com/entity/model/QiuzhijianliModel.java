package com.entity.model;

import com.entity.QiuzhijianliEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 求职简历
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public class QiuzhijianliModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
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
