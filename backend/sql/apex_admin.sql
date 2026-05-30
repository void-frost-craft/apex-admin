-- ============================================
-- Apex Admin 数据库设计
-- 版本：1.0.0
-- 日期：2026-05-30
-- 数据库：MySQL 8.0+
-- ============================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `apex_admin` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `apex_admin`;

-- ============================================
-- 1. 用户管理模块
-- ============================================

-- 用户表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
    `user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户账号',
    `nickname` VARCHAR(50) NOT NULL COMMENT '用户昵称',
    `email` VARCHAR(100) DEFAULT '' COMMENT '邮箱',
    `phone` VARCHAR(20) DEFAULT '' COMMENT '手机号',
    `sex` TINYINT DEFAULT 0 COMMENT '性别 0=男 1=女 2=未知',
    `avatar` VARCHAR(200) DEFAULT '' COMMENT '头像地址',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    `status` TINYINT DEFAULT 0 COMMENT '状态 0=正常 1=停用',
    `del_flag` TINYINT DEFAULT 0 COMMENT '删除标志 0=存在 2=删除',
    `login_ip` VARCHAR(128) DEFAULT '' COMMENT '最后登录IP',
    `login_date` DATETIME DEFAULT NULL COMMENT '最后登录时间',
    `dept_id` BIGINT DEFAULT NULL COMMENT '部门ID',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `idx_username` (`username`),
    KEY `idx_dept_id` (`dept_id`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='用户信息表';

-- 用户角色关联表
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`),
    KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB COMMENT='用户和角色关联表';

-- ============================================
-- 2. 角色管理模块
-- ============================================

-- 角色表
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
    `role_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
    `role_key` VARCHAR(100) NOT NULL COMMENT '角色权限字符串',
    `role_sort` INT NOT NULL COMMENT '显示顺序',
    `data_scope` TINYINT DEFAULT 1 COMMENT '数据范围 1=全部数据 2=自定义数据 3=本部门数据 4=本部门及以下数据 5=仅本人数据',
    `menu_check_strictly` TINYINT DEFAULT 1 COMMENT '菜单树选择项是否关联 0=不关联 1=关联',
    `dept_check_strictly` TINYINT DEFAULT 1 COMMENT '部门树选择项是否关联 0=不关联 1=关联',
    `status` TINYINT NOT NULL COMMENT '状态 0=正常 1=停用',
    `del_flag` TINYINT DEFAULT 0 COMMENT '删除标志 0=存在 2=删除',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `idx_role_key` (`role_key`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='角色信息表';

-- 角色菜单关联表
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    `menu_id` BIGINT NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`),
    KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB COMMENT='角色和菜单关联表';

-- 角色部门关联表
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    `dept_id` BIGINT NOT NULL COMMENT '部门ID',
    PRIMARY KEY (`role_id`, `dept_id`),
    KEY `idx_dept_id` (`dept_id`)
) ENGINE=InnoDB COMMENT='角色和部门关联表';

-- ============================================
-- 3. 菜单管理模块
-- ============================================

-- 菜单表
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
    `menu_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name` VARCHAR(50) NOT NULL COMMENT '菜单名称',
    `parent_id` BIGINT DEFAULT 0 COMMENT '父菜单ID',
    `order_num` INT DEFAULT 0 COMMENT '显示顺序',
    `path` VARCHAR(200) DEFAULT '' COMMENT '路由地址',
    `component` VARCHAR(255) DEFAULT NULL COMMENT '组件路径',
    `query` VARCHAR(255) DEFAULT NULL COMMENT '路由参数',
    `is_frame` TINYINT DEFAULT 1 COMMENT '是否为外链 0=是 1=否',
    `is_cache` TINYINT DEFAULT 0 COMMENT '是否缓存 0=缓存 1=不缓存',
    `menu_type` CHAR(1) DEFAULT '' COMMENT '菜单类型 M=目录 C=菜单 F=按钮',
    `visible` TINYINT DEFAULT 0 COMMENT '是否显示 0=显示 1=隐藏',
    `status` TINYINT DEFAULT 0 COMMENT '状态 0=正常 1=停用',
    `perms` VARCHAR(100) DEFAULT NULL COMMENT '权限标识',
    `icon` VARCHAR(100) DEFAULT '#' COMMENT '菜单图标',
    `remark` VARCHAR(500) DEFAULT '' COMMENT '备注',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`menu_id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2000 COMMENT='菜单权限表';

-- ============================================
-- 4. 部门管理模块
-- ============================================

-- 部门表
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
    `dept_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '部门ID',
    `parent_id` BIGINT DEFAULT 0 COMMENT '父部门ID',
    `ancestors` VARCHAR(500) DEFAULT '' COMMENT '祖级列表',
    `dept_name` VARCHAR(50) NOT NULL COMMENT '部门名称',
    `order_num` INT DEFAULT 0 COMMENT '显示顺序',
    `leader` VARCHAR(20) DEFAULT NULL COMMENT '负责人',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `status` TINYINT DEFAULT 0 COMMENT '状态 0=正常 1=停用',
    `del_flag` TINYINT DEFAULT 0 COMMENT '删除标志 0=存在 2=删除',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`dept_id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 COMMENT='部门表';

-- ============================================
-- 5. 岗位管理模块
-- ============================================

-- 岗位表
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
    `post_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `post_code` VARCHAR(64) NOT NULL COMMENT '岗位编码',
    `post_name` VARCHAR(50) NOT NULL COMMENT '岗位名称',
    `post_sort` INT NOT NULL COMMENT '显示顺序',
    `status` TINYINT NOT NULL COMMENT '状态 0=正常 1=停用',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='岗位信息表';

