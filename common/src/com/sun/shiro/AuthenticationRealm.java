package com.sun.shiro;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.octo.captcha.service.CaptchaService;
import com.sun.entity.Admin;
import com.sun.service.AdminService;

/**
 * 权限认证
 * 
 * 
 * @version 3.0
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Resource(name = "imageCaptchaService")
	private CaptchaService captchaService;
	@Autowired
	private AdminService adminService;

	/**
	 * 获取认证信息
	 * 
	 * @param token
	 *            令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		String captchaId = authenticationToken.getCaptchaId();
		String captcha = authenticationToken.getCaptcha();
		String ip = authenticationToken.getHost();
		if (!captchaService.validateResponseForID(captchaId, captcha.toUpperCase())) {
			throw new UnsupportedTokenException();
		}
		if (username != null && password != null) {
			Admin admin = adminService.findByUserName(username);
			if (admin == null) {
				throw new UnknownAccountException();
			}
			if (!admin.getIsEnabled()) {
				throw new DisabledAccountException();
			}
			if (admin.getIsLocked()) {
				Date lockedDate = admin.getLockedDate();
				Date unlockDate = DateUtils.addMinutes(lockedDate, 5);//锁定5分钟
				if (new Date().after(unlockDate)) {
					admin.setLoginFailureCount(0);
					admin.setIsLocked(false);
					admin.setLockedDate(null);
					adminService.update(admin);
				} else {
					throw new LockedAccountException();
				}
			}
			if (!DigestUtils.md5Hex(password).equals(admin.getPassWord())) {
				int loginFailureCount = admin.getLoginFailureCount() + 1;
				if (loginFailureCount >= 5) {
					admin.setIsLocked(true);
					admin.setLockedDate(new Date());
				}
				admin.setLoginFailureCount(loginFailureCount);
				adminService.update(admin);
				throw new IncorrectCredentialsException();
			}
			admin.setLoginIp(ip);
			admin.setLoginDate(new Date());
			admin.setLoginFailureCount(0);
			adminService.update(admin);
			return new SimpleAuthenticationInfo(new Principal(admin.getId(), username), password, getName());
		}
		throw new UnknownAccountException();
	}

	/**
	 * 获取授权信息
	 * 
	 * @param principals
	 *            principals
	 * @return 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<String> authorities = adminService.findAuthorities(principal.getId());
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}

}