package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.ForumreportEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ForumreportVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ForumreportView;


/**
 * 交流论坛举报
 *
 * @author 
 * @email 
 * @date 2025-02-07 12:22:18
 */
public interface ForumreportService extends IService<ForumreportEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ForumreportVO> selectListVO(Wrapper<ForumreportEntity> wrapper);
   	
   	ForumreportVO selectVO(@Param("ew") Wrapper<ForumreportEntity> wrapper);
   	
   	List<ForumreportView> selectListView(Wrapper<ForumreportEntity> wrapper);
   	
   	ForumreportView selectView(@Param("ew") Wrapper<ForumreportEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ForumreportEntity> wrapper);

   	

}

