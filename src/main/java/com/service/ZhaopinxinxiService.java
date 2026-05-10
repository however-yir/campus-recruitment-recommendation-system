package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.ZhaopinxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhaopinxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhaopinxinxiView;


/**
 * 招聘信息
 *
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface ZhaopinxinxiService extends IService<ZhaopinxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhaopinxinxiVO> selectListVO(Wrapper<ZhaopinxinxiEntity> wrapper);
   	
   	ZhaopinxinxiVO selectVO(@Param("ew") Wrapper<ZhaopinxinxiEntity> wrapper);
   	
   	List<ZhaopinxinxiView> selectListView(Wrapper<ZhaopinxinxiEntity> wrapper);
   	
   	ZhaopinxinxiView selectView(@Param("ew") Wrapper<ZhaopinxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhaopinxinxiEntity> wrapper);

   	

}

