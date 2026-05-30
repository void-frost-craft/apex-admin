# Apex Admin - Claude Code 开发工作流

## 概述

本文档介绍如何使用 Claude Code 高效开发 Apex Admin 后台管理系统。通过 Claude Code 的强大功能，我们可以实现：

- 智能代码生成
- TDD 驱动开发
- 自动化测试
- 代码审查
- 文档生成

## 开发工作流

### 1. 项目初始化阶段

#### 1.1 创建项目结构

使用 Claude Code 生成标准的微服务项目结构：

```bash
# 与 Claude 对话，生成项目结构
claude "创建一个 Spring Boot 3 微服务项目结构，包含以下模块：
- apex-gateway (API 网关)
- apex-auth (认证授权)
- apex-system (系统管理)
- apex-common (公共模块)
每个模块使用 Maven 构建，JDK 17+"
```

#### 1.2 配置数据库

```bash
# 生成数据库脚本
claude "根据以下需求生成 MySQL 8.0 数据库表结构：
1. 用户表 (sys_user) - 用户基本信息
2. 角色表 (sys_role) - 角色信息
3. 菜单表 (sys_menu) - 菜单权限
4. 部门表 (sys_dept) - 部门组织
5. 岗位表 (sys_post) - 岗位信息
包含必要的索引和外键约束"
```

### 2. 核心功能开发

#### 2.1 用户管理模块

```bash
# 生成用户管理代码
claude "使用 MyBatis-Plus 生成用户管理模块的完整代码：
1. Entity - 用户实体类
2. Mapper - 数据访问层
3. Service - 业务逻辑层
4. Controller - 控制器层
包含 CRUD 操作、分页查询、条件查询"
```

#### 2.2 角色管理模块

```bash
# 生成角色管理代码
claude "生成角色管理模块代码，包含：
1. 角色 CRUD 操作
2. 角色权限分配
3. 角色用户关联
使用 Sa-Token 进行权限控制"
```

#### 2.3 菜单管理模块

```bash
# 生成菜单管理代码
claude "生成菜单管理模块代码，包含：
1. 菜单树形结构
2. 菜单权限控制
3. 动态路由生成
支持 M目录 C菜单 F按钮 三种类型"
```

### 3. TDD 开发流程

#### 3.1 编写测试用例

```bash
# 生成单元测试
claude "为用户管理模块编写 JUnit 5 单元测试：
1. 测试用户注册
2. 测试用户登录
3. 测试用户信息修改
4. 测试用户删除
使用 Mockito 进行 Mock"
```

#### 3.2 实现功能代码

```bash
# 根据测试实现代码
claude "根据以下测试用例，实现用户注册功能：
@Test
void testRegister() {
    // 测试用户注册
}
要求：
1. 用户名唯一性检查
2. 密码加密存储
3. 返回注册结果"
```

#### 3.3 重构优化

```bash
# 重构代码
claude "重构用户服务代码，提取公共方法，优化代码结构：
1. 提取验证逻辑
2. 统一异常处理
3. 优化数据库查询"
```

### 4. 代码审查

#### 4.1 安全审查

```bash
# 安全审查
claude "审查用户管理模块的安全性：
1. SQL 注入风险
2. XSS 攻击风险
3. CSRF 攻击风险
4. 敏感信息泄露
给出修复建议"
```

#### 4.2 性能审查

```bash
# 性能审查
claude "审查用户管理模块的性能：
1. 数据库查询优化
2. 缓存使用建议
3. 并发处理能力
4. 响应时间优化"
```

### 5. 文档生成

#### 5.1 API 文档

```bash
# 生成 API 文档
claude "生成用户管理模块的 API 文档，使用 Swagger 注解：
1. 接口描述
2. 请求参数
3. 响应格式
4. 错误码说明"
```

#### 5.2 使用文档

```bash
# 生成使用文档
claude "生成用户管理模块的使用文档：
1. 功能说明
2. 操作流程
3. 常见问题
4. 注意事项"
```

## Claude Code 高级用法

### 1. 批量代码生成

```bash
# 一次性生成多个模块
claude "根据数据库表结构，生成以下模块的完整代码：
1. 用户管理 (sys_user)
2. 角色管理 (sys_role)
3. 菜单管理 (sys_menu)
4. 部门管理 (sys_dept)
5. 岗位管理 (sys_post)
每个模块包含 Entity、Mapper、Service、Controller"
```

### 2. 代码转换

```bash
# 将单体代码转换为微服务
claude "将以下单体应用代码转换为微服务架构：
1. 提取公共服务
2. 定义服务接口
3. 配置服务调用
4. 处理分布式事务"
```

### 3. 代码优化

```bash
# 优化现有代码
claude "优化以下代码的性能：
1. 减少数据库查询次数
2. 添加缓存支持
3. 优化算法复杂度
4. 提升并发处理能力"
```

## 最佳实践

### 1. 提示词技巧

#### 明确需求
```bash
# 不好的提示
claude "写一个用户管理"

# 好好的提示
claude "使用 Spring Boot 3 + MyBatis-Plus 实现用户管理模块，包含：
1. 用户注册 - 用户名唯一性检查，密码加密
2. 用户登录 - Sa-Token 认证，返回 Token
3. 用户信息修改 - 字段验证，权限检查
4. 用户删除 - 逻辑删除，关联数据处理
包含完整的单元测试"
```

