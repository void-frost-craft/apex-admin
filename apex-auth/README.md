# Apex Auth - 认证授权服务

## 概述

认证授权服务负责用户身份认证、Token 管理、权限验证等安全相关功能。

## 端口

- 服务端口：**8100**

## 功能特性

### 1. 用户登录
- 用户名/密码登录
- 验证码验证（可选）
- Sa-Token 生成 Token
- 登录日志记录

### 2. 用户注册
- 用户名唯一性检查
- 密码强度验证
- 邮箱/手机号验证
- 注册信息保存

### 3. Token 管理
- Token 生成与刷新
- Token 有效期管理
- Token 主动失效
- 多端登录控制

### 4. 权限验证
- 用户权限查询
- 角色权限查询
- 接口权限拦截
- 数据权限控制

## API 接口

### 登录接口

```http
POST /auth/login
Content-Type: application/json

{
    "username": "admin",
    "password": "admin123",
    "captcha": "1234",
    "uuid": "captcha-uuid"
}
```

**响应示例：**
```json
{
    "code": 200,
    "msg": "登录成功",
    "data": {
        "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
        "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
        "tokenType": "Bearer",
        "expiresIn": 2592000,
        "userId": 1,
        "username": "admin",
        "nickname": "超级管理员",
        "avatar": "",
        "email": "admin@apex.com",
        "phone": "15888888888",
        "sex": 0,
        "deptName": "研发部门",
        "roles": ["admin"],
        "permissions": ["system:user:list", "system:user:add", ...]
    }
}
```

### 注册接口

```http
POST /auth/register
Content-Type: application/json

{
    "username": "newuser",
    "password": "password123",
    "confirmPassword": "password123",
    "nickname": "新用户",
    "email": "user@example.com",
    "phone": "13800138000",
    "captcha": "1234",
    "uuid": "captcha-uuid"
}
```

### 退出登录

```http
POST /auth/logout
Authorization: Bearer {token}
```

### 刷新 Token

```http
POST /auth/refresh
Authorization: Bearer {token}
```

### 获取用户信息

```http
GET /auth/info
Authorization: Bearer {token}
```

### 修改密码

```http
PUT /auth/password
Authorization: Bearer {token}
Content-Type: application/x-www-form-urlencoded

oldPassword=old123&password=new123
```

## 技术实现

### Sa-Token 集成

使用 Sa-Token 作为权限认证框架，主要特性：

- 轻量级，简单易用
- 支持多种认证模式
- 支持分布式 Session
- 支持注解式权限控制

### Token 存储

Token 存储在 Redis 中，支持：

- Token 有效期管理
- Token 主动踢下线
- 多端登录控制
- Token 并发登录控制

### 密码安全

密码使用 BCrypt 加密存储：

- 不可逆加密
- 盐值随机生成
- 抗彩虹表攻击

## 配置说明

### Sa-Token 配置

```yaml
sa-token:
  token-name: Authorization
  timeout: 2592000  # Token 有效期 30天
  active-timeout: -1
  is-concurrent: true
  is-share: true
  token-style: uuid
  is-log: false
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

## 依赖服务

- **Redis**: Token 存储、缓存
- **Nacos**: 服务注册发现、配置中心
- **System Service**: 用户信息查询（Feign 调用）

## 启动命令

```bash
# 开发环境
java -jar apex-auth.jar --spring.profiles.active=dev

# 生产环境
java -jar apex-auth.jar --spring.profiles.active=prod
```

## 注意事项

1. 生产环境需要修改默认密码
2. 建议开启验证码功能
3. 定期清理过期 Token
4. 监控登录失败次数，防止暴力破解
5. 敏感操作需要二次验证
