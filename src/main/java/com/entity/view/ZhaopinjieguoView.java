package com.entity.view;

import com.entity.ZhaopinjieguoEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 招聘结果
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
@TableName("zhaopinjieguo")
public class ZhaopinjieguoView  extends ZhaopinjieguoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ZhaopinjieguoView(){
	}
 
 	public ZhaopinjieguoView(ZhaopinjieguoEntity zhaopinjieguoEntity){
 	try {
			BeanUtils.copyProperties(this, zhaopinjieguoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
