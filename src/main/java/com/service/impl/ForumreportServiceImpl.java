package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.ForumreportDao;
import com.entity.ForumreportEntity;
import com.service.ForumreportService;
import com.entity.vo.ForumreportVO;
import com.entity.view.ForumreportView;

@Service("forumreportService")
public class ForumreportServiceImpl extends ServiceImpl<ForumreportDao, ForumreportEntity> implements ForumreportService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ForumreportEntity> page = this.page(
                new Query<ForumreportEntity>(params).getPage(),
                new QueryWrapper<ForumreportEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ForumreportEntity> wrapper) {
		  Page<ForumreportView> page =new Query<ForumreportView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ForumreportVO> selectListVO(Wrapper<ForumreportEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ForumreportVO selectVO(Wrapper<ForumreportEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ForumreportView> selectListView(Wrapper<ForumreportEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ForumreportView selectView(Wrapper<ForumreportEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
