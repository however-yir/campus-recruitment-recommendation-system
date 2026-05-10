package com.dao;

import com.entity.ForumreportEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ForumreportVO;
import com.entity.view.ForumreportView;


/**
 * 交流论坛举报
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
public interface ForumreportDao extends BaseMapper<ForumreportEntity> {
	
	List<ForumreportVO> selectListVO(@Param("ew") Wrapper<ForumreportEntity> wrapper);
	
	ForumreportVO selectVO(@Param("ew") Wrapper<ForumreportEntity> wrapper);
	
	List<ForumreportView> selectListView(@Param("ew") Wrapper<ForumreportEntity> wrapper);

	List<ForumreportView> selectListView(Page<?> page,@Param("ew") Wrapper<ForumreportEntity> wrapper);

	
	ForumreportView selectView(@Param("ew") Wrapper<ForumreportEntity> wrapper);
	

}
