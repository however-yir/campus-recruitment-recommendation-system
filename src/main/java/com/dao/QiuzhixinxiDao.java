package com.dao;

import com.entity.QiuzhixinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QiuzhixinxiVO;
import com.entity.view.QiuzhixinxiView;


/**
 * 求职信息
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface QiuzhixinxiDao extends BaseMapper<QiuzhixinxiEntity> {
	
	List<QiuzhixinxiVO> selectListVO(@Param("ew") Wrapper<QiuzhixinxiEntity> wrapper);
	
	QiuzhixinxiVO selectVO(@Param("ew") Wrapper<QiuzhixinxiEntity> wrapper);
	
	List<QiuzhixinxiView> selectListView(@Param("ew") Wrapper<QiuzhixinxiEntity> wrapper);

	List<QiuzhixinxiView> selectListView(Page<?> page,@Param("ew") Wrapper<QiuzhixinxiEntity> wrapper);

	
	QiuzhixinxiView selectView(@Param("ew") Wrapper<QiuzhixinxiEntity> wrapper);
	

}