-- 用户岗位关联表
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `post_id` BIGINT NOT NULL COMMENT '岗位ID',
    PRIMARY KEY (`user_id`, `post_id`),
    KEY `idx_post_id` (`post_id`)
) ENGINE=InnoDB COMMENT='用户与岗位关联表';

-- ============================================
-- 6. 日志管理模块
-- ============================================

-- 操作日志表
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
    `oper_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title` VARCHAR(50) DEFAULT '' COMMENT '模块标题',
    `business_type` TINYINT DEFAULT 0 COMMENT '业务类型 0=其它 1=新增 2=修改 3=删除 4=授权 5=导出 6=导入 7=强退 8=清空',
    `method` VARCHAR(100) DEFAULT '' COMMENT '方法名称',
    `request_method` VARCHAR(10) DEFAULT '' COMMENT '请求方式',
    `oper_name` VARCHAR(50) DEFAULT '' COMMENT '操作人员',
    `oper_url` VARCHAR(500) DEFAULT '' COMMENT '请求URL',
    `oper_ip` VARCHAR(128) DEFAULT '' COMMENT '主机地址',
    `oper_location` VARCHAR(255) DEFAULT '' COMMENT '操作地点',
    `oper_param` VARCHAR(2000) DEFAULT '' COMMENT '请求参数',
    `json_result` VARCHAR(2000) DEFAULT '' COMMENT '返回参数',
    `status` TINYINT DEFAULT 0 COMMENT '操作状态 0=正常 1=异常',
    `error_msg` VARCHAR(2000) DEFAULT '' COMMENT '错误消息',
    `oper_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    `cost_time` BIGINT DEFAULT 0 COMMENT '消耗时间',
    PRIMARY KEY (`oper_id`),
    KEY `idx_oper_time` (`oper_time`),
    KEY `idx_business_type` (`business_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='操作日志记录';

-- 登录日志表
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
    `info_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `user_name` VARCHAR(50) DEFAULT '' COMMENT '用户账号',
    `ipaddr` VARCHAR(128) DEFAULT '' COMMENT '登录IP地址',
    `login_location` VARCHAR(255) DEFAULT '' COMMENT '登录地点',
    `browser` VARCHAR(50) DEFAULT '' COMMENT '浏览器类型',
    `os` VARCHAR(50) DEFAULT '' COMMENT '操作系统',
    `status` TINYINT DEFAULT 0 COMMENT '登录状态 0=成功 1=失败',
    `msg` VARCHAR(255) DEFAULT '' COMMENT '提示消息',
    `login_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
    PRIMARY KEY (`info_id`),
    KEY `idx_login_time` (`login_time`),
    KEY `idx_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='系统访问记录';

-- ============================================
-- 7. 字典管理模块
-- ============================================

-- 字典类型表
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
    `dict_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name` VARCHAR(100) DEFAULT '' COMMENT '字典名称',
    `dict_type` VARCHAR(100) DEFAULT '' UNIQUE COMMENT '字典类型',
    `status` TINYINT DEFAULT 0 COMMENT '状态 0=正常 1=停用',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`dict_id`),
    UNIQUE KEY `idx_dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='字典类型表';

