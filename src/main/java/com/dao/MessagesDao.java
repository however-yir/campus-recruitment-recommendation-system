package com.dao;

import com.entity.MessagesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.MessagesVO;
import com.entity.view.MessagesView;


/**
 * 留言反馈
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
public interface MessagesDao extends BaseMapper<MessagesEntity> {
	
	List<MessagesVO> selectListVO(@Param("ew") Wrapper<MessagesEntity> wrapper);
	
	MessagesVO selectVO(@Param("ew") Wrapper<MessagesEntity> wrapper);
	
	List<MessagesView> selectListView(@Param("ew") Wrapper<MessagesEntity> wrapper);

	List<MessagesView> selectListView(Page<?> page,@Param("ew") Wrapper<MessagesEntity> wrapper);

	
	MessagesView selectView(@Param("ew") Wrapper<MessagesEntity> wrapper);
	

}
