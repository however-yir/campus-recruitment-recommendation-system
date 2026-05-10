package com.dao;

import com.entity.ForumEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ForumVO;
import com.entity.view.ForumView;


/**
 * 交流论坛
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
public interface ForumDao extends BaseMapper<ForumEntity> {
	
	List<ForumVO> selectListVO(@Param("ew") Wrapper<ForumEntity> wrapper);
	
	ForumVO selectVO(@Param("ew") Wrapper<ForumEntity> wrapper);
	
	List<ForumView> selectListView(@Param("ew") Wrapper<ForumEntity> wrapper);

	List<ForumView> selectListView(Page<?> page,@Param("ew") Wrapper<ForumEntity> wrapper);

	
	ForumView selectView(@Param("ew") Wrapper<ForumEntity> wrapper);
	

}
