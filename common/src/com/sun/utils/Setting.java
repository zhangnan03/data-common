package com.sun.utils;

import java.io.Serializable;

/**
 * 系统设置
 * 
 * 
 * @version 3.0
 */
public class Setting implements Serializable {

	private static final long serialVersionUID = -1478999889661796840L;

	/**
	 * 小数位精确方式
	 */
	public enum RoundType {

		/** 四舍五入 */
		roundHalfUp,

		/** 向上取整 */
		roundUp,

		/** 向下取整 */
		roundDown
	}

	/**
	 * 验证码类型
	 */
	public enum CaptchaType {

		/** 会员登录 */
		memberLogin,

		/** 会员注册 */
		memberRegister,

		/** 后台登录 */
		adminLogin,

		/** 找回密码 */
		findPassword,

		/** 重置密码 */
		resetPassword,

		/** 其它 */
		other
	}

	/**
	 * 账号锁定类型
	 */
	public enum AccountLockType {

		/** 会员 */
		member,

		/** 管理员 */
		admin
	}

	/**
	 * 库存分配时间点
	 */
	public enum StockAllocationTime {

		/** 下订单 */
		order,

		/** 订单支付 */
		payment,

		/** 订单发货 */
		ship
	}

	/**
	 * 评论权限
	 */
	public enum ReviewAuthority {

		/** 任何访问者 */
		anyone,

		/** 注册会员 */
		member,

		/** 已购买会员 */
		purchased
	}
}