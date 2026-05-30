# Apex Admin 环境搭建指南

本文档详细介绍如何搭建 Apex Admin 项目的开发环境，包括所有依赖软件的安装和配置。

## 📋 环境要求

| 软件 | 版本要求 | 说明 |
|------|----------|------|
| JDK | 17+ | Java 开发环境 |
| Node.js | 18+ | 前端运行环境 |
| Maven | 3.8+ | Java 构建工具 |
| MySQL | 8.0+ | 数据库 |
| Redis | 7.0+ | 缓存 |
| Nacos | 2.3+ | 服务注册发现 |
| Sentinel | 1.8+ | 流量控制 |
| RabbitMQ | 3.12+ | 消息队列 |
| Elasticsearch | 8.x | 搜索引擎 |
| MinIO | 最新版 | 对象存储 |

---

## 1. JDK 17 安装

### Windows

1. **下载 JDK 17**
   - 访问 https://adoptium.net/
   - 下载 Windows x64 `.msi` 安装包

2. **安装**
   - 双击安装包，按提示完成安装
   - 默认安装路径：`C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot`

3. **配置环境变量**
   ```
   JAVA_HOME = C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot
   Path 添加 = %JAVA_HOME%\bin
   ```

4. **验证安装**
   ```bash
   java -version
   # 输出: openjdk version "17.x.x"
   ```

### macOS

```bash
# 使用 Homebrew
brew install openjdk@17

# 配置环境变量
echo 'export JAVA_HOME=/usr/local/opt/openjdk@17' >> ~/.zshrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.zshrc
source ~/.zshrc
```

### Linux (Ubuntu/Debian)

```bash
sudo apt update
sudo apt install openjdk-17-jdk

# 验证
java -version
```

---

## 2. Maven 安装

### Windows

1. **下载 Maven**
   - 访问 https://maven.apache.org/download.cgi
   - 下载 `apache-maven-3.9.x-bin.zip`

2. **解压并配置**
   ```
   解压到: C:\Program Files\Apache\maven
   
   环境变量:
   MAVEN_HOME = C:\Program Files\Apache\maven\apache-maven-3.9.x
   Path 添加 = %MAVEN_HOME%\bin
   ```

3. **配置镜像加速**
   
   编辑 `C:\Users\{用户名}\.m2\settings.xml`：
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <settings>
     <mirrors>
       <mirror>
         <id>aliyun</id>
         <name>Aliyun Maven</name>
         <url>https://maven.aliyun.com/repository/public</url>
         <mirrorOf>central</mirrorOf>
       </mirror>
     </mirrors>
   </settings>
   ```

4. **验证安装**
   ```bash
   mvn -version
   ```

### macOS/Linux

```bash
# macOS
brew install maven

# Linux
sudo apt install maven
```

---

## 3. Node.js 安装

### Windows

1. **下载 Node.js**
   - 访问 https://nodejs.org/
   - 下载 LTS 版本 (18.x 或 20.x)

2. **安装**
   - 双击安装包，按提示完成安装
   - 勾选 "Automatically install the necessary tools"

3. **配置 npm 镜像**
   ```bash
   npm config set registry https://registry.npmmirror.com
   ```

4. **验证安装**
   ```bash
   node -v
   npm -v
   ```

### macOS/Linux

```bash
# macOS
brew install node

