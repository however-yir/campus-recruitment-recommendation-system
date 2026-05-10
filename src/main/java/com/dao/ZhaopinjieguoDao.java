package com.dao;

import com.entity.ZhaopinjieguoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhaopinjieguoVO;
import com.entity.view.ZhaopinjieguoView;


/**
 * 招聘结果
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface ZhaopinjieguoDao extends BaseMapper<ZhaopinjieguoEntity> {
	
	List<ZhaopinjieguoVO> selectListVO(@Param("ew") Wrapper<ZhaopinjieguoEntity> wrapper);
	
	ZhaopinjieguoVO selectVO(@Param("ew") Wrapper<ZhaopinjieguoEntity> wrapper);
	
	List<ZhaopinjieguoView> selectListView(@Param("ew") Wrapper<ZhaopinjieguoEntity> wrapper);

	List<ZhaopinjieguoView> selectListView(Page<?> page,@Param("ew") Wrapper<ZhaopinjieguoEntity> wrapper);

	
	ZhaopinjieguoView selectView(@Param("ew") Wrapper<ZhaopinjieguoEntity> wrapper);
	

}
