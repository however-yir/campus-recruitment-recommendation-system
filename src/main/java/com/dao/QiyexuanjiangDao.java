package com.dao;

import com.entity.QiyexuanjiangEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QiyexuanjiangVO;
import com.entity.view.QiyexuanjiangView;


/**
 * 企业宣讲
 * 
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface QiyexuanjiangDao extends BaseMapper<QiyexuanjiangEntity> {
	
	List<QiyexuanjiangVO> selectListVO(@Param("ew") Wrapper<QiyexuanjiangEntity> wrapper);
	
	QiyexuanjiangVO selectVO(@Param("ew") Wrapper<QiyexuanjiangEntity> wrapper);
	
	List<QiyexuanjiangView> selectListView(@Param("ew") Wrapper<QiyexuanjiangEntity> wrapper);

	List<QiyexuanjiangView> selectListView(Page<?> page,@Param("ew") Wrapper<QiyexuanjiangEntity> wrapper);

	
	QiyexuanjiangView selectView(@Param("ew") Wrapper<QiyexuanjiangEntity> wrapper);
	

}
