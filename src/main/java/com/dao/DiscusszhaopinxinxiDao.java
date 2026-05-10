package com.dao;

import com.entity.DiscusszhaopinxinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusszhaopinxinxiVO;
import com.entity.view.DiscusszhaopinxinxiView;


/**
 * 招聘信息评论表
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:19
 */
public interface DiscusszhaopinxinxiDao extends BaseMapper<DiscusszhaopinxinxiEntity> {
	
	List<DiscusszhaopinxinxiVO> selectListVO(@Param("ew") Wrapper<DiscusszhaopinxinxiEntity> wrapper);
	
	DiscusszhaopinxinxiVO selectVO(@Param("ew") Wrapper<DiscusszhaopinxinxiEntity> wrapper);
	
	List<DiscusszhaopinxinxiView> selectListView(@Param("ew") Wrapper<DiscusszhaopinxinxiEntity> wrapper);

	List<DiscusszhaopinxinxiView> selectListView(Page<?> page,@Param("ew") Wrapper<DiscusszhaopinxinxiEntity> wrapper);

	
	DiscusszhaopinxinxiView selectView(@Param("ew") Wrapper<DiscusszhaopinxinxiEntity> wrapper);
	

}
