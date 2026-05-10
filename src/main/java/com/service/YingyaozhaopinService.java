package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.YingyaozhaopinEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YingyaozhaopinVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YingyaozhaopinView;


/**
 * 应邀招聘
 *
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
public interface YingyaozhaopinService extends IService<YingyaozhaopinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YingyaozhaopinVO> selectListVO(Wrapper<YingyaozhaopinEntity> wrapper);
   	
   	YingyaozhaopinVO selectVO(@Param("ew") Wrapper<YingyaozhaopinEntity> wrapper);
   	
   	List<YingyaozhaopinView> selectListView(Wrapper<YingyaozhaopinEntity> wrapper);
   	
   	YingyaozhaopinView selectView(@Param("ew") Wrapper<YingyaozhaopinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YingyaozhaopinEntity> wrapper);

   	

}

