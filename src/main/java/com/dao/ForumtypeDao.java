package com.dao;

import com.entity.ForumtypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ForumtypeVO;
import com.entity.view.ForumtypeView;


/**
 * 交流论坛类型
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
public interface ForumtypeDao extends BaseMapper<ForumtypeEntity> {
	
	List<ForumtypeVO> selectListVO(@Param("ew") Wrapper<ForumtypeEntity> wrapper);
	
	ForumtypeVO selectVO(@Param("ew") Wrapper<ForumtypeEntity> wrapper);
	
	List<ForumtypeView> selectListView(@Param("ew") Wrapper<ForumtypeEntity> wrapper);

	List<ForumtypeView> selectListView(Page<?> page,@Param("ew") Wrapper<ForumtypeEntity> wrapper);

	
	ForumtypeView selectView(@Param("ew") Wrapper<ForumtypeEntity> wrapper);
	

}
