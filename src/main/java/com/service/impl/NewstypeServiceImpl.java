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


import com.dao.NewstypeDao;
import com.entity.NewstypeEntity;
import com.service.NewstypeService;
import com.entity.vo.NewstypeVO;
import com.entity.view.NewstypeView;

@Service("newstypeService")
public class NewstypeServiceImpl extends ServiceImpl<NewstypeDao, NewstypeEntity> implements NewstypeService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NewstypeEntity> page = this.page(
                new Query<NewstypeEntity>(params).getPage(),
                new QueryWrapper<NewstypeEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<NewstypeEntity> wrapper) {
		  Page<NewstypeView> page =new Query<NewstypeView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<NewstypeVO> selectListVO(Wrapper<NewstypeEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public NewstypeVO selectVO(Wrapper<NewstypeEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<NewstypeView> selectListView(Wrapper<NewstypeEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public NewstypeView selectView(Wrapper<NewstypeEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
