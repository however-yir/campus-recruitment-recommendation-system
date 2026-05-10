package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.ZhaopinjieguoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhaopinjieguoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhaopinjieguoView;


/**
 * 招聘结果
 *
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface ZhaopinjieguoService extends IService<ZhaopinjieguoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhaopinjieguoVO> selectListVO(Wrapper<ZhaopinjieguoEntity> wrapper);
   	
   	ZhaopinjieguoVO selectVO(@Param("ew") Wrapper<ZhaopinjieguoEntity> wrapper);
   	
   	List<ZhaopinjieguoView> selectListView(Wrapper<ZhaopinjieguoEntity> wrapper);
   	
   	ZhaopinjieguoView selectView(@Param("ew") Wrapper<ZhaopinjieguoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhaopinjieguoEntity> wrapper);

   	

}