# Linux (使用 nvm)
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash
nvm install 18
nvm use 18
```

---

## 4. MySQL 8.0 安装

### Windows

1. **下载 MySQL**
   - 访问 https://dev.mysql.com/downloads/mysql/
   - 下载 MySQL Installer

2. **安装**
   - 选择 "Developer Default" 或 "Server only"
   - 设置 root 密码（建议使用 `root`）
   - 端口保持默认 `3306`

3. **配置字符集**
   
   编辑 `C:\ProgramData\MySQL\MySQL Server 8.0\my.ini`：
   ```ini
   [mysqld]
   character-set-server=utf8mb4
   collation-server=utf8mb4_general_ci
   
   [client]
   default-character-set=utf8mb4
   ```

4. **创建数据库**
   ```bash
   # 登录 MySQL
   mysql -u root -p
   
   # 创建数据库
   CREATE DATABASE apex_admin DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
   
   # 导入初始化脚本
   USE apex_admin;
   SOURCE E:/code/Claude-Code/apex-admin/backend/sql/apex_admin.sql;
   ```

### macOS

```bash
brew install mysql@8.0
brew services start mysql@8.0
mysql_secure_installation
```

### Linux

```bash
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
sudo systemctl enable mysql
sudo mysql_secure_installation
```

### Docker 方式

```bash
docker run -d \
  --name mysql \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=apex_admin \
  -v mysql_data:/var/lib/mysql \
  mysql:8.0 \
  --character-set-server=utf8mb4 \
  --collation-server=utf8mb4_general_ci
```

---

## 5. Redis 安装

### Windows

1. **下载 Redis**
   - 访问 https://github.com/tporadowski/redis/releases
   - 下载 `.msi` 安装包

2. **安装**
   - 双击安装包，按提示完成安装
   - 端口保持默认 `6379`
   - 建议设置为 Windows 服务

3. **验证安装**
   ```bash
   redis-cli ping
   # 输出: PONG
   ```

### macOS

```bash
brew install redis
brew services start redis
```

### Linux

```bash
sudo apt update
sudo apt install redis-server
sudo systemctl start redis
sudo systemctl enable redis
```

### Docker 方式

```bash
docker run -d \
  --name redis \
  -p 6379:6379 \
  redis:7-alpine
```

---

## 6. Nacos 安装

### 下载安装

1. **下载 Nacos**
   - 访问 https://github.com/alibaba/nacos/releases
   - 下载 `nacos-server-2.3.x.zip`

2. **解压**
   ```
   解压到: C:\software\nacos
   ```

3. **配置数据库**
   
   编辑 `conf/application.properties`：
   ```properties
   spring.datasource.platform=mysql
   db.num=1
   db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
   db.user.0=root
   db.password.0=root
   ```

4. **初始化数据库**
   ```bash
   mysql -u root -p < conf/mysql-schema.sql
   ```

5. **启动服务**
   ```bash
   # Windows
   bin\startup.cmd -m standalone
   
   # Linux/Mac
   sh bin/startup.sh -m standalone
   ```

6. **访问控制台**
   - 地址：http://127.0.0.1:8848/nacos
   - 账号：nacos
   - 密码：nacos

### Docker 方式

```bash
docker run -d \
  --name nacos \
  -p 8848:8848 \
  -p 9848:9848 \
  -e MODE=standalone \
  -e SPRING_DATASOURCE_PLATFORM=mysql \
  -e MYSQL_SERVICE_HOST=127.0.0.1 \
  -e MYSQL_SERVICE_PORT=3306 \
  -e MYSQL_SERVICE_DB_NAME=nacos \
  -e MYSQL_SERVICE_USER=root \
  -e MYSQL_SERVICE_PASSWORD=root \
  nacos/nacos-server:v2.3.0
```

---

## 7. Sentinel 安装

### 下载安装

1. **下载 Sentinel Dashboard**
   - 访问 https://github.com/alibaba/Sentinel/releases
   - 下载 `sentinel-dashboard-1.8.x.jar`

2. **启动服务**
   ```bash
   java -jar sentinel-dashboard-1.8.x.jar
   ```

3. **访问控制台**
   - 地址：http://127.0.0.1:8858
   - 账号：sentinel
   - 密码：sentinel

### Docker 方式

```bash
docker run -d \
  --name sentinel \
  -p 8858:8858 \
  bladex/sentinel-dashboard:1.8.7
