package com.entity.model;

import com.entity.DiscusszhaopinxinxiEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 招聘信息评论表
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-02-07 12:22:19
 */
public class DiscusszhaopinxinxiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 用户id
	 */
	
	private Long userid;
		
	/**
	 * 头像
	 */
	
	private String avatarurl;
		
	/**
	 * 用户名
	 */
	
	private String nickname;
		
	/**
	 * 评论内容
	 */
	
	private String content;
		
	/**
	 * 回复内容
	 */
	
	private String reply;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
	/**
	 * 置顶(1:置顶,0:非置顶)
	 */
	
	private Integer istop;
		
	/**
	 * 赞用户ids
	 */
	
	private String tuserids;
		
	/**
	 * 踩用户ids
	 */
	
	private String cuserids;
				
	
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
	 * 设置：用户名
	 */
	 
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 获取：用户名
	 */
	public String getNickname() {
		return nickname;
	}
				
	
	/**
	 * 设置：评论内容
	 */
	 
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取：评论内容
	 */
	public String getContent() {
		return content;
	}
				
	
	/**
	 * 设置：回复内容
	 */
	 
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	/**
	 * 获取：回复内容
	 */
	public String getReply() {
		return reply;
	}
				
	
	/**
	 * 设置：赞
	 */
	 
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
				
	
	/**
	 * 设置：踩
	 */
	 
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
				
	
	/**
	 * 设置：置顶(1:置顶,0:非置顶)
	 */
	 
	public void setIstop(Integer istop) {
		this.istop = istop;
	}
	
	/**
	 * 获取：置顶(1:置顶,0:非置顶)
	 */
	public Integer getIstop() {
		return istop;
	}
				
	
	/**
	 * 设置：赞用户ids
	 */
	 
	public void setTuserids(String tuserids) {
		this.tuserids = tuserids;
	}
	
	/**
	 * 获取：赞用户ids
	 */
	public String getTuserids() {
		return tuserids;
	}
				
	
	/**
	 * 设置：踩用户ids
	 */
	 
	public void setCuserids(String cuserids) {
		this.cuserids = cuserids;
	}
	
	/**
	 * 获取：踩用户ids
	 */
	public String getCuserids() {
		return cuserids;
	}
			
}
