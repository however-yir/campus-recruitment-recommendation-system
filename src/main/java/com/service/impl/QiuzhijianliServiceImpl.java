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


import com.dao.QiuzhijianliDao;
import com.entity.QiuzhijianliEntity;
import com.service.QiuzhijianliService;
import com.entity.vo.QiuzhijianliVO;
import com.entity.view.QiuzhijianliView;

@Service("qiuzhijianliService")
public class QiuzhijianliServiceImpl extends ServiceImpl<QiuzhijianliDao, QiuzhijianliEntity> implements QiuzhijianliService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QiuzhijianliEntity> page = this.page(
                new Query<QiuzhijianliEntity>(params).getPage(),
                new QueryWrapper<QiuzhijianliEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QiuzhijianliEntity> wrapper) {
		  Page<QiuzhijianliView> page =new Query<QiuzhijianliView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<QiuzhijianliVO> selectListVO(Wrapper<QiuzhijianliEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QiuzhijianliVO selectVO(Wrapper<QiuzhijianliEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QiuzhijianliView> selectListView(Wrapper<QiuzhijianliEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QiuzhijianliView selectView(Wrapper<QiuzhijianliEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
