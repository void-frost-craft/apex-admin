# Apex Admin

一个基于 **Spring Boot 3 + Vue 3** 的企业级后台管理系统。

## 📸 项目截图

> 待补充

## ✨ 项目特性

- 🔐 **完善的权限管理** - 基于 Sa-Token 的认证授权，支持 RBAC 权限控制
- 🏗️ **微服务架构** - 基于 Spring Cloud Alibaba 的微服务架构
- 🎨 **现代化前端** - Vue 3 + Element Plus + Pinia
- 📊 **系统监控** - 服务器监控、缓存监控
- 🔄 **代码生成** - 根据数据库表自动生成 CRUD 代码
- 📝 **操作日志** - 完善的操作日志记录
- ⏰ **定时任务** - 基于 Quartz 的任务调度
- 📁 **文件管理** - 基于 MinIO 的对象存储
- 🔍 **全文搜索** - 基于 Elasticsearch 的搜索功能
- 📨 **消息通知** - 基于 RabbitMQ 的消息队列

## 🛠️ 技术栈

### 后端技术

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 3.2.5 | 基础框架 |
| Spring Cloud | 2023.0.1 | 微服务框架 |
| Spring Cloud Alibaba | 2023.0.1.0 | 微服务组件 |
| MyBatis-Plus | 3.5.6 | ORM 框架 |
| Sa-Token | 1.38.0 | 权限认证 |
| MySQL | 8.0+ | 数据库 |
| Redis | 7.0+ | 缓存 |
| RabbitMQ | 3.12+ | 消息队列 |
| Elasticsearch | 8.x | 搜索引擎 |
| MinIO | 最新版 | 对象存储 |
| Prometheus | 最新版 | 监控系统 |

### 前端技术

| 技术 | 版本 | 说明 |
|------|------|------|
| Vue | 3.4 | 渐进式框架 |
| Vite | 5.x | 构建工具 |
| Element Plus | 2.7 | UI 组件库 |
| Vue Router | 4.x | 路由管理 |
| Pinia | 2.x | 状态管理 |
| Axios | 1.x | HTTP 客户端 |

## 📁 项目结构

```
apex-admin/
├── backend/                      # 后端项目
│   ├── apex-common/              # 公共模块
│   │   ├── apex-common-core/     # 核心工具类
│   │   ├── apex-common-redis/    # Redis 工具
│   │   ├── apex-common-mybatis/  # MyBatis 配置
│   │   ├── apex-common-log/      # 日志工具
│   │   └── apex-common-security/ # 安全工具
│   ├── apex-api/                 # API 接口定义
│   │   ├── apex-api-system/      # 系统 API
│   │   ├── apex-api-log/         # 日志 API
│   │   └── apex-api-file/        # 文件 API
│   ├── apex-gateway/             # API 网关 (8080)
│   ├── apex-auth/                # 认证授权 (8100)
│   ├── apex-system/              # 系统管理 (8200)
│   ├── apex-log/                 # 日志管理 (8300)
│   ├── apex-job/                 # 定时任务 (8400)
│   ├── apex-file/                # 文件服务 (8500)
│   ├── apex-message/             # 消息服务 (8600)
│   ├── apex-search/              # 搜索服务 (8700)
│   ├── apex-monitor/             # 监控服务 (8800)
│   ├── apex-generator/           # 代码生成 (8900)
│   ├── pom.xml
│   └── sql/                      # 数据库脚本
│
└── frontend/                     # 前端项目
    ├── src/
    │   ├── api/                  # API 接口
    │   ├── assets/               # 静态资源
    │   ├── components/           # 公共组件
    │   ├── layouts/              # 布局组件
    │   ├── router/               # 路由配置
    │   ├── store/                # 状态管理
    │   ├── utils/                # 工具函数
    │   └── views/                # 页面组件
    ├── package.json
    └── vite.config.js
```

## 🚀 快速开始

### 环境要求

