# Apex System - 系统管理服务

## 概述

系统管理服务是 Apex Admin 的核心模块，负责用户管理、角色管理、菜单管理、部门管理、岗位管理等系统配置功能。

## 端口

- 服务端口：**8200**

## 功能特性

### 1. 用户管理
- 用户列表查询（分页、条件查询）
- 用户新增、修改、删除
- 用户密码重置
- 用户状态管理
- 用户名/手机号/邮箱唯一性校验

### 2. 角色管理
- 角色列表查询（分页）
- 角色新增、修改、删除
- 角色状态管理
- 角色权限分配
- 角色名称/权限标识唯一性校验

### 3. 菜单管理
- 菜单列表查询（树形结构）
- 菜单新增、修改、删除
- 菜单类型支持（目录、菜单、按钮）
- 菜单权限标识配置
- 菜单名称唯一性校验

### 4. 部门管理
- 部门列表查询（树形结构）
- 部门新增、修改、删除
- 部门层级关系维护
- 部门名称唯一性校验

### 5. 岗位管理
- 岗位列表查询（分页）
- 岗位新增、修改、删除
- 岗位编码/名称唯一性校验

## API 接口

### 用户管理

```http
# 获取用户列表
GET /system/user/list?username=admin&status=0&pageNum=1&pageSize=10

# 获取用户详情
GET /system/user/{userId}

# 新增用户
POST /system/user
{
    "username": "newuser",
    "nickname": "新用户",
    "password": "password123",
    "email": "user@example.com",
    "phone": "13800138000",
    "sex": 0,
    "deptId": 103,
    "status": 0
}

# 修改用户
PUT /system/user
{
    "userId": 1,
    "nickname": "修改后的昵称",
    "email": "newemail@example.com"
}

# 删除用户
DELETE /system/user/1,2,3

# 重置密码
PUT /system/user/resetPwd?userId=1&password=new123

# 修改状态
PUT /system/user/changeStatus?userId=1&status=0
```

### 角色管理

```http
# 获取角色列表
GET /system/role/list?pageNum=1&pageSize=10

# 获取角色详情
GET /system/role/{roleId}

# 新增角色
POST /system/role
{
    "roleName": "普通角色",
    "roleKey": "common",
    "roleSort": 2,
    "status": 0
}

# 修改角色
PUT /system/role
{
    "roleId": 2,
    "roleName": "修改后的角色名",
    "status": 0
}

# 删除角色
DELETE /system/role/2,3
```

### 菜单管理

```http
# 获取菜单列表
GET /system/menu/list

# 获取菜单树
GET /system/menu/tree

# 获取菜单详情
GET /system/menu/{menuId}

# 新增菜单
POST /system/menu
{
    "menuName": "用户管理",
    "parentId": 1,
    "orderNum": 1,
    "path": "user",
    "component": "system/user/index",
    "menuType": "C",
    "perms": "system:user:list",
    "icon": "user"
}

# 修改菜单
PUT /system/menu
{
    "menuId": 100,
    "menuName": "修改后的菜单名"
}

# 删除菜单
DELETE /system/menu/100
```

### 部门管理

```http
# 获取部门列表
GET /system/dept/list

# 获取部门树
GET /system/dept/tree

# 获取部门详情
GET /system/dept/{deptId}

# 新增部门
POST /system/dept
{
    "deptName": "研发部门",
    "parentId": 101,
    "orderNum": 1,
    "leader": "admin",
    "phone": "15888888888",
    "email": "dept@example.com",
    "status": 0
}

# 修改部门
PUT /system/dept
{
    "deptId": 103,
    "deptName": "修改后的部门名"
}

# 删除部门
DELETE /system/dept/103
```

### 岗位管理

```http
# 获取岗位列表
GET /system/post/list?pageNum=1&pageSize=10

# 获取岗位详情
GET /system/post/{postId}

# 新增岗位
POST /system/post
{
    "postCode": "dev",
    "postName": "开发工程师",
    "postSort": 1,
    "status": 0
}

# 修改岗位
PUT /system/post
{
    "postId": 1,
    "postName": "修改后的岗位名"
}

# 删除岗位
DELETE /system/post/1,2
```

## 数据库表结构

### 用户表 (sys_user)
- user_id: 用户ID
- username: 用户账号
- nickname: 用户昵称
- email: 邮箱
- phone: 手机号
- sex: 性别
- avatar: 头像
- password: 密码
- status: 状态
- dept_id: 部门ID

### 角色表 (sys_role)
- role_id: 角色ID
- role_name: 角色名称
- role_key: 角色权限字符串
- role_sort: 显示顺序
- data_scope: 数据范围
- status: 状态

### 菜单表 (sys_menu)
- menu_id: 菜单ID
- menu_name: 菜单名称
- parent_id: 父菜单ID
- order_num: 显示顺序
- path: 路由地址
- component: 组件路径
- menu_type: 菜单类型
- perms: 权限标识
- icon: 菜单图标

### 部门表 (sys_dept)
- dept_id: 部门ID
- parent_id: 父部门ID
- ancestors: 祖级列表
- dept_name: 部门名称
- order_num: 显示顺序
- leader: 负责人

### 岗位表 (sys_post)
- post_id: 岗位ID
- post_code: 岗位编码
- post_name: 岗位名称
- post_sort: 显示顺序
- status: 状态

## 技术实现

### MyBatis Plus
- 使用 MyBatis Plus 简化 CRUD 操作
- 支持分页查询
- 支持逻辑删除
- 支持自动填充

### 权限控制
- 使用 Sa-Token 进行权限验证
- 支持注解式权限控制
- 支持数据权限控制

### 日志记录
- 使用 AOP 记录操作日志
- 支持自定义日志注解
- 异步保存日志

## 依赖服务

- **MySQL**: 数据存储
- **Redis**: 缓存、Token 存储
- **Nacos**: 服务注册发现、配置中心
- **Auth Service**: 认证授权（Feign 调用）

## 启动命令

```bash
# 开发环境
java -jar apex-system.jar --spring.profiles.active=dev

# 生产环境
java -jar apex-system.jar --spring.profiles.active=prod
```

## 注意事项

1. 删除用户前需要检查是否为管理员
2. 删除部门前需要检查是否有子部门
3. 删除角色前需要检查是否有用户使用
4. 删除菜单前需要检查是否有子菜单
5. 密码需要加密存储
6. 敏感操作需要记录日志
