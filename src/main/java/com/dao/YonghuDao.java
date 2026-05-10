package com.dao;

import com.entity.YonghuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YonghuVO;
import com.entity.view.YonghuView;


/**
 * 用户
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:16
 */
public interface YonghuDao extends BaseMapper<YonghuEntity> {
	
	List<YonghuVO> selectListVO(@Param("ew") Wrapper<YonghuEntity> wrapper);
	
	YonghuVO selectVO(@Param("ew") Wrapper<YonghuEntity> wrapper);
	
	List<YonghuView> selectListView(@Param("ew") Wrapper<YonghuEntity> wrapper);

	List<YonghuView> selectListView(Page<?> page,@Param("ew") Wrapper<YonghuEntity> wrapper);

	
	YonghuView selectView(@Param("ew") Wrapper<YonghuEntity> wrapper);
	

}
