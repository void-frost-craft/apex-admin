@echo off
REM Apex Admin - SCALE OS 自动化配置脚本 (Windows)
REM 版本：1.0.0
REM 日期：2026-05-30

setlocal enabledelayedexpansion

echo ==========================================
echo    Apex Admin - SCALE OS 自动化配置
echo ==========================================
echo.

REM 步骤 1：检查环境依赖
echo [INFO] 步骤 1：检查环境依赖
echo -----------------------------------

where node >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Node.js 未安装，请先安装 Node.js
    pause
    exit /b 1
)
echo [SUCCESS] Node.js 已安装

where npm >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] npm 未安装，请先安装 npm
    pause
    exit /b 1
)
echo [SUCCESS] npm 已安装

for /f "tokens=1 delims=." %%a in ('node -v') do set NODE_VERSION=%%a
set NODE_VERSION=%NODE_VERSION:v=%
if %NODE_VERSION% lss 16 (
    echo [ERROR] Node.js 版本过低，需要 16+
    pause
    exit /b 1
)
echo [SUCCESS] Node.js 版本符合要求

echo.

REM 步骤 2：安装 SCALE OS
echo [INFO] 步骤 2：安装 SCALE OS
echo -----------------------------------

where scale >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] SCALE OS 已安装
    set /p REINSTALL="是否重新安装？(y/N): "
    if /i "!REINSTALL!"=="y" (
        echo [INFO] 重新安装 SCALE OS...
        call npm install -g @anthropic-ai/scale-os
    )
) else (
    echo [INFO] 安装 SCALE OS...
    call npm install -g @anthropic-ai/scale-os
)

echo [SUCCESS] SCALE OS 安装完成

echo.

REM 步骤 3：初始化项目配置
echo [INFO] 步骤 3：初始化项目配置
echo -----------------------------------

set PROJECT_DIR=E:\code\Claude Code\apex-admin

if exist "%PROJECT_DIR%\.scale" (
    echo [WARNING] SCALE OS 配置目录已存在
    set /p REINIT="是否重新初始化？(y/N): "
    if /i "!REINIT!"=="y" (
        echo [INFO] 重新初始化...
        rmdir /s /q "%PROJECT_DIR%\.scale"
    ) else (
        echo [INFO] 跳过初始化
        goto :skip_init
    )
)

cd /d "%PROJECT_DIR%"
call scale init --project apex-admin
echo [SUCCESS] 项目初始化完成

:skip_init

echo.

REM 步骤 4：配置 AI Agent
echo [INFO] 步骤 4：配置 AI Agent
echo -----------------------------------

cd /d "%PROJECT_DIR%"

scale agent list | findstr "Claude" >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] Claude Agent 已配置
) else (
    echo [INFO] 配置 Claude Agent...
    call scale agent add claude --name "Claude" --provider anthropic --model claude-3-opus-20240229
    echo [SUCCESS] Claude Agent 配置完成
)

echo.

REM 步骤 5：配置技能映射
echo [INFO] 步骤 5：配置技能映射
echo -----------------------------------

REM Java 开发技能
scale skill list | findstr "java-development" >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] Java 开发技能已存在
) else (
    echo [INFO] 添加 Java 开发技能...
    call scale skill add java-development --description "Java 开发技能" --includes "spring-boot,mybatis,jpa,maven,gradle"
    echo [SUCCESS] Java 开发技能添加完成
)

REM 微服务技能
scale skill list | findstr "microservice" >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] 微服务技能已存在
) else (
    echo [INFO] 添加微服务技能...
    call scale skill add microservice --description "微服务开发技能" --includes "spring-cloud,nacos,gateway,sentinel,seata"
    echo [SUCCESS] 微服务技能添加完成
)

REM 测试技能
scale skill list | findstr "testing" >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] 测试技能已存在
) else (
    echo [INFO] 添加测试技能...
    call scale skill add testing --description "测试技能" --includes "junit,mockito,testcontainers,jmeter"
    echo [SUCCESS] 测试技能添加完成
)

REM 运维技能
scale skill list | findstr "devops" >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] 运维技能已存在
) else (
    echo [INFO] 添加运维技能...
    call scale skill add devops --description "运维部署技能" --includes "docker,kubernetes,ci-cd,github-actions"
    echo [SUCCESS] 运维技能添加完成
)

REM 数据库技能
scale skill list | findstr "database" >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] 数据库技能已存在
) else (
    echo [INFO] 添加数据库技能...
    call scale skill add database --description "数据库技能" --includes "mysql,redis,elasticsearch,mybatis-plus"
    echo [SUCCESS] 数据库技能添加完成
)

