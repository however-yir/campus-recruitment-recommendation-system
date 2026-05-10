package com.dao;

import com.entity.QiyexinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QiyexinxiVO;
import com.entity.view.QiyexinxiView;


/**
 * 企业信息
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface QiyexinxiDao extends BaseMapper<QiyexinxiEntity> {
	
	List<QiyexinxiVO> selectListVO(@Param("ew") Wrapper<QiyexinxiEntity> wrapper);
	
	QiyexinxiVO selectVO(@Param("ew") Wrapper<QiyexinxiEntity> wrapper);
	
	List<QiyexinxiView> selectListView(@Param("ew") Wrapper<QiyexinxiEntity> wrapper);

	List<QiyexinxiView> selectListView(Page<?> page,@Param("ew") Wrapper<QiyexinxiEntity> wrapper);

	
	QiyexinxiView selectView(@Param("ew") Wrapper<QiyexinxiEntity> wrapper);
	

}