-- 字典数据表
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
    `dict_code` BIGINT NOT NULL AUTO_INCREMENT COMMENT '字典编码',
    `dict_sort` INT DEFAULT 0 COMMENT '字典排序',
    `dict_label` VARCHAR(100) DEFAULT '' COMMENT '字典标签',
    `dict_value` VARCHAR(100) DEFAULT '' COMMENT '字典键值',
    `dict_type` VARCHAR(100) DEFAULT '' COMMENT '字典类型',
    `css_class` VARCHAR(100) DEFAULT NULL COMMENT '样式属性',
    `list_class` VARCHAR(100) DEFAULT NULL COMMENT '表格回显样式',
    `is_default` CHAR(1) DEFAULT 'N' COMMENT '是否默认 Y=是 N=否',
    `status` TINYINT DEFAULT 0 COMMENT '状态 0=正常 1=停用',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`dict_code`),
    KEY `idx_dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='字典数据表';

-- ============================================
-- 8. 系统配置模块
-- ============================================

-- 系统配置表
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
    `config_id` INT NOT NULL AUTO_INCREMENT COMMENT '参数主键',
    `config_name` VARCHAR(100) DEFAULT '' COMMENT '参数名称',
    `config_key` VARCHAR(100) DEFAULT '' COMMENT '参数键名',
    `config_value` VARCHAR(500) DEFAULT '' COMMENT '参数键值',
    `config_type` CHAR(1) DEFAULT 'N' COMMENT '系统内置 Y=是 N=否',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`config_id`),
    UNIQUE KEY `idx_config_key` (`config_key`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='参数配置表';

-- ============================================
-- 9. 定时任务模块
-- ============================================

-- 定时任务表
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
    `job_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '任务ID',
    `job_name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '任务名称',
    `job_group` VARCHAR(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
    `invoke_target` VARCHAR(500) NOT NULL COMMENT '调用目标字符串',
    `cron_expression` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'cron执行表达式',
    `misfire_policy` VARCHAR(20) DEFAULT '3' COMMENT '计划执行错误策略 1=立即执行 2=执行一次 3=放弃执行',
    `concurrent` CHAR(1) DEFAULT '1' COMMENT '是否并发执行 0=允许 1=禁止',
    `status` CHAR(1) DEFAULT '0' COMMENT '状态 0=暂停 1=正常',
    `remark` VARCHAR(500) DEFAULT '' COMMENT '备注信息',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`job_id`, `job_name`, `job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='定时任务调度表';

-- 定时任务日志表
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
    `job_log_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
    `job_name` VARCHAR(64) NOT NULL COMMENT '任务名称',
    `job_group` VARCHAR(64) NOT NULL COMMENT '任务组名',
    `invoke_target` VARCHAR(500) NOT NULL COMMENT '调用目标字符串',
    `job_message` VARCHAR(500) DEFAULT '' COMMENT '日志信息',
    `status` CHAR(1) DEFAULT '1' COMMENT '执行状态 0=正常 1=失败',
    `exception_info` VARCHAR(2000) DEFAULT '' COMMENT '异常信息',
    `start_time` DATETIME DEFAULT NULL COMMENT '开始时间',
    `stop_time` DATETIME DEFAULT NULL COMMENT '结束时间',
    PRIMARY KEY (`job_log_id`),
    KEY `idx_job_name_group` (`job_name`, `job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='定时任务调度日志表';

-- ============================================
-- 10. 通知公告模块
-- ============================================

-- 通知公告表
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
    `notice_id` INT NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `notice_title` VARCHAR(50) NOT NULL COMMENT '公告标题',
    `notice_type` TINYINT NOT NULL COMMENT '公告类型 1=通知 2=公告',
    `notice_content` LONGBLOB DEFAULT NULL COMMENT '公告内容',
    `status` TINYINT DEFAULT 0 COMMENT '公告状态 0=正常 1=关闭',
    `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='通知公告表';

-- ============================================
-- 11. 文件管理模块
-- ============================================

-- 文件存储表
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
    `file_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '文件ID',
    `file_name` VARCHAR(200) NOT NULL COMMENT '文件名称',
    `original_name` VARCHAR(200) NOT NULL COMMENT '原始文件名',
    `file_suffix` VARCHAR(20) DEFAULT '' COMMENT '文件后缀',
    `file_path` VARCHAR(500) NOT NULL COMMENT '文件路径',
    `file_url` VARCHAR(500) DEFAULT '' COMMENT '文件URL',
    `file_size` BIGINT DEFAULT 0 COMMENT '文件大小(字节)',
    `file_type` VARCHAR(50) DEFAULT '' COMMENT '文件类型',
    `storage_type` TINYINT DEFAULT 1 COMMENT '存储类型 1=本地 2=MinIO 3=OSS',
    `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`file_id`),
    KEY `idx_file_name` (`file_name`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='文件存储表';

-- ============================================
-- 初始化数据
-- ============================================

-- 初始化管理员用户 (密码：admin123)
INSERT INTO `sys_user` (`user_id`, `username`, `nickname`, `password`, `email`, `phone`, `sex`, `status`, `create_by`, `create_time`) VALUES
(1, 'admin', '超级管理员', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 'admin@apex.com', '15888888888', 0, 0, 'admin', NOW()),
(2, 'apex', '系统管理员', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 'apex@apex.com', '15666666666', 0, 0, 'admin', NOW());

-- 初始化角色信息
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `status`, `create_by`, `create_time`) VALUES
(1, '超级管理员', 'admin', 1, 1, 0, 'admin', NOW()),
(2, '普通角色', 'common', 2, 2, 0, 'admin', NOW());

-- 初始化部门信息
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `create_by`, `create_time`) VALUES
(100, 0, '0', 'Apex科技集团', 0, 'admin', '15888888888', 'admin@apex.com', 0, 'admin', NOW()),
(101, 100, '0,100', '总部', 1, 'admin', '15888888888', 'admin@apex.com', 0, 'admin', NOW()),
(102, 100, '0,100', '分公司', 2, 'apex', '15666666666', 'apex@apex.com', 0, 'admin', NOW()),
(103, 101, '0,100,101', '研发部门', 1, 'admin', '15888888888', 'admin@apex.com', 0, 'admin', NOW()),
(104, 101, '0,100,101', '市场部门', 2, 'apex', '15666666666', 'apex@apex.com', 0, 'admin', NOW()),
(105, 101, '0,100,101', '测试部门', 3, 'admin', '15888888888', 'admin@apex.com', 0, 'admin', NOW()),
(106, 101, '0,100,101', '财务部门', 4, 'apex', '15666666666', 'apex@apex.com', 0, 'admin', NOW()),
(107, 101, '0,100,101', '运维部门', 5, 'admin', '15888888888', 'admin@apex.com', 0, 'admin', NOW()),
(108, 102, '0,100,102', '市场部门', 1, 'apex', '15666666666', 'apex@apex.com', 0, 'admin', NOW()),
(109, 102, '0,100,102', '财务部门', 2, 'apex', '15666666666', 'apex@apex.com', 0, 'admin', NOW());

-- 初始化岗位信息
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `status`, `create_by`, `create_time`) VALUES
(1, 'ceo', '董事长', 1, 0, 'admin', NOW()),
(2, 'cto', '技术总监', 2, 0, 'admin', NOW()),
(3, 'hr', '人力资源总监', 3, 0, 'admin', NOW()),
(4, 'user', '普通员工', 4, 0, 'admin', NOW());

-- 初始化菜单信息
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`) VALUES
-- 一级菜单
(1, '系统管理', 0, 1, 'system', NULL, 'M', 0, 0, '', 'system', 'admin', NOW()),
(2, '系统监控', 0, 2, 'monitor', NULL, 'M', 0, 0, '', 'monitor', 'admin', NOW()),
(3, '系统工具', 0, 3, 'tool', NULL, 'M', 0, 0, '', 'tool', 'admin', NOW()),

