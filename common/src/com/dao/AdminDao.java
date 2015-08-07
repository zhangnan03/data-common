package com.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.entity.Admin;

public interface AdminDao extends PagingAndSortingRepository<Admin,Long>{
	public Admin findByUserName(String userName);
}
