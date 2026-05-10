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


import com.dao.ZhiweileixingDao;
import com.entity.ZhiweileixingEntity;
import com.service.ZhiweileixingService;
import com.entity.vo.ZhiweileixingVO;
import com.entity.view.ZhiweileixingView;

@Service("zhiweileixingService")
public class ZhiweileixingServiceImpl extends ServiceImpl<ZhiweileixingDao, ZhiweileixingEntity> implements ZhiweileixingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhiweileixingEntity> page = this.page(
                new Query<ZhiweileixingEntity>(params).getPage(),
                new QueryWrapper<ZhiweileixingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhiweileixingEntity> wrapper) {
		  Page<ZhiweileixingView> page =new Query<ZhiweileixingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZhiweileixingVO> selectListVO(Wrapper<ZhiweileixingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZhiweileixingVO selectVO(Wrapper<ZhiweileixingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZhiweileixingView> selectListView(Wrapper<ZhiweileixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhiweileixingView selectView(Wrapper<ZhiweileixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
