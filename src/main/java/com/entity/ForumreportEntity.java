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
 * 交流论坛举报
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
@TableName("forumreport")
public class ForumreportEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ForumreportEntity() {
		
	}
	
	public ForumreportEntity(T t) {
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
	 * 论坛id
	 */
					
	private Long forumid;
	
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
	 * 设置：论坛id
	 */
	public void setForumid(Long forumid) {
		this.forumid = forumid;
	}
	/**
	 * 获取：论坛id
	 */
	public Long getForumid() {
		return forumid;
	}
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
