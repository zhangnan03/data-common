CREATE TABLE `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME NOT NULL,
  `modify_date` DATETIME NOT NULL,
  `address` VARCHAR(255) DEFAULT NULL,
  `birth` DATETIME DEFAULT NULL,
  `email` VARCHAR(255) DEFAULT NULL,
  `gender` INT(11) DEFAULT NULL COMMENT '性别',
  `is_enabled` BIT(1) NOT NULL,
  `is_locked` BIT(1) NOT NULL,
  `locked_date` DATETIME DEFAULT NULL,
  `login_date` DATETIME DEFAULT NULL,
  `login_failure_count` INT(11) NOT NULL,
  `login_ip` VARCHAR(255) DEFAULT NULL,
  `mobile` VARCHAR(255) DEFAULT NULL COMMENT '手机',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '姓名',
  `password` VARCHAR(255) NOT NULL,
  `point` BIGINT(20) NOT NULL,
  `register_ip` VARCHAR(255) NOT NULL,
  `register_date` DATETIME DEFAULT NULL,
  `safe_key_expire` DATETIME DEFAULT NULL,
  `safe_key_value` VARCHAR(255) DEFAULT NULL,
  `member_rank` BIGINT(20) NOT NULL,
  `nick_name` VARCHAR(255) DEFAULT NULL COMMENT '会员昵称',
  `path` VARCHAR(255) DEFAULT NULL COMMENT '头像',
  `area` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8

CREATE TABLE `role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME NOT NULL COMMENT '创建时间',
  `modify_date` DATETIME NOT NULL COMMENT '修改时间',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `is_system` BIT(1) NOT NULL COMMENT '是否系统角色',
  `name` VARCHAR(255) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8

CREATE TABLE `role_authority` (
  `role` BIGINT(20) NOT NULL COMMENT '角色编号',
  `authorities` VARCHAR(255) DEFAULT NULL COMMENT '权限描述'
) ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `admin` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME NOT NULL,
  `modify_date` DATETIME NOT NULL,
  `department` VARCHAR(255) DEFAULT NULL,
  `email` VARCHAR(255) NOT NULL,
  `is_enabled` BIT(1) NOT NULL,
  `is_locked` BIT(1) NOT NULL,
  `locked_date` DATETIME DEFAULT NULL,
  `login_date` DATETIME DEFAULT NULL,
  `login_failure_count` INT(11) NOT NULL,
  `login_ip` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  `is_remind` BIT(1) NOT NULL COMMENT '是否提醒预警消息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

CREATE TABLE `admin_role` (
  `admins` bigint(20) NOT NULL,
  `roles` bigint(20) NOT NULL,
  PRIMARY KEY (`admins`,`roles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