```

---

## 8. RabbitMQ 安装

### Windows

1. **安装 Erlang**
   - 访问 https://www.erlang.org/downloads
   - 下载并安装 Erlang

2. **下载 RabbitMQ**
   - 访问 https://rabbitmq.com/install-windows.html
   - 下载 RabbitMQ Installer

3. **安装**
   - 双击安装包，按提示完成安装

4. **启用管理插件**
   ```bash
   rabbitmq-plugins enable rabbitmq_management
   ```

5. **访问控制台**
   - 地址：http://127.0.0.1:15672
   - 账号：guest
   - 密码：guest

### macOS

```bash
brew install rabbitmq
brew services start rabbitmq
rabbitmq-plugins enable rabbitmq_management
```

### Linux

```bash
# 安装 Erlang
sudo apt install erlang

# 安装 RabbitMQ
sudo apt install rabbitmq-server
sudo systemctl start rabbitmq-server
sudo systemctl enable rabbitmq-server

# 启用管理插件
sudo rabbitmq-plugins enable rabbitmq_management
```

### Docker 方式

```bash
docker run -d \
  --name rabbitmq \
  -p 5672:5672 \
  -p 15672:15672 \
  rabbitmq:3-management
```

---

## 9. Elasticsearch 安装

### 下载安装

1. **下载 Elasticsearch**
   - 访问 https://www.elastic.co/downloads/elasticsearch
   - 下载对应系统版本

2. **解压**
   ```
   解压到: C:\software\elasticsearch
   ```

3. **配置**
   
   编辑 `config/elasticsearch.yml`：
   ```yaml
   cluster.name: apex-admin
   network.host: 0.0.0.0
   http.port: 9200
   xpack.security.enabled: false
   ```

4. **启动服务**
   ```bash
   # Windows
   bin\elasticsearch.bat
   
   # Linux/Mac
   ./bin/elasticsearch
   ```

5. **验证安装**
   ```bash
   curl http://127.0.0.1:9200
   ```

### Docker 方式

```bash
docker run -d \
  --name elasticsearch \
  -p 9200:9200 \
  -p 9300:9300 \
  -e "discovery.type=single-node" \
  -e "xpack.security.enabled=false" \
  -v es_data:/usr/share/elasticsearch/data \
  elasticsearch:8.13.0
```

---

## 10. MinIO 安装

### 下载安装

1. **下载 MinIO**
   - 访问 https://min.io/download
   - 下载对应系统版本

2. **启动服务**
   ```bash
   # Windows
   minio.exe server C:\data\minio
   
   # Linux/Mac
   ./minio server /data/minio
   ```

3. **访问控制台**
   - 地址：http://127.0.0.1:9001
   - 账号：minioadmin
   - 密码：minioadmin

4. **创建 Bucket**
   - 登录控制台
   - 点击 "Create Bucket"
   - 输入名称：`apex-admin`

### Docker 方式

```bash
docker run -d \
  --name minio \
  -p 9000:9000 \
  -p 9001:9001 \
  -e MINIO_ROOT_USER=minioadmin \
  -e MINIO_ROOT_PASSWORD=minioadmin \
  -v minio_data:/data \
  minio/minio server /data --console-address ":9001"
```

---

## 11. 后端项目启动

### 1. 导入数据库

```bash
# 登录 MySQL
mysql -u root -p

# 创建数据库
CREATE DATABASE apex_admin DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE nacos DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

# 导入 Apex Admin 数据库
USE apex_admin;
SOURCE E:/code/Claude-Code/apex-admin/backend/sql/apex_admin.sql;

# 导入 Nacos 数据库
USE nacos;
SOURCE C:/software/nacos/conf/mysql-schema.sql;
```

### 2. 修改配置

修改各模块的 `application.yml` 文件中的数据库连接信息。

### 3. 启动服务（按顺序）

```bash
# 1. 启动网关服务
cd backend/apex-gateway
mvn spring-boot:run

# 2. 启动认证服务
cd backend/apex-auth
mvn spring-boot:run

# 3. 启动系统管理服务
cd backend/apex-system
mvn spring-boot:run

# 4. 启动其他服务...
```

---

## 12. 前端项目启动

```bash
# 进入前端目录
cd frontend

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

访问地址：http://localhost:3000

默认账号：admin / admin123

---

## 13. 常见问题

### Q1: MySQL 连接失败

