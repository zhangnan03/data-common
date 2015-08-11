package com.sun.dao;

import org.springframework.data.repository.CrudRepository;

import com.sun.entity.Admin;
public interface AdminDao extends CrudRepository<Admin, Long>{
	/**
	 * 根据用户名查找管理员
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 管理员，若不存在则返回null
	 */
	Admin findByUserName(String userName);
}
