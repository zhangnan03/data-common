package com.service;

import com.entity.Admin;

public interface AdminService {
	public Admin findByUserName(String userName);
}