**原因**: MySQL 服务未启动或密码错误

**解决**:
```bash
# 检查 MySQL 服务状态
sudo systemctl status mysql

# 重启 MySQL
sudo systemctl restart mysql
```

### Q2: Redis 连接失败

**原因**: Redis 服务未启动

**解决**:
```bash
# 检查 Redis 服务
redis-cli ping

# 启动 Redis
redis-server
```

### Q3: Nacos 启动失败

**原因**: 端口被占用或数据库配置错误

**解决**:
```bash
# 检查端口占用
netstat -ano | findstr 8848

# 检查数据库连接
mysql -u root -p -h 127.0.0.1 -P 3306
```

### Q4: RabbitMQ 连接失败

**原因**: Erlang 未安装或 RabbitMQ 服务未启动

**解决**:
```bash
# 检查 RabbitMQ 状态
rabbitmqctl status

# 启动 RabbitMQ
rabbitmq-server start
```

### Q5: Elasticsearch 启动失败

**原因**: 内存不足或 Java 版本不兼容

**解决**:
```bash
# 修改 JVM 内存
export ES_JAVA_OPTS="-Xms512m -Xmx512m"

# 检查 Java 版本
java -version
```

---

## 14. 服务端口汇总

| 服务 | 端口 | 控制台地址 |
|------|------|-----------|
| 前端 | 3000 | http://localhost:3000 |
| API 网关 | 8080 | - |
| 认证服务 | 8100 | - |
| 系统管理 | 8200 | - |
| 日志管理 | 8300 | - |
| 定时任务 | 8400 | - |
| 文件服务 | 8500 | - |
| 消息服务 | 8600 | - |
| 搜索服务 | 8700 | - |
| 监控服务 | 8800 | - |
| 代码生成 | 8900 | - |
| MySQL | 3306 | - |
| Redis | 6379 | - |
| Nacos | 8848 | http://localhost:8848/nacos |
| Sentinel | 8858 | http://localhost:8858 |
| RabbitMQ | 5672 | http://localhost:15672 |
| Elasticsearch | 9200 | - |
| MinIO | 9000 | http://localhost:9001 |

---

## 15. Docker Compose 一键部署

创建 `docker-compose.yml`：

```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: apex-mysql
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: apex_admin
    volumes:
      - mysql_data:/var/lib/mysql
      - ./backend/sql:/docker-entrypoint-initdb.d
    command: --character-set-server=utf8mb4 --collation-server=utf8mb4_general_ci

  redis:
    image: redis:7-alpine
    container_name: apex-redis
    ports:
      - "6379:6379"

  nacos:
    image: nacos/nacos-server:v2.3.0
    container_name: apex-nacos
    ports:
      - "8848:8848"
      - "9848:9848"
    environment:
      MODE: standalone
      SPRING_DATASOURCE_PLATFORM: mysql
      MYSQL_SERVICE_HOST: mysql
      MYSQL_SERVICE_PORT: 3306
      MYSQL_SERVICE_DB_NAME: nacos
      MYSQL_SERVICE_USER: root
      MYSQL_SERVICE_PASSWORD: root
    depends_on:
      - mysql

  rabbitmq:
    image: rabbitmq:3-management
    container_name: apex-rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"

  elasticsearch:
    image: elasticsearch:8.13.0
    container_name: apex-elasticsearch
    ports:
      - "9200:9200"
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false
    volumes:
      - es_data:/usr/share/elasticsearch/data

  minio:
    image: minio/minio
    container_name: apex-minio
    ports:
      - "9000:9000"
      - "9001:9001"
    environment:
      MINIO_ROOT_USER: minioadmin
      MINIO_ROOT_PASSWORD: minioadmin
    volumes:
      - minio_data:/data
    command: server /data --console-address ":9001"

volumes:
  mysql_data:
  es_data:
  minio_data:
```

启动所有服务：

```bash
docker-compose up -d
```

---

## 📞 技术支持

如有问题，请提交 Issue：https://github.com/void-frost-craft/apex-admin/issues
