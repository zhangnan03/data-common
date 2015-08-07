package com.base.service.impl;

import java.util.List;

import com.base.dao.BaseDao;
import com.base.service.BaseService;

public class BaseServiceImpl <T> implements BaseService<T, Long>{
	
	private BaseDao<T> baseDao;

	@Override
	public T find(Long id) {
		return baseDao.findOne(id);
	}

	@Override
	public List<T> findList(Long... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T update(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long... ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		
	}
}
