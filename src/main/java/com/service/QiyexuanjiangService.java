package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.QiyexuanjiangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QiyexuanjiangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QiyexuanjiangView;


/**
 * 企业宣讲
 *
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface QiyexuanjiangService extends IService<QiyexuanjiangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QiyexuanjiangVO> selectListVO(Wrapper<QiyexuanjiangEntity> wrapper);
   	
   	QiyexuanjiangVO selectVO(@Param("ew") Wrapper<QiyexuanjiangEntity> wrapper);
   	
   	List<QiyexuanjiangView> selectListView(Wrapper<QiyexuanjiangEntity> wrapper);
   	
   	QiyexuanjiangView selectView(@Param("ew") Wrapper<QiyexuanjiangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QiyexuanjiangEntity> wrapper);

   	

}

