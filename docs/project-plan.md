# Apex Admin - 项目规划文档

## 项目概述

**项目名称**：Apex Admin  
**项目定位**：企业级后台管理系统  
**技术架构**：Spring Cloud 微服务 + Vue 3 前端  
**开发理念**：TDD 驱动开发 + SCALE OS AI 编码效率提升

## 技术架构

### 后端技术栈

| 类别 | 技术 | 版本 | 说明 |
|------|------|------|------|
| **核心框架** | Spring Boot | 3.2+ | 最新稳定版 |
| **JDK** | OpenJDK | 17+ | LTS 版本 |
| **微服务** | Spring Cloud Alibaba | 2022.x | 微服务全家桶 |
| **ORM 框架** | MyBatis-Plus | 3.5+ | 增强版 MyBatis |
| **权限认证** | Sa-Token | 1.38+ | 轻量级认证框架 |
| **数据库** | MySQL | 8.0+ | 主数据库 |
| **缓存** | Redis | 7.0+ | 分布式缓存 |
| **本地缓存** | Caffeine | 2.9+ | 高性能本地缓存 |
| **消息队列** | RabbitMQ | 3.12+ | AMQP 协议消息队列 |
| **搜索引擎** | Elasticsearch | 8.x | 全文搜索引擎 |
| **对象存储** | MinIO | 最新版 | 文件存储服务 |
| **监控** | Prometheus | 最新版 | 指标监控系统 |

### 微服务组件

| 组件 | 用途 | 端口 |
|------|------|------|
| **Nacos** | 服务注册发现 + 配置中心 | 8848 |
| **Spring Cloud Gateway** | API 网关 | 8080 |
| **Sentinel** | 流量控制 + 熔断降级 | 8858 |
| **Seata** | 分布式事务 | 8091 |

### 前端技术栈

| 类别 | 技术 | 说明 |
|------|------|------|
| **框架** | Vue 3 | Composition API |
| **UI 组件库** | Element Plus | 企业级 UI |
| **构建工具** | Vite | 快速构建 |
| **状态管理** | Pinia | 轻量级状态管理 |
| **路由** | Vue Router 4 | 官方路由 |
| **HTTP 客户端** | Axios | Promise 基础 HTTP 库 |

## 微服务架构设计

### 服务拆分

```
apex-admin/
├── apex-gateway/              # API 网关服务 (8080)
├── apex-auth/                 # 认证授权服务 (8100)
├── apex-system/               # 系统管理服务 (8200)
│   ├── 用户管理
│   ├── 角色管理
│   ├── 菜单管理
│   ├── 部门管理
│   └── 岗位管理
├── apex-log/                  # 日志服务 (8300)
│   ├── 操作日志
│   └── 登录日志
├── apex-job/                  # 定时任务服务 (8400)
├── apex-file/                 # 文件服务 (8500)
├── apex-message/              # 消息服务 (8600)
├── apex-search/               # 搜索服务 (8700)
├── apex-monitor/              # 监控服务 (8800)
├── apex-generator/            # 代码生成服务 (8900)
├── apex-common/               # 公共模块
│   ├── apex-common-core/      # 核心工具类
│   ├── apex-common-redis/     # Redis 工具
│   ├── apex-common-mybatis/   # MyBatis 配置
│   ├── apex-common-log/       # 日志工具
│   └── apex-common-security/  # 安全工具
└── apex-api/                  # API 接口定义
    ├── apex-api-system/       # 系统 API
    ├── apex-api-log/          # 日志 API
    └── apex-api-file/         # 文件 API
```

### 服务依赖关系

```
apex-gateway
    ├── apex-auth (认证)
    └── apex-system (系统管理)
        ├── apex-log (日志)
        ├── apex-job (定时任务)
        ├── apex-file (文件)
        ├── apex-message (消息)
        ├── apex-search (搜索)
        └── apex-monitor (监控)
```

## 数据库设计

### 核心表结构