echo.

REM 步骤 6：配置工作流
echo [INFO] 步骤 6：配置工作流
echo -----------------------------------

REM TDD 工作流
scale workflow list | findstr "tdd-workflow" >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] TDD 工作流已存在
) else (
    echo [INFO] 创建 TDD 工作流...
    call scale workflow create tdd-workflow --description "TDD 驱动开发工作流" --steps "write-test,implement,refactor,run-tests"
    echo [SUCCESS] TDD 工作流创建完成
)

REM 代码审查工作流
scale workflow list | findstr "code-review" >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] 代码审查工作流已存在
) else (
    echo [INFO] 创建代码审查工作流...
    call scale workflow create code-review --description "代码审查工作流" --steps "analyze,review,suggest,approve"
    echo [SUCCESS] 代码审查工作流创建完成
)

REM 部署工作流
scale workflow list | findstr "deploy" >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] 部署工作流已存在
) else (
    echo [INFO] 创建部署工作流...
    call scale workflow create deploy --description "部署上线工作流" --steps "build,test,deploy,verify"
    echo [SUCCESS] 部署工作流创建完成
)

echo.

REM 步骤 7：创建配置文件
echo [INFO] 步骤 7：创建配置文件
echo -----------------------------------

(
echo {
echo   "project": {
echo     "name": "Apex Admin",
echo     "description": "企业级后台管理系统",
echo     "version": "1.0.0",
echo     "techStack": {
echo       "backend": "Spring Boot 3",
echo       "frontend": "Vue 3",
echo       "database": "MySQL 8.0",
echo       "cache": "Redis + Caffeine",
echo       "microservice": "Spring Cloud Alibaba"
echo     }
echo   },
echo   "agents": {
echo     "claude": {
echo       "name": "Claude",
echo       "provider": "anthropic",
echo       "model": "claude-3-opus-20240229",
echo       "enabled": true
echo     }
echo   },
echo   "skills": {
echo     "java-development": {
echo       "enabled": true,
echo       "priority": 1
echo     },
echo     "microservice": {
echo       "enabled": true,
echo       "priority": 2
echo     },
echo     "testing": {
echo       "enabled": true,
echo       "priority": 3
echo     },
echo     "devops": {
echo       "enabled": true,
echo       "priority": 4
echo     },
echo     "database": {
echo       "enabled": true,
echo       "priority": 5
echo     }
echo   },
echo   "workflows": {
echo     "tdd-workflow": {
echo       "enabled": true,
echo       "autoTrigger": true
echo     },
echo     "code-review": {
echo       "enabled": true,
echo       "autoTrigger": false
echo     },
echo     "deploy": {
echo       "enabled": true,
echo       "autoTrigger": false
echo     }
echo   }
echo }
) > "%PROJECT_DIR%\.scale\config.json"

echo [SUCCESS] 主配置文件创建完成

echo.

REM 步骤 8：验证配置
echo [INFO] 步骤 8：验证配置
echo -----------------------------------

cd /d "%PROJECT_DIR%"

REM 检查 SCALE OS 状态
call scale status
if %errorlevel% equ 0 (
    echo [SUCCESS] SCALE OS 状态正常
) else (
    echo [WARNING] SCALE OS 状态异常，请检查配置
)

REM 列出配置的 Agent
echo [INFO] 已配置的 Agent：
call scale agent list

REM 列出配置的技能
echo [INFO] 已配置的技能：
call scale skill list

REM 列出配置的工作流
echo [INFO] 已配置的工作流：
call scale workflow list

echo.

REM 完成
echo ==========================================
echo    SCALE OS 配置完成！
echo ==========================================
echo.
echo 项目目录：%PROJECT_DIR%
echo 配置目录：%PROJECT_DIR%\.scale
echo.
echo 下一步：
echo 1. 启动 SCALE OS：scale start
echo 2. 与 Claude 对话：scale chat claude "你好"
echo 3. 查看状态：scale status
echo 4. 查看日志：scale logs
echo.
echo 常用命令：
echo - scale chat claude "提示词"  # 与 Claude 对话
echo - scale generate claude --skill java-development --template controller --entity User  # 生成代码
echo - scale workflow run tdd-workflow --task "任务描述" --agent claude  # 执行工作流
echo.
echo 文档：
echo - 项目规划：docs\project-plan.md
echo - SCALE OS 指南：docs\scale-os-guide.md
echo.

pause