-- 系统管理 - 二级菜单
(100, '用户管理', 1, 1, 'user', 'system/user/index', 'C', 0, 0, 'system:user:list', 'user', 'admin', NOW()),
(101, '角色管理', 1, 2, 'role', 'system/role/index', 'C', 0, 0, 'system:role:list', 'peoples', 'admin', NOW()),
(102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 'C', 0, 0, 'system:menu:list', 'tree-table', 'admin', NOW()),
(103, '部门管理', 1, 4, 'dept', 'system/dept/index', 'C', 0, 0, 'system:dept:list', 'tree', 'admin', NOW()),
(104, '岗位管理', 1, 5, 'post', 'system/post/index', 'C', 0, 0, 'system:post:list', 'post', 'admin', NOW()),
(105, '字典管理', 1, 6, 'dict', 'system/dict/index', 'C', 0, 0, 'system:dict:list', 'dict', 'admin', NOW()),
(106, '参数设置', 1, 7, 'config', 'system/config/index', 'C', 0, 0, 'system:config:list', 'edit', 'admin', NOW()),
(107, '通知公告', 1, 8, 'notice', 'system/notice/index', 'C', 0, 0, 'system:notice:list', 'message', 'admin', NOW()),
(108, '日志管理', 1, 9, 'log', '', 'M', 0, 0, '', 'log', 'admin', NOW()),