```sql
-- 用户表
CREATE TABLE sys_user (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(20),
    avatar VARCHAR(200),
    status TINYINT DEFAULT 0,
    dept_id BIGINT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME ON UPDATE CURRENT_TIMESTAMP
);

-- 角色表
CREATE TABLE sys_role (
    role_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL,
    role_key VARCHAR(50) NOT NULL,
    sort INT DEFAULT 0,
    status TINYINT DEFAULT 0,
    remark VARCHAR(500),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 菜单表
CREATE TABLE sys_menu (
    menu_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    menu_name VARCHAR(50) NOT NULL,
    parent_id BIGINT DEFAULT 0,
    sort INT DEFAULT 0,
    path VARCHAR(200),
    component VARCHAR(200),
    menu_type CHAR(1),  -- M目录 C菜单 F按钮
    perms VARCHAR(100),
    icon VARCHAR(100),
    status TINYINT DEFAULT 0
);

-- 部门表
CREATE TABLE sys_dept (
    dept_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    parent_id BIGINT DEFAULT 0,
    dept_name VARCHAR(50) NOT NULL,
    sort INT DEFAULT 0,
    leader VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(100),
    status TINYINT DEFAULT 0
);

-- 岗位表
CREATE TABLE sys_post (
    post_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    post_code VARCHAR(50) NOT NULL,
    post_name VARCHAR(50) NOT NULL,
    sort INT DEFAULT 0,
    status TINYINT DEFAULT 0,
    remark VARCHAR(500)
);
```

## 开发规范

### 代码规范

1. **命名规范**
   - 类名：UpperCamelCase
   - 方法名：lowerCamelCase
   - 常量：UPPER_SNAKE_CASE
   - 包名：全小写

2. **分层规范**
   - Controller：处理请求，参数校验
   - Service：业务逻辑，事务管理
   - Mapper：数据访问，SQL 操作
   - Entity：数据库映射对象
   - VO：视图对象，前端展示
   - DTO：数据传输对象

3. **API 规范**
   - RESTful 风格
   - 统一响应格式
   - 统一异常处理
   - 接口版本控制

### TDD 开发流程

1. **编写测试用例** (Red)
   - 根据需求编写失败的测试
   - 测试覆盖正常流程和异常流程

2. **编写实现代码** (Green)
   - 编写最少代码使测试通过
   - 不考虑优化，只关注功能

3. **重构代码** (Refactor)
   - 优化代码结构
   - 提取公共方法
   - 保持测试通过

## SCALE OS 集成

### 配置步骤

1. **初始化 SCALE OS 环境**
   ```bash
   # 安装 SCALE OS
   npm install -g @anthropic-ai/scale-os
   
   # 初始化项目配置
   scale init --project apex-admin
   ```

2. **配置技能映射**
   - 90+ 技能映射覆盖开发流程
   - 自定义技能组合
   - 配置 AI Agent 协作模式

3. **配置工作流**
   - 定义开发阶段
   - 配置质量门禁
   - 设置自动化流程

### AI Agent 协作模式

| 阶段 | Agent | 技能 |
|------|-------|------|
| 需求分析 | Claude | 需求理解、技术方案设计 |
| 代码生成 | Claude | 代码编写、单元测试 |
| 代码审查 | Claude | 代码质量检查、安全审计 |
| 测试执行 | Claude | 测试运行、结果分析 |
| 部署运维 | Claude | 部署脚本、监控配置 |

## 开发里程碑

### Phase 1：基础框架 (2周)

- [ ] 项目初始化
- [ ] 微服务框架搭建
- [ ] 数据库设计
- [ ] 公共模块开发
- [ ] SCALE OS 环境配置

### Phase 2：核心功能 (3周)

- [ ] 用户管理
- [ ] 角色管理
- [ ] 菜单管理
- [ ] 部门管理
- [ ] 岗位管理
- [ ] 权限认证

### Phase 3：扩展功能 (2周)

- [ ] 日志管理
- [ ] 定时任务
- [ ] 文件管理
- [ ] 消息通知

### Phase 4：高级功能 (2周)

- [ ] 代码生成器
- [ ] 搜索功能
- [ ] 系统监控
- [ ] 性能优化

### Phase 5：测试部署 (1周)

- [ ] 集成测试
- [ ] 性能测试
- [ ] 安全测试
- [ ] 部署上线

## 风险与应对

| 风险 | 影响 | 应对措施 |
|------|------|----------|
| 技术复杂度高 | 开发周期延长 | 分阶段实施，优先核心功能 |
| 微服务拆分过度 | 维护成本增加 | 合理拆分，预留扩展点 |
| 性能瓶颈 | 用户体验下降 | 性能测试，缓存优化 |
| 安全漏洞 | 数据泄露 | 安全审计，定期更新 |

## 总结

Apex Admin 是一个完整的企业级后台管理系统，采用最新的技术栈和开发理念。通过 SCALE OS 的集成，我们可以：

1. **提升开发效率** - AI 辅助编码，自动化测试
2. **保证代码质量** - TDD 驱动，代码审查
3. **降低维护成本** - 微服务架构，模块化设计
4. **快速响应变化** - 敏捷开发，持续集成

项目预计总工期 **10周**，分 5 个阶段实施，每个阶段都有明确的交付物和验收标准。
