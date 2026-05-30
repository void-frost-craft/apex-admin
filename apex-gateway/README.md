# Apex Gateway - API 网关服务

## 概述

API 网关服务是 Apex Admin 的入口，负责请求路由、负载均衡、认证鉴权、限流熔断、跨域处理等网关功能。

## 端口

- 服务端口：**8080**

## 功能特性

### 1. 路由转发
- 自动服务发现（Nacos）
- 路由规则配置
- 路径重写
- 负载均衡

### 2. 认证鉴权
- Sa-Token 集成
- Token 验证
- 登录状态检查
- 白名单路由

### 3. 限流熔断
- Sentinel 集成
- 路由级限流
- 服务级熔断
- 降级处理

### 4. 跨域处理
- CORS 配置
- 预检请求处理
- 自定义头支持

### 5. 请求过滤
- IP 黑白名单
- 请求日志记录
- 慢请求监控
- 异常处理

## API 路由

### 路由规则

| 服务 | 路径 | 端口 | 说明 |
|------|------|------|------|
| apex-auth | /auth/** | 8100 | 认证授权 |
| apex-system | /system/** | 8200 | 系统管理 |
| apex-log | /log/** | 8300 | 日志管理 |
| apex-job | /job/** | 8400 | 定时任务 |
| apex-file | /file/** | 8500 | 文件服务 |
| apex-message | /message/** | 8600 | 消息服务 |
| apex-search | /search/** | 8700 | 搜索服务 |
| apex-monitor | /monitor/** | 8800 | 监控服务 |
| apex-generator | /generator/** | 8900 | 代码生成 |

### 请求示例

```http
# 认证服务
POST http://localhost:8080/auth/login
{
    "username": "admin",
    "password": "admin123"
}

# 系统管理服务
GET http://localhost:8080/system/user/list
Authorization: Bearer {token}

# 文件服务
POST http://localhost:8080/file/upload
Content-Type: multipart/form-data
```

## 技术实现

### Spring Cloud Gateway

使用 Spring Cloud Gateway 作为网关框架：

- 基于 WebFlux 响应式编程
- 高性能、非阻塞
- 支持 WebSocket
- 支持限流、熔断

### Sa-Token 集成

使用 Sa-Token 进行认证鉴权：

- Token 验证
- 登录状态检查
- 权限验证
- 角色验证

### Sentinel 集成

使用 Sentinel 进行限流熔断：

- 路由级限流
- 服务级熔断
- 降级处理
- 流控规则动态配置

## 配置说明

### 路由配置

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: apex-auth
          uri: lb://apex-auth
          predicates:
            - Path=/auth/**
          filters:
            - StripPrefix=1
```

### Sentinel 配置

```yaml
spring:
  cloud:
    sentinel:
      transport:
        dashboard: 127.0.0.1:8858
```

### Sa-Token 配置

```yaml
sa-token:
  token-name: Authorization
  timeout: 2592000
  is-concurrent: true
  is-share: true
  token-style: uuid
```

## 过滤器链

### 执行顺序

1. **IpFilter** (-200) - IP 黑白名单检查
2. **RequestFilter** (-100) - 请求日志记录
3. **SentinelGatewayFilter** (-1) - Sentinel 限流
4. **SaReactorFilter** (默认) - Sa-Token 认证
5. **GatewayFilter** (默认) - 路由转发

### 过滤器说明

#### IpFilter
- IP 黑白名单检查
- 添加真实 IP 到请求头
- 支持 X-Forwarded-For 解析

#### RequestFilter
- 请求日志记录
- 请求耗时统计
- 慢请求告警

#### AuthFilter
- Sa-Token 认证验证
- 白名单路由排除
- 异常处理

## 依赖服务

- **Nacos**: 服务注册发现、配置中心
- **Sentinel**: 限流熔断
- **Redis**: Token 存储、缓存

## 启动命令

```bash
# 开发环境
java -jar apex-gateway.jar --spring.profiles.active=dev

# 生产环境
java -jar apex-gateway.jar --spring.profiles.active=prod
```

## 注意事项

1. 网关服务需要先于其他服务启动
2. 确保 Nacos 服务正常运行
3. 生产环境需要配置 IP 白名单
4. 建议开启 Sentinel 限流保护
5. 监控网关性能指标
