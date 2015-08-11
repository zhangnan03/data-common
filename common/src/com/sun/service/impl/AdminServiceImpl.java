package com.sun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.dao.AdminDao;
import com.sun.entity.Admin;
import com.sun.entity.Role;
import com.sun.service.AdminService;
import com.sun.shiro.Principal;
@Service
@Transactional(readOnly=true)
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	@Override
	public Admin findByUserName(String userName) {
		return adminDao.findByUserName(userName);
	}
	public List<String> findAuthorities(Long id) {
		List<String> authorities = new ArrayList<String>();
		Admin admin = adminDao.findOne(id);
		if (admin != null) {
			for (Role role : admin.getRoles()) {
				authorities.addAll(role.getAuthorities());
			}
		}
		return authorities;
	}

	public boolean isAuthenticated() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			return subject.isAuthenticated();
		}
		return false;
	}

	public Admin getCurrent() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return adminDao.findOne(principal.getId());
			}
		}
		return null;
	}

	public String getCurrentUsername() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Admin update(Admin admin) {
		return adminDao.save(admin);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		adminDao.delete(id);
	}
	@Override
	public boolean usernameExists(String userName) {
		return this.findByUserName(userName)!=null?true:false;
	}

}
