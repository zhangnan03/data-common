package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AdminDao;
import com.entity.Admin;
import com.service.AdminService;
@Transactional(readOnly=true)
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	@Override
	public Admin findByUserName(String userName) {
		return adminDao.findByUserName(userName);
	}

}
