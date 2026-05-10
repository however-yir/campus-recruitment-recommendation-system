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


import com.dao.ForumDao;
import com.entity.ForumEntity;
import com.service.ForumService;
import com.entity.vo.ForumVO;
import com.entity.view.ForumView;

@Service("forumService")
public class ForumServiceImpl extends ServiceImpl<ForumDao, ForumEntity> implements ForumService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ForumEntity> page = this.page(
                new Query<ForumEntity>(params).getPage(),
                new QueryWrapper<ForumEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ForumEntity> wrapper) {
		  Page<ForumView> page =new Query<ForumView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ForumVO> selectListVO(Wrapper<ForumEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ForumVO selectVO(Wrapper<ForumEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ForumView> selectListView(Wrapper<ForumEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ForumView selectView(Wrapper<ForumEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
