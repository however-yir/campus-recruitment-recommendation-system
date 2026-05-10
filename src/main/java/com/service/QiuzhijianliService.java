package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.QiuzhijianliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QiuzhijianliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QiuzhijianliView;


/**
 * 求职简历
 *
 * @author 
 * @email 
 * @date 2025-02-07 12:22:17
 */
public interface QiuzhijianliService extends IService<QiuzhijianliEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QiuzhijianliVO> selectListVO(Wrapper<QiuzhijianliEntity> wrapper);
   	
   	QiuzhijianliVO selectVO(@Param("ew") Wrapper<QiuzhijianliEntity> wrapper);
   	
   	List<QiuzhijianliView> selectListView(Wrapper<QiuzhijianliEntity> wrapper);
   	
   	QiuzhijianliView selectView(@Param("ew") Wrapper<QiuzhijianliEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QiuzhijianliEntity> wrapper);

   	

}

