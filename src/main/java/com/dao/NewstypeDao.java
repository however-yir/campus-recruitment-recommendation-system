package com.dao;

import com.entity.NewstypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.NewstypeVO;
import com.entity.view.NewstypeView;


/**
 * 公告信息分类
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
public interface NewstypeDao extends BaseMapper<NewstypeEntity> {
	
	List<NewstypeVO> selectListVO(@Param("ew") Wrapper<NewstypeEntity> wrapper);
	
	NewstypeVO selectVO(@Param("ew") Wrapper<NewstypeEntity> wrapper);
	
	List<NewstypeView> selectListView(@Param("ew") Wrapper<NewstypeEntity> wrapper);

	List<NewstypeView> selectListView(Page<?> page,@Param("ew") Wrapper<NewstypeEntity> wrapper);

	
	NewstypeView selectView(@Param("ew") Wrapper<NewstypeEntity> wrapper);
	

}
