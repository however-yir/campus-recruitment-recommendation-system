package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.ZhiweileixingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhiweileixingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhiweileixingView;


/**
 * 职位类型
 *
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface ZhiweileixingService extends IService<ZhiweileixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhiweileixingVO> selectListVO(Wrapper<ZhiweileixingEntity> wrapper);
   	
   	ZhiweileixingVO selectVO(@Param("ew") Wrapper<ZhiweileixingEntity> wrapper);
   	
   	List<ZhiweileixingView> selectListView(Wrapper<ZhiweileixingEntity> wrapper);
   	
   	ZhiweileixingView selectView(@Param("ew") Wrapper<ZhiweileixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhiweileixingEntity> wrapper);

   	

}

