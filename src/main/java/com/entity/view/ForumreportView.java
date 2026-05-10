package com.entity.view;

import com.entity.ForumreportEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 交流论坛举报
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
@TableName("forumreport")
public class ForumreportView  extends ForumreportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ForumreportView(){
	}
 
 	public ForumreportView(ForumreportEntity forumreportEntity){
 	try {
			BeanUtils.copyProperties(this, forumreportEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
