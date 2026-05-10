package com.dao;

import com.entity.QiuzhijianliEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QiuzhijianliVO;
import com.entity.view.QiuzhijianliView;


/**
 * 求职简历
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface QiuzhijianliDao extends BaseMapper<QiuzhijianliEntity> {
	
	List<QiuzhijianliVO> selectListVO(@Param("ew") Wrapper<QiuzhijianliEntity> wrapper);
	
	QiuzhijianliVO selectVO(@Param("ew") Wrapper<QiuzhijianliEntity> wrapper);
	
	List<QiuzhijianliView> selectListView(@Param("ew") Wrapper<QiuzhijianliEntity> wrapper);

	List<QiuzhijianliView> selectListView(Page<?> page,@Param("ew") Wrapper<QiuzhijianliEntity> wrapper);

	
	QiuzhijianliView selectView(@Param("ew") Wrapper<QiuzhijianliEntity> wrapper);
	

}