#### 提供上下文
```bash
# 提供项目上下文
claude "在 Apex Admin 项目中，使用以下技术栈：
- Spring Boot 3.2
- MyBatis-Plus 3.5
- Sa-Token 1.38
- MySQL 8.0
实现用户管理模块"
```

#### 分步骤实现
```bash
# 分步骤实现复杂功能
claude "第一步：创建用户实体类和数据库表结构"
claude "第二步：实现用户 Mapper 和 Service"
claude "第三步：实现用户 Controller 和 API"
claude "第四步：编写单元测试和集成测试"
```

### 2. 代码质量保证

#### 测试覆盖
```bash
# 确保测试覆盖
claude "为用户管理模块编写测试，确保：
1. 单元测试覆盖率 ≥ 80%
2. 集成测试覆盖核心流程
3. 边界条件测试
4. 异常情况测试"
```

#### 代码规范
```bash
# 遵循代码规范
claude "生成代码时遵循以下规范：
1. 命名规范 - 驼峰命名法
2. 注释规范 - JavaDoc 注释
3. 异常处理 - 统一异常处理
4. 日志规范 - SLF4J 日志"
```

### 3. 性能优化

#### 数据库优化
```bash
# 数据库优化
claude "优化用户查询的 SQL：
1. 添加必要的索引
2. 优化查询语句
3. 使用分页查询
4. 避免 N+1 查询问题"
```

#### 缓存优化
```bash
# 缓存优化
claude "为用户管理模块添加缓存：
1. 用户信息缓存 - Redis
2. 权限信息缓存 - Caffeine
3. 缓存更新策略
4. 缓存穿透处理"
```

## 开发示例

### 示例 1：用户注册功能

```bash
# 步骤 1：生成实体类
claude "创建用户实体类 User，包含以下字段：
- userId (Long) - 用户ID，主键自增
- username (String) - 用户名，唯一
- password (String) - 密码，加密存储
- nickname (String) - 昵称
- email (String) - 邮箱
- phone (String) - 手机号
- status (Integer) - 状态 0正常 1停用
- createTime (LocalDateTime) - 创建时间
- updateTime (LocalDateTime) - 更新时间
使用 MyBatis-Plus 注解"

# 步骤 2：生成 Mapper
claude "创建用户 Mapper 接口，继承 BaseMapper<User>，包含：
1. 根据用户名查询用户
2. 根据邮箱查询用户
3. 根据手机号查询用户"

# 步骤 3：生成 Service
claude "创建用户 Service 接口和实现类，包含：
1. register(UserDTO) - 用户注册
2. login(String username, String password) - 用户登录
3. update(UserDTO) - 更新用户信息
4. delete(Long userId) - 删除用户
5. getById(Long userId) - 根据ID查询
6. list(PageQuery) - 分页查询"

# 步骤 4：生成 Controller
claude "创建用户 Controller，使用 RESTful 风格：
POST /api/user/register - 用户注册
POST /api/user/login - 用户登录
PUT /api/user/{userId} - 更新用户
DELETE /api/user/{userId} - 删除用户
GET /api/user/{userId} - 查询用户
GET /api/user/list - 分页列表
使用 Sa-Token 进行权限控制"
```

### 示例 2：权限控制

```bash
# 生成权限注解
claude "使用 Sa-Token 实现权限控制：
1. @SaCheckPermission - 权限检查
2. @SaCheckRole - 角色检查
3. @SaCheckLogin - 登录检查
配置权限拦截器和异常处理"

# 生成权限服务
claude "创建权限服务 PermissionService：
1. getPermissionByUserId(Long userId) - 获取用户权限
2. getRoleByUserId(Long userId) - 获取用户角色
3. checkPermission(String permission) - 检查权限
4. checkRole(String role) - 检查角色"
```

## 常见问题

### Q1: 如何提高 Claude Code 的代码生成质量？

A1: 
1. 提供详细的需求描述
2. 指定技术栈和版本
3. 提供示例代码或参考
4. 分步骤实现复杂功能

### Q2: 如何处理 Claude Code 生成的代码错误？

A2:
1. 检查错误信息
2. 提供正确的上下文
3. 要求 Claude 修复错误
4. 手动调整代码

### Q3: 如何保证代码质量？

A3:
1. 使用 TDD 开发流程
2. 进行代码审查
3. 运行单元测试
4. 使用静态代码分析

### Q4: 如何处理复杂的业务逻辑？

A4:
1. 分步骤实现
2. 提供详细的业务规则
3. 使用状态机或工作流
4. 编写完整的测试用例

## 总结

使用 Claude Code 开发 Apex Admin 项目的优势：

1. **高效开发** - 快速生成代码，减少重复工作
2. **质量保证** - TDD 驱动，测试覆盖完整
3. **智能辅助** - AI 代码审查，优化建议
4. **持续学习** - 不断优化开发流程

通过合理使用 Claude Code，我们可以将开发效率提升 3-5 倍，同时保证代码质量。

---

**提示**：本文档将随着项目开发不断更新，添加更多实用技巧和最佳实践。
