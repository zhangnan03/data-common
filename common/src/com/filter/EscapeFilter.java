package com.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;


/**
 * 
 *@Copyright:Copyright (c) 2012-2015
 *@Company:zplay.cn
 *@Title:
 *@Description:
 *@Author:wangdezhen#zplay.cn
 *@Since:2015年1月20日  上午10:17:27
 *@Version:1.0
 */
public class EscapeFilter implements Filter {

	public EscapeFilter() {
	}

	private String cleanXSS(String value) throws IOException {

		String coverValue = URLDecoder.decode(value, "utf-8");
		coverValue = coverValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		coverValue = coverValue.replaceAll("\\(", "& #40;").replaceAll("\\)", "&#41;");
		coverValue = coverValue.replaceAll("'", "&#39;");
		coverValue = coverValue.replaceAll("eval\\((.*)\\)", "");
		coverValue = coverValue.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		coverValue = coverValue.replaceAll("script", "");
		if (!value.equals(coverValue)) {
			coverValue = URLDecoder.decode(coverValue, "utf-8");
		}
		return StringEscapeUtils.escapeEcmaScript(coverValue);
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;
		Map<?, ?> map = hreq.getParameterMap();
		Iterator<?> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next().toString();
			String[] values = hreq.getParameterValues(key);
			if (values != null) {
				for (int i = 0; i < values.length; i++)

				{
					if (!key.equals("password")) {
						values[i] = cleanXSS(values[i]);
					}
				}
				if (values.length > 1) {
					hreq.setAttribute(key, values);
				} else {
					hreq.setAttribute(key, values[0]);
				}
			}

		}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
