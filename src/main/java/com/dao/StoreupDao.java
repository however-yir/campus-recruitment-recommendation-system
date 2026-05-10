package com.dao;

import com.entity.StoreupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.StoreupVO;
import com.entity.view.StoreupView;


/**
 * 收藏表
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
public interface StoreupDao extends BaseMapper<StoreupEntity> {
	
	List<StoreupVO> selectListVO(@Param("ew") Wrapper<StoreupEntity> wrapper);
	
	StoreupVO selectVO(@Param("ew") Wrapper<StoreupEntity> wrapper);
	
	List<StoreupView> selectListView(@Param("ew") Wrapper<StoreupEntity> wrapper);

	List<StoreupView> selectListView(Page<?> page,@Param("ew") Wrapper<StoreupEntity> wrapper);

	
	StoreupView selectView(@Param("ew") Wrapper<StoreupEntity> wrapper);
	

}