-- 系统监控 - 二级菜单
(109, '在线用户', 2, 1, 'online', 'monitor/online/index', 'C', 0, 0, 'monitor:online:list', 'online', 'admin', NOW()),
(110, '定时任务', 2, 2, 'job', 'monitor/job/index', 'C', 0, 0, 'monitor:job:list', 'job', 'admin', NOW()),
(111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 'C', 0, 0, 'monitor:druid:list', 'druid', 'admin', NOW()),
(112, '服务监控', 2, 4, 'server', 'monitor/server/index', 'C', 0, 0, 'monitor:server:list', 'server', 'admin', NOW()),
(113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', 'C', 0, 0, 'monitor:cache:list', 'redis', 'admin', NOW()),
(114, '缓存列表', 2, 6, 'cacheList', 'monitor/cache/list', 'C', 0, 0, 'monitor:cache:list', 'redis-list', 'admin', NOW()),

-- 系统工具 - 二级菜单
(115, '代码生成', 3, 2, 'gen', 'tool/gen/index', 'C', 0, 0, 'tool:gen:list', 'code', 'admin', NOW()),
(116, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', 'C', 0, 0, 'tool:swagger:list', 'swagger', 'admin', NOW()),

-- 日志管理 - 三级菜单
(500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', 'C', 0, 0, 'monitor:operlog:list', 'form', 'admin', NOW()),
(501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', 'C', 0, 0, 'monitor:logininfor:list', 'logininfor', 'admin', NOW()),

-- 用户管理按钮
(1000, '用户查询', 100, 1, '', '', 'F', 0, 0, 'system:user:query', '#', 'admin', NOW()),
(1001, '用户新增', 100, 2, '', '', 'F', 0, 0, 'system:user:add', '#', 'admin', NOW()),
(1002, '用户修改', 100, 3, '', '', 'F', 0, 0, 'system:user:edit', '#', 'admin', NOW()),
(1003, '用户删除', 100, 4, '', '', 'F', 0, 0, 'system:user:remove', '#', 'admin', NOW()),
(1004, '重置密码', 100, 5, '', '', 'F', 0, 0, 'system:user:resetPwd', '#', 'admin', NOW()),

-- 角色管理按钮
(1005, '角色查询', 101, 1, '', '', 'F', 0, 0, 'system:role:query', '#', 'admin', NOW()),
(1006, '角色新增', 101, 2, '', '', 'F', 0, 0, 'system:role:add', '#', 'admin', NOW()),
(1007, '角色修改', 101, 3, '', '', 'F', 0, 0, 'system:role:edit', '#', 'admin', NOW()),
(1008, '角色删除', 101, 4, '', '', 'F', 0, 0, 'system:role:remove', '#', 'admin', NOW()),

-- 菜单管理按钮
(1009, '菜单查询', 102, 1, '', '', 'F', 0, 0, 'system:menu:query', '#', 'admin', NOW()),
(1010, '菜单新增', 102, 2, '', '', 'F', 0, 0, 'system:menu:add', '#', 'admin', NOW()),
(1011, '菜单修改', 102, 3, '', '', 'F', 0, 0, 'system:menu:edit', '#', 'admin', NOW()),
(1012, '菜单删除', 102, 4, '', '', 'F', 0, 0, 'system:menu:remove', '#', 'admin', NOW()),

-- 部门管理按钮
(1013, '部门查询', 103, 1, '', '', 'F', 0, 0, 'system:dept:query', '#', 'admin', NOW()),
(1014, '部门新增', 103, 2, '', '', 'F', 0, 0, 'system:dept:add', '#', 'admin', NOW()),
(1015, '部门修改', 103, 3, '', '', 'F', 0, 0, 'system:dept:edit', '#', 'admin', NOW()),
(1016, '部门删除', 103, 4, '', '', 'F', 0, 0, 'system:dept:remove', '#', 'admin', NOW()),

-- 岗位管理按钮
(1017, '岗位查询', 104, 1, '', '', 'F', 0, 0, 'system:post:query', '#', 'admin', NOW()),
(1018, '岗位新增', 104, 2, '', '', 'F', 0, 0, 'system:post:add', '#', 'admin', NOW()),
(1019, '岗位修改', 104, 3, '', '', 'F', 0, 0, 'system:post:edit', '#', 'admin', NOW()),
(1020, '岗位删除', 104, 4, '', '', 'F', 0, 0, 'system:post:remove', '#', 'admin', NOW()),

-- 字典管理按钮
(1021, '字典查询', 105, 1, '', '', 'F', 0, 0, 'system:dict:query', '#', 'admin', NOW()),
(1022, '字典新增', 105, 2, '', '', 'F', 0, 0, 'system:dict:add', '#', 'admin', NOW()),
(1023, '字典修改', 105, 3, '', '', 'F', 0, 0, 'system:dict:edit', '#', 'admin', NOW()),
(1024, '字典删除', 105, 4, '', '', 'F', 0, 0, 'system:dict:remove', '#', 'admin', NOW()),

-- 参数设置按钮
(1025, '参数查询', 106, 1, '', '', 'F', 0, 0, 'system:config:query', '#', 'admin', NOW()),
(1026, '参数新增', 106, 2, '', '', 'F', 0, 0, 'system:config:add', '#', 'admin', NOW()),
(1027, '参数修改', 106, 3, '', '', 'F', 0, 0, 'system:config:edit', '#', 'admin', NOW()),
(1028, '参数删除', 106, 4, '', '', 'F', 0, 0, 'system:config:remove', '#', 'admin', NOW()),

-- 通知公告按钮
(1029, '公告查询', 107, 1, '', '', 'F', 0, 0, 'system:notice:query', '#', 'admin', NOW()),
(1030, '公告新增', 107, 2, '', '', 'F', 0, 0, 'system:notice:add', '#', 'admin', NOW()),
(1031, '公告修改', 107, 3, '', '', 'F', 0, 0, 'system:notice:edit', '#', 'admin', NOW()),
(1032, '公告删除', 107, 4, '', '', 'F', 0, 0, 'system:notice:remove', '#', 'admin', NOW()),

-- 操作日志按钮
(1033, '操作查询', 500, 1, '', '', 'F', 0, 0, 'monitor:operlog:query', '#', 'admin', NOW()),
(1034, '操作删除', 500, 2, '', '', 'F', 0, 0, 'monitor:operlog:remove', '#', 'admin', NOW()),
(1035, '日志导出', 500, 3, '', '', 'F', 0, 0, 'monitor:operlog:export', '#', 'admin', NOW()),

-- 登录日志按钮
(1036, '登录查询', 501, 1, '', '', 'F', 0, 0, 'monitor:logininfor:query', '#', 'admin', NOW()),
(1037, '登录删除', 501, 2, '', '', 'F', 0, 0, 'monitor:logininfor:remove', '#', 'admin', NOW()),
(1038, '日志导出', 501, 3, '', '', 'F', 0, 0, 'monitor:logininfor:export', '#', 'admin', NOW()),

-- 在线用户按钮
(1039, '在线查询', 109, 1, '', '', 'F', 0, 0, 'monitor:online:query', '#', 'admin', NOW()),
(1040, '批量强退', 109, 2, '', '', 'F', 0, 0, 'monitor:online:batchLogout', '#', 'admin', NOW()),
(1041, '单条强退', 109, 3, '', '', 'F', 0, 0, 'monitor:online:forceLogout', '#', 'admin', NOW()),

-- 定时任务按钮
(1042, '任务查询', 110, 1, '', '', 'F', 0, 0, 'monitor:job:query', '#', 'admin', NOW()),
(1043, '任务新增', 110, 2, '', '', 'F', 0, 0, 'monitor:job:add', '#', 'admin', NOW()),
(1044, '任务修改', 110, 3, '', '', 'F', 0, 0, 'monitor:job:edit', '#', 'admin', NOW()),
(1045, '任务删除', 110, 4, '', '', 'F', 0, 0, 'monitor:job:remove', '#', 'admin', NOW()),
(1046, '状态修改', 110, 5, '', '', 'F', 0, 0, 'monitor:job:changeStatus', '#', 'admin', NOW()),
(1047, '任务导出', 110, 6, '', '', 'F', 0, 0, 'monitor:job:export', '#', 'admin', NOW()),

-- 代码生成按钮
(1048, '生成查询', 115, 1, '', '', 'F', 0, 0, 'tool:gen:query', '#', 'admin', NOW()),
(1049, '生成修改', 115, 2, '', '', 'F', 0, 0, 'tool:gen:edit', '#', 'admin', NOW()),
(1050, '生成删除', 115, 3, '', '', 'F', 0, 0, 'tool:gen:remove', '#', 'admin', NOW()),
(1051, '导入代码', 115, 4, '', '', 'F', 0, 0, 'tool:gen:import', '#', 'admin', NOW()),
(1052, '预览代码', 115, 5, '', '', 'F', 0, 0, 'tool:gen:preview', '#', 'admin', NOW()),
(1053, '生成代码', 115, 6, '', '', 'F', 0, 0, 'tool:gen:code', '#', 'admin', NOW());

-- 初始化用户角色关联
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2);

-- 初始化角色菜单关联（管理员拥有所有权限）
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(1, 1), (1, 2), (1, 3), (1, 100), (1, 101), (1, 102), (1, 103), (1, 104), (1, 105), (1, 106), (1, 107), (1, 108),
(1, 109), (1, 110), (1, 111), (1, 112), (1, 113), (1, 114), (1, 115), (1, 116), (1, 500), (1, 501),
(1, 1000), (1, 1001), (1, 1002), (1, 1003), (1, 1004), (1, 1005), (1, 1006), (1, 1007), (1, 1008),
(1, 1009), (1, 1010), (1, 1011), (1, 1012), (1, 1013), (1, 1014), (1, 1015), (1, 1016), (1, 1017),
(1, 1018), (1, 1019), (1, 1020), (1, 1021), (1, 1022), (1, 1023), (1, 1024), (1, 1025), (1, 1026),
(1, 1027), (1, 1028), (1, 1029), (1, 1030), (1, 1031), (1, 1032), (1, 1033), (1, 1034), (1, 1035),
(1, 1036), (1, 1037), (1, 1038), (1, 1039), (1, 1040), (1, 1041), (1, 1042), (1, 1043), (1, 1044),
(1, 1045), (1, 1046), (1, 1047), (1, 1048), (1, 1049), (1, 1050), (1, 1051), (1, 1052), (1, 1053);

-- 初始化字典类型
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `remark`, `create_by`, `create_time`) VALUES
(1, '用户性别', 'sys_user_sex', 0, '用户性别列表', 'admin', NOW()),
(2, '系统状态', 'sys_normal_disable', 0, '系统状态列表', 'admin', NOW()),
(3, '系统是否', 'sys_yes_no', 0, '系统是否列表', 'admin', NOW()),
(4, '通知类型', 'sys_notice_type', 0, '通知类型列表', 'admin', NOW()),
(5, '通知状态', 'sys_notice_status', 0, '通知状态列表', 'admin', NOW()),
(6, '操作类型', 'sys_oper_type', 0, '操作类型列表', 'admin', NOW()),
(7, '系统状态', 'sys_common_status', 0, '登录状态列表', 'admin', NOW());

-- 初始化字典数据
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `remark`, `create_by`, `create_time`) VALUES
(1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', 0, '性别男', 'admin', NOW()),
(2, 2, '女', '1', 'sys_user_sex', '', '', 'N', 0, '性别女', 'admin', NOW()),
(3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', 0, '性别未知', 'admin', NOW()),
(4, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', 0, '正常状态', 'admin', NOW()),
(5, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', 0, '停用状态', 'admin', NOW()),
(6, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', 0, '系统默认是', 'admin', NOW()),
(7, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', 0, '系统默认否', 'admin', NOW()),
(8, 1, '通知', '1', 'sys_notice_type', '', 'primary', 'Y', 0, '通知', 'admin', NOW()),
(9, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', 0, '公告', 'admin', NOW()),
(10, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', 0, '正常状态', 'admin', NOW()),
(11, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', 0, '关闭状态', 'admin', NOW()),
(12, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', 0, '其他操作', 'admin', NOW()),
(13, 1, '新增', '1', 'sys_oper_type', '', 'primary', 'N', 0, '新增操作', 'admin', NOW()),
(14, 2, '修改', '2', 'sys_oper_type', '', 'primary', 'N', 0, '修改操作', 'admin', NOW()),
(15, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', 0, '删除操作', 'admin', NOW()),
(16, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', 0, '授权操作', 'admin', NOW()),
(17, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', 0, '导出操作', 'admin', NOW()),
(18, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', 0, '导入操作', 'admin', NOW()),
(19, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', 0, '强退操作', 'admin', NOW()),
(20, 8, '清空', '8', 'sys_oper_type', '', 'danger', 'N', 0, '清空操作', 'admin', NOW()),
(21, 1, '成功', '0', 'sys_common_status', '', 'primary', 'Y', 0, '正常状态', 'admin', NOW()),
(22, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', 0, '停用状态', 'admin', NOW());

-- 初始化系统配置
INSERT INTO `sys_config` (`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `remark`, `create_by`, `create_time`) VALUES
(1, '主框架页-默认皮肤', 'sys.index.skinName', 'skin-blue', 'Y', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow', 'admin', NOW()),
(2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', '初始化密码 123456', 'admin', NOW()),
(3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', '深色主题theme-dark，浅色主题theme-light', 'admin', NOW()),
(4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', '是否开启验证码功能（true开启，false关闭）', 'admin', NOW()),
(5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', '是否开启注册用户功能（true开启，false关闭）', 'admin', NOW()),
(6, '用户登录-最大重试次数', 'sys.login.maxRetry', '5', 'Y', '登录最大重试次数', 'admin', NOW()),
(7, '用户登录-密码锁定时间', 'sys.login.lockTime', '10', 'Y', '密码锁定时间（分钟）', 'admin', NOW());

-- 初始化定时任务
INSERT INTO `sys_job` (`job_id`, `job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `remark`, `create_by`, `create_time`) VALUES
(1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', '无参数', 'admin', NOW()),
(2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', '有参数', 'admin', NOW()),
(3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', '多参数', 'admin', NOW());

-- ============================================
-- 创建索引优化查询性能
-- ============================================

-- 用户表索引
CREATE INDEX idx_user_status ON sys_user(status);
CREATE INDEX idx_user_dept ON sys_user(dept_id);

-- 角色表索引
CREATE INDEX idx_role_status ON sys_role(status);

-- 菜单表索引
CREATE INDEX idx_menu_parent ON sys_menu(parent_id);
CREATE INDEX idx_menu_type ON sys_menu(menu_type);

-- 部门表索引
CREATE INDEX idx_dept_parent ON sys_dept(parent_id);
CREATE INDEX idx_dept_status ON sys_dept(status);

-- 操作日志索引
CREATE INDEX idx_oper_status ON sys_oper_log(status);
CREATE INDEX idx_oper_business ON sys_oper_log(business_type);

-- 登录日志索引
CREATE INDEX idx_login_status ON sys_logininfor(status);

-- 字典数据索引
CREATE INDEX idx_dict_status ON sys_dict_data(status);

-- 文件表索引
CREATE INDEX idx_file_type ON sys_file(file_type);
CREATE INDEX idx_file_storage ON sys_file(storage_type);

-- ============================================
-- 创建视图简化查询
-- ============================================

-- 用户详情视图
CREATE OR REPLACE VIEW v_user_detail AS
SELECT
    u.user_id,
    u.username,
    u.nickname,
    u.email,
    u.phone,
    u.sex,
    u.avatar,
    u.status,
    u.login_ip,
    u.login_date,
    u.create_time,
    d.dept_name,
    d.leader AS dept_leader
FROM sys_user u
LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
WHERE u.del_flag = 0;

-- 角色权限视图
CREATE OR REPLACE VIEW v_role_permission AS
SELECT
    r.role_id,
    r.role_name,
    r.role_key,
    r.status,
    GROUP_CONCAT(m.perms) AS permissions
FROM sys_role r
LEFT JOIN sys_role_menu rm ON r.role_id = rm.role_id
LEFT JOIN sys_menu m ON rm.menu_id = m.menu_id
WHERE r.del_flag = 0 AND m.status = 0
GROUP BY r.role_id, r.role_name, r.role_key, r.status;

-- 用户角色视图
CREATE OR REPLACE VIEW v_user_role AS
SELECT
    u.user_id,
    u.username,
    GROUP_CONCAT(r.role_name) AS roles,
    GROUP_CONCAT(r.role_key) AS role_keys
FROM sys_user u
LEFT JOIN sys_user_role ur ON u.user_id = ur.user_id
LEFT JOIN sys_role r ON ur.role_id = r.role_id
WHERE u.del_flag = 0 AND r.del_flag = 0
GROUP BY u.user_id, u.username;

-- ============================================
-- 存储过程
-- ============================================

DELIMITER //

-- 根据用户ID获取权限列表
CREATE PROCEDURE sp_get_user_permissions(IN p_user_id BIGINT)
BEGIN
    SELECT DISTINCT m.perms
    FROM sys_menu m
    INNER JOIN sys_role_menu rm ON m.menu_id = rm.menu_id
    INNER JOIN sys_user_role ur ON rm.role_id = ur.role_id
    WHERE ur.user_id = p_user_id
      AND m.status = 0
      AND m.perms IS NOT NULL
      AND m.perms != '';
END //

-- 根据用户ID获取角色列表
CREATE PROCEDURE sp_get_user_roles(IN p_user_id BIGINT)
BEGIN
    SELECT r.role_id, r.role_name, r.role_key
    FROM sys_role r
    INNER JOIN sys_user_role ur ON r.role_id = ur.role_id
    WHERE ur.user_id = p_user_id
      AND r.status = 0
      AND r.del_flag = 0;
END //

-- 获取部门树
CREATE PROCEDURE sp_get_dept_tree()
BEGIN
    SELECT
        dept_id,
        parent_id,
        dept_name,
        order_num,
        status
    FROM sys_dept
    WHERE del_flag = 0
    ORDER BY order_num;
END //

-- 获取菜单树
CREATE PROCEDURE sp_get_menu_tree()
BEGIN
    SELECT
        menu_id,
        parent_id,
        menu_name,
        order_num,
        path,
        component,
        menu_type,
        visible,
        status,
        perms,
        icon
    FROM sys_menu
    WHERE status = 0
    ORDER BY order_num;
END //

DELIMITER ;

-- ============================================
-- 触发器
-- ============================================

DELIMITER //

-- 用户删除时清理关联数据
CREATE TRIGGER trg_user_delete
AFTER UPDATE ON sys_user
FOR EACH ROW
BEGIN
    IF NEW.del_flag = 2 AND OLD.del_flag = 0 THEN
        -- 删除用户角色关联
        DELETE FROM sys_user_role WHERE user_id = NEW.user_id;
        -- 删除用户岗位关联
        DELETE FROM sys_user_post WHERE user_id = NEW.user_id;
    END IF;
END //

-- 角色删除时清理关联数据
CREATE TRIGGER trg_role_delete
AFTER UPDATE ON sys_role
FOR EACH ROW
BEGIN
    IF NEW.del_flag = 2 AND OLD.del_flag = 0 THEN
        -- 删除角色菜单关联
        DELETE FROM sys_role_menu WHERE role_id = NEW.role_id;
        -- 删除角色部门关联
        DELETE FROM sys_role_dept WHERE role_id = NEW.role_id;
        -- 删除用户角色关联
        DELETE FROM sys_user_role WHERE role_id = NEW.role_id;
    END IF;
END //

-- 部门删除时清理关联数据
CREATE TRIGGER trg_dept_delete
AFTER UPDATE ON sys_dept
FOR EACH ROW
BEGIN
    IF NEW.del_flag = 2 AND OLD.del_flag = 0 THEN
        -- 更新子部门的父ID
        UPDATE sys_dept SET parent_id = OLD.parent_id WHERE parent_id = OLD.dept_id;
        -- 更新用户的部门ID
        UPDATE sys_user SET dept_id = NULL WHERE dept_id = OLD.dept_id;
    END IF;
END //

DELIMITER ;

-- ============================================
-- 数据库用户权限设置
-- ============================================

-- 创建应用用户（生产环境使用）
-- CREATE USER 'apex_admin'@'%' IDENTIFIED BY 'your_password_here';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON apex_admin.* TO 'apex_admin'@'%';
-- FLUSH PRIVILEGES;

-- ============================================
-- 完成
-- ============================================

SELECT 'Apex Admin 数据库初始化完成！' AS message;
