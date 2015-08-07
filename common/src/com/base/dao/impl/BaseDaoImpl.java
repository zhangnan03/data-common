package com.base.dao.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.base.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T>{

	@Override
	public Iterable<T> findAll(Sort sort) {
		return null;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void delete(Long id) {
		
	}

	@Override
	public void delete(T entity) {
		
	}

	@Override
	public void delete(Iterable<? extends T> entity) {
		
	}

	@Override
	public void deleteAll() {
		
	}

	@Override
	public boolean exists(Long id) {
		return false;
	}

	@Override
	public Iterable<T> findAll() {
		return null;
	}

	@Override
	public T findOne(Long id) {
		return null;
	}

	@Override
	public T save(T entity) {
		return null;
	}

	@Override
	public Iterable<T> save(Iterable<? extends T> entity) {
		return null;
	}

	@Override
	public long count(Specification<T> entity) {
		return 0;
	}

	@Override
	public List<T> findAll(Specification<T> entity) {
		return null;
	}

	@Override
	public Page<T> findAll(Specification<T> entity, Pageable pageable) {
		return null;
	}

	@Override
	public List<T> findAll(Specification<T> entity, Sort sort) {
		return null;
	}

	@Override
	public T findOne(Specification<T> entity) {
		return null;
	}
}