- **JDK**: 17+
- **Node.js**: 18+
- **Maven**: 3.8+
- **MySQL**: 8.0+
- **Redis**: 7.0+

### 后端启动

1. **初始化数据库**

```bash
# 登录 MySQL
mysql -u root -p

# 执行初始化脚本
source backend/sql/apex_admin.sql
```

2. **修改配置**

修改各模块的 `application.yml` 文件，配置数据库、Redis 等连接信息。

3. **启动服务**

```bash
# 进入后端目录
cd backend

# 编译项目
mvn clean install

# 启动各服务 (按顺序)
cd apex-gateway && mvn spring-boot:run
cd apex-auth && mvn spring-boot:run
cd apex-system && mvn spring-boot:run
# ... 启动其他服务
```

### 前端启动

```bash
# 进入前端目录
cd frontend

# 安装依赖
npm install

# 启动开发服务器
npm run dev

# 构建生产版本
npm run build
```

### 访问地址

| 服务 | 地址 | 说明 |
|------|------|------|
| 前端 | http://localhost:3000 | Vue 前端 |
| 网关 | http://localhost:8080 | API 网关 |
| 认证 | http://localhost:8100 | 认证服务 |
| 系统 | http://localhost:8200 | 系统管理 |

### 默认账号

- **用户名**: admin
- **密码**: admin123

## 📖 功能模块

### 系统管理

- ✅ 用户管理 - 用户的增删改查、密码重置、状态管理
- ✅ 角色管理 - 角色的增删改查、权限分配
- ✅ 菜单管理 - 菜单的增删改查、树形结构
- ✅ 部门管理 - 部门的增删改查、层级关系
- ✅ 岗位管理 - 岗位的增删改查

### 系统监控

- ✅ 服务器监控 - CPU、内存、JVM、磁盘信息
- ✅ 缓存监控 - Redis 缓存信息
- ✅ 操作日志 - 操作日志记录和查询
- ✅ 登录日志 - 登录日志记录和查询

### 系统工具

- ✅ 代码生成 - 根据表结构生成 CRUD 代码
- ⏳ 系统接口 - Swagger API 文档

### 基础设施

- ✅ API 网关 - 路由转发、负载均衡、限流熔断
- ✅ 认证授权 - 登录认证、Token 管理、权限验证
- ✅ 文件管理 - 文件上传、下载、MinIO 存储
- ✅ 消息管理 - 消息发送、RabbitMQ 集成
- ✅ 搜索服务 - 全文搜索、Elasticsearch 集成
- ✅ 定时任务 - 任务调度、Quartz 集成

## 🔧 配置说明

### 数据库配置

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/apex_admin?useUnicode=true&characterEncoding=utf8
    username: root
    password: root
```

### Redis 配置

```yaml
spring:
  data:
    redis:
      host: 127.0.0.1
      port: 6379
      password:
      database: 0
```

### Nacos 配置

```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
        namespace: apex-admin
```

## 📚 相关文档

- [项目规划文档](docs/project-plan.md)
- [Claude Code 工作流](docs/claude-code-workflow.md)
- [后端 README](backend/README.md)
- [前端 README](frontend/README.md)

## 🤝 参与贡献

1. Fork 本仓库
2. 创建你的开发分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的改动 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交 Pull Request

## 📄 开源协议

本项目采用 [MIT](LICENSE) 协议开源。

## 🙏 致谢

- [RuoYi](http://ruoyi.vip/) - 项目参考
- [Spring Boot](https://spring.io/projects/spring-boot) - 后端框架
- [Vue](https://vuejs.org/) - 前端框架
- [Element Plus](https://element-plus.org/) - UI 组件库
- [Sa-Token](https://sa-token.dev33.cn/) - 权限认证

## 📧 联系方式

- 作者: Apex Admin
- 邮箱: admin@apex.com
- GitHub: [void-frost-craft](https://github.com/void-frost-craft)

---

⭐ 如果这个项目对你有帮助，请给一个 Star 支持一下！
