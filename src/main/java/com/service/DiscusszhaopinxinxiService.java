package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusszhaopinxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscusszhaopinxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusszhaopinxinxiView;


/**
 * 招聘信息评论表
 *
 * @author 
 * @email 
 * @date 2025-02-07 12:22:19
 */
public interface DiscusszhaopinxinxiService extends IService<DiscusszhaopinxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusszhaopinxinxiVO> selectListVO(Wrapper<DiscusszhaopinxinxiEntity> wrapper);
   	
   	DiscusszhaopinxinxiVO selectVO(@Param("ew") Wrapper<DiscusszhaopinxinxiEntity> wrapper);
   	
   	List<DiscusszhaopinxinxiView> selectListView(Wrapper<DiscusszhaopinxinxiEntity> wrapper);
   	
   	DiscusszhaopinxinxiView selectView(@Param("ew") Wrapper<DiscusszhaopinxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusszhaopinxinxiEntity> wrapper);

   	

}

