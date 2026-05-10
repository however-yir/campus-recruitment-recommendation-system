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


import com.dao.YingyaozhaopinDao;
import com.entity.YingyaozhaopinEntity;
import com.service.YingyaozhaopinService;
import com.entity.vo.YingyaozhaopinVO;
import com.entity.view.YingyaozhaopinView;

@Service("yingyaozhaopinService")
public class YingyaozhaopinServiceImpl extends ServiceImpl<YingyaozhaopinDao, YingyaozhaopinEntity> implements YingyaozhaopinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YingyaozhaopinEntity> page = this.page(
                new Query<YingyaozhaopinEntity>(params).getPage(),
                new QueryWrapper<YingyaozhaopinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YingyaozhaopinEntity> wrapper) {
		  Page<YingyaozhaopinView> page =new Query<YingyaozhaopinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YingyaozhaopinVO> selectListVO(Wrapper<YingyaozhaopinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YingyaozhaopinVO selectVO(Wrapper<YingyaozhaopinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YingyaozhaopinView> selectListView(Wrapper<YingyaozhaopinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YingyaozhaopinView selectView(Wrapper<YingyaozhaopinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
