package com.web.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.web.BaseController;

@Controller("commonController")
public class CommonController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(CommonController.class);
	/**
	 * 登录页面
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		logger.info("首页");
		return "index";
	}
}