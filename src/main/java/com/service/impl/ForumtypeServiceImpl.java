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


import com.dao.ForumtypeDao;
import com.entity.ForumtypeEntity;
import com.service.ForumtypeService;
import com.entity.vo.ForumtypeVO;
import com.entity.view.ForumtypeView;

@Service("forumtypeService")
public class ForumtypeServiceImpl extends ServiceImpl<ForumtypeDao, ForumtypeEntity> implements ForumtypeService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ForumtypeEntity> page = this.page(
                new Query<ForumtypeEntity>(params).getPage(),
                new QueryWrapper<ForumtypeEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ForumtypeEntity> wrapper) {
		  Page<ForumtypeView> page =new Query<ForumtypeView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ForumtypeVO> selectListVO(Wrapper<ForumtypeEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ForumtypeVO selectVO(Wrapper<ForumtypeEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ForumtypeView> selectListView(Wrapper<ForumtypeEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ForumtypeView selectView(Wrapper<ForumtypeEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
