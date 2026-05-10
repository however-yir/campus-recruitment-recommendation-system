package com.dao;

import com.entity.ZhiweileixingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhiweileixingVO;
import com.entity.view.ZhiweileixingView;


/**
 * 职位类型
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface ZhiweileixingDao extends BaseMapper<ZhiweileixingEntity> {
	
	List<ZhiweileixingVO> selectListVO(@Param("ew") Wrapper<ZhiweileixingEntity> wrapper);
	
	ZhiweileixingVO selectVO(@Param("ew") Wrapper<ZhiweileixingEntity> wrapper);
	
	List<ZhiweileixingView> selectListView(@Param("ew") Wrapper<ZhiweileixingEntity> wrapper);

	List<ZhiweileixingView> selectListView(Page<?> page,@Param("ew") Wrapper<ZhiweileixingEntity> wrapper);

	
	ZhiweileixingView selectView(@Param("ew") Wrapper<ZhiweileixingEntity> wrapper);
	

}
