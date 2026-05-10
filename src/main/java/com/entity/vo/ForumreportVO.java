package com.entity.vo;

import com.entity.ForumreportEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 交流论坛举报
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
public class ForumreportVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 帖子标题
	 */
	
	private String title;
		
	/**
	 * 举报用户id
	 */
	
	private Long userid;
		
	/**
	 * 举报用户名
	 */
	
	private String username;
		
	/**
	 * 被举报用户id
	 */
	
	private Long reporteduserid;
		
	/**
	 * 被举报用户名
	 */
	
	private String reportedusername;
		
	/**
	 * 举报原因
	 */
	
	private String reason;
		
	/**
	 * 图片补充
	 */
	
	private String picture;
		
	/**
	 * 处理建议
	 */
	
	private String handleadvise;
		
	/**
	 * 状态
	 */
	
	private String status;
		
	/**
	 * 举报类型
	 */
	
	private String reporttype;
				
	
	/**
	 * 设置：帖子标题
	 */
	 
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取：帖子标题
	 */
	public String getTitle() {
		return title;
	}
				
	
	/**
	 * 设置：举报用户id
	 */
	 
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	/**
	 * 获取：举报用户id
	 */
	public Long getUserid() {
		return userid;
	}
				
	
	/**
	 * 设置：举报用户名
	 */
	 
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 获取：举报用户名
	 */
	public String getUsername() {
		return username;
	}
				
	
	/**
	 * 设置：被举报用户id
	 */
	 
	public void setReporteduserid(Long reporteduserid) {
		this.reporteduserid = reporteduserid;
	}
	
	/**
	 * 获取：被举报用户id
	 */
	public Long getReporteduserid() {
		return reporteduserid;
	}
				
	
	/**
	 * 设置：被举报用户名
	 */
	 
	public void setReportedusername(String reportedusername) {
		this.reportedusername = reportedusername;
	}
	
	/**
	 * 获取：被举报用户名
	 */
	public String getReportedusername() {
		return reportedusername;
	}
				
	
	/**
	 * 设置：举报原因
	 */
	 
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**
	 * 获取：举报原因
	 */
	public String getReason() {
		return reason;
	}
				
	
	/**
	 * 设置：图片补充
	 */
	 
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	/**
	 * 获取：图片补充
	 */
	public String getPicture() {
		return picture;
	}
				
	
	/**
	 * 设置：处理建议
	 */
	 
	public void setHandleadvise(String handleadvise) {
		this.handleadvise = handleadvise;
	}
	
	/**
	 * 获取：处理建议
	 */
	public String getHandleadvise() {
		return handleadvise;
	}
				
	
	/**
	 * 设置：状态
	 */
	 
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
				
	
	/**
	 * 设置：举报类型
	 */
	 
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}
	
	/**
	 * 获取：举报类型
	 */
	public String getReporttype() {
		return reporttype;
	}
			
}
