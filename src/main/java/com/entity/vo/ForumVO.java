package com.entity.vo;

import com.entity.ForumEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 交流论坛
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
public class ForumVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 帖子内容
	 */
	
	private String content;
		
	/**
	 * 父节点id
	 */
	
	private Long parentid;
		
	/**
	 * 用户id
	 */
	
	private Long userid;
		
	/**
	 * 用户名
	 */
	
	private String username;
		
	/**
	 * 头像
	 */
	
	private String avatarurl;
		
	/**
	 * 状态
	 */
	
	private String isdone;
		
	/**
	 * 是否置顶
	 */
	
	private Integer istop;
		
	/**
	 * 置顶时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date toptime;
		
	/**
	 * 分类名称
	 */
	
	private String typename;
		
	/**
	 * 封面
	 */
	
	private String cover;
		
	/**
	 * 是否匿名(1:是,0:否)
	 */
	
	private Integer isanon;
		
	/**
	 * 是否删除(1:是,0:否)
	 */
	
	private Integer delflag;
				
	
	/**
	 * 设置：帖子内容
	 */
	 
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取：帖子内容
	 */
	public String getContent() {
		return content;
	}
				
	
	/**
	 * 设置：父节点id
	 */
	 
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
	
	/**
	 * 获取：父节点id
	 */
	public Long getParentid() {
		return parentid;
	}
				
	
	/**
	 * 设置：用户id
	 */
	 
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	/**
	 * 获取：用户id
	 */
	public Long getUserid() {
		return userid;
	}
				
	
	/**
	 * 设置：用户名
	 */
	 
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
				
	
	/**
	 * 设置：头像
	 */
	 
	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}
	
	/**
	 * 获取：头像
	 */
	public String getAvatarurl() {
		return avatarurl;
	}
				
	
	/**
	 * 设置：状态
	 */
	 
	public void setIsdone(String isdone) {
		this.isdone = isdone;
	}
	
	/**
	 * 获取：状态
	 */
	public String getIsdone() {
		return isdone;
	}
				
	
	/**
	 * 设置：是否置顶
	 */
	 
	public void setIstop(Integer istop) {
		this.istop = istop;
	}
	
	/**
	 * 获取：是否置顶
	 */
	public Integer getIstop() {
		return istop;
	}
				
	
	/**
	 * 设置：置顶时间
	 */
	 
	public void setToptime(Date toptime) {
		this.toptime = toptime;
	}
	
	/**
	 * 获取：置顶时间
	 */
	public Date getToptime() {
		return toptime;
	}
				
	
	/**
	 * 设置：分类名称
	 */
	 
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	/**
	 * 获取：分类名称
	 */
	public String getTypename() {
		return typename;
	}
				
	
	/**
	 * 设置：封面
	 */
	 
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	/**
	 * 获取：封面
	 */
	public String getCover() {
		return cover;
	}
				
	
	/**
	 * 设置：是否匿名(1:是,0:否)
	 */
	 
	public void setIsanon(Integer isanon) {
		this.isanon = isanon;
	}
	
	/**
	 * 获取：是否匿名(1:是,0:否)
	 */
	public Integer getIsanon() {
		return isanon;
	}
				
	
	/**
	 * 设置：是否删除(1:是,0:否)
	 */
	 
	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
	
	/**
	 * 获取：是否删除(1:是,0:否)
	 */
	public Integer getDelflag() {
		return delflag;
	}
			
}
