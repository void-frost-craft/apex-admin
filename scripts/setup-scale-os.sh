#!/bin/bash

# Apex Admin - SCALE OS 自动化配置脚本
# 版本：1.0.0
# 日期：2026-05-30

set -e  # 遇到错误立即退出

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# 日志函数
log_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

log_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

log_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

log_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# 检查命令是否存在
check_command() {
    if ! command -v "$1" &> /dev/null; then
        log_error "$1 未安装，请先安装 $1"
        return 1
    fi
    log_success "$1 已安装"
    return 0
}

# 检查 Node.js 版本
check_node_version() {
    local node_version=$(node -v | cut -d'v' -f2 | cut -d'.' -f1)
    if [ "$node_version" -lt 16 ]; then
        log_error "Node.js 版本过低，需要 16+，当前版本：$(node -v)"
        return 1
    fi
    log_success "Node.js 版本符合要求：$(node -v)"
    return 0
}

# 主函数
main() {
    echo "=========================================="
    echo "   Apex Admin - SCALE OS 自动化配置"
    echo "=========================================="
    echo ""

    # 步骤 1：检查环境依赖
    log_info "步骤 1：检查环境依赖"
    echo "-----------------------------------"

    check_command "node" || exit 1
    check_command "npm" || exit 1
    check_node_version || exit 1

    echo ""

    # 步骤 2：安装 SCALE OS
    log_info "步骤 2：安装 SCALE OS"
    echo "-----------------------------------"

    if command -v scale &> /dev/null; then
        log_warning "SCALE OS 已安装，版本：$(scale --version)"
        read -p "是否重新安装？(y/N): " -n 1 -r
        echo
        if [[ $REPLY =~ ^[Yy]$ ]]; then
            log_info "重新安装 SCALE OS..."
            npm install -g @anthropic-ai/scale-os
        fi
    else
        log_info "安装 SCALE OS..."
        npm install -g @anthropic-ai/scale-os
    fi

    log_success "SCALE OS 安装完成"

    echo ""

    # 步骤 3：初始化项目配置
    log_info "步骤 3：初始化项目配置"
    echo "-----------------------------------"

    PROJECT_DIR="E:/code/Claude Code/apex-admin"

    if [ -d "$PROJECT_DIR/.scale" ]; then
        log_warning "SCALE OS 配置目录已存在"
        read -p "是否重新初始化？(y/N): " -n 1 -r
        echo
        if [[ $REPLY =~ ^[Yy]$ ]]; then
            log_info "重新初始化..."
            rm -rf "$PROJECT_DIR/.scale"
        else
            log_info "跳过初始化"
        fi
    fi

    if [ ! -d "$PROJECT_DIR/.scale" ]; then
        cd "$PROJECT_DIR"
        scale init --project apex-admin
        log_success "项目初始化完成"
    fi

    echo ""

    # 步骤 4：配置 AI Agent
    log_info "步骤 4：配置 AI Agent"
    echo "-----------------------------------"

    cd "$PROJECT_DIR"

    # 检查是否已配置 Claude
    if scale agent list | grep -q "Claude"; then
        log_warning "Claude Agent 已配置"
    else
        log_info "配置 Claude Agent..."
        scale agent add claude \
            --name "Claude" \
            --provider anthropic \
            --model claude-3-opus-20240229
        log_success "Claude Agent 配置完成"
    fi

    echo ""

    # 步骤 5：配置技能映射
    log_info "步骤 5：配置技能映射"
    echo "-----------------------------------"

    # Java 开发技能
    if ! scale skill list | grep -q "java-development"; then
        log_info "添加 Java 开发技能..."
        scale skill add java-development \
            --description "Java 开发技能" \
            --includes "spring-boot,mybatis,jpa,maven,gradle"
        log_success "Java 开发技能添加完成"
    else
        log_warning "Java 开发技能已存在"
    fi

    # 微服务技能
    if ! scale skill list | grep -q "microservice"; then
        log_info "添加微服务技能..."
        scale skill add microservice \
            --description "微服务开发技能" \
            --includes "spring-cloud,nacos,gateway,sentinel,seata"
        log_success "微服务技能添加完成"
    else
        log_warning "微服务技能已存在"
    fi

    # 测试技能
    if ! scale skill list | grep -q "testing"; then
        log_info "添加测试技能..."
        scale skill add testing \
            --description "测试技能" \
            --includes "junit,mockito,testcontainers,jmeter"
        log_success "测试技能添加完成"
    else
        log_warning "测试技能已存在"
    fi

    # 运维技能
    if ! scale skill list | grep -q "devops"; then
        log_info "添加运维技能..."
        scale skill add devops \
            --description "运维部署技能" \
            --includes "docker,kubernetes,ci-cd,github-actions"
        log_success "运维技能添加完成"
    else
        log_warning "运维技能已存在"
    fi

    # 数据库技能
    if ! scale skill list | grep -q "database"; then
        log_info "添加数据库技能..."
        scale skill add database \
            --description "数据库技能" \
            --includes "mysql,redis,elasticsearch,mybatis-plus"
        log_success "数据库技能添加完成"
    else
        log_warning "数据库技能已存在"
    fi

    echo ""

    # 步骤 6：配置工作流
    log_info "步骤 6：配置工作流"
    echo "-----------------------------------"

    # TDD 工作流
    if ! scale workflow list | grep -q "tdd-workflow"; then
        log_info "创建 TDD 工作流..."
        scale workflow create tdd-workflow \
            --description "TDD 驱动开发工作流" \
            --steps "write-test,implement,refactor,run-tests"
        log_success "TDD 工作流创建完成"
    else
        log_warning "TDD 工作流已存在"
    fi

    # 代码审查工作流
    if ! scale workflow list | grep -q "code-review"; then
        log_info "创建代码审查工作流..."
        scale workflow create code-review \
            --description "代码审查工作流" \
            --steps "analyze,review,suggest,approve"
        log_success "代码审查工作流创建完成"
    else
        log_warning "代码审查工作流已存在"
    fi

    # 部署工作流
    if ! scale workflow list | grep -q "deploy"; then
        log_info "创建部署工作流..."
        scale workflow create deploy \
            --description "部署上线工作流" \
            --steps "build,test,deploy,verify"
        log_success "部署工作流创建完成"
    else
        log_warning "部署工作流已存在"
    fi

    echo ""

    # 步骤 7：创建配置文件
    log_info "步骤 7：创建配置文件"
    echo "-----------------------------------"

    # 创建主配置文件
    cat > "$PROJECT_DIR/.scale/config.json" << 'EOF'
{
  "project": {
    "name": "Apex Admin",
    "description": "企业级后台管理系统",
    "version": "1.0.0",
    "techStack": {
      "backend": "Spring Boot 3",
      "frontend": "Vue 3",
      "database": "MySQL 8.0",
      "cache": "Redis + Caffeine",
      "microservice": "Spring Cloud Alibaba"
    }
  },
  "agents": {
    "claude": {
      "name": "Claude",
      "provider": "anthropic",
      "model": "claude-3-opus-20240229",
      "enabled": true
    }
  },
  "skills": {
    "java-development": {
      "enabled": true,
      "priority": 1
    },
    "microservice": {
      "enabled": true,
      "priority": 2
    },
    "testing": {
      "enabled": true,
      "priority": 3
    },
    "devops": {
      "enabled": true,
      "priority": 4
    },
    "database": {
      "enabled": true,
      "priority": 5
    }
  },
  "workflows": {
    "tdd-workflow": {
      "enabled": true,
      "autoTrigger": true
    },
    "code-review": {
      "enabled": true,
      "autoTrigger": false
    },
    "deploy": {
      "enabled": true,
      "autoTrigger": false
    }
  }
}
EOF

    log_success "主配置文件创建完成"

    echo ""

    # 步骤 8：验证配置
    log_info "步骤 8：验证配置"
    echo "-----------------------------------"

    cd "$PROJECT_DIR"

    # 检查 SCALE OS 状态
    if scale status; then
        log_success "SCALE OS 状态正常"
    else
        log_warning "SCALE OS 状态异常，请检查配置"
    fi

    # 列出配置的 Agent
    log_info "已配置的 Agent："
    scale agent list

    # 列出配置的技能
    log_info "已配置的技能："
    scale skill list

    # 列出配置的工作流
    log_info "已配置的工作流："
    scale workflow list

    echo ""

    # 完成
    echo "=========================================="
    echo "   SCALE OS 配置完成！"
    echo "=========================================="
    echo ""
    echo "项目目录：$PROJECT_DIR"
    echo "配置目录：$PROJECT_DIR/.scale"
    echo ""
    echo "下一步："
    echo "1. 启动 SCALE OS：scale start"
    echo "2. 与 Claude 对话：scale chat claude \"你好\""
    echo "3. 查看状态：scale status"
    echo "4. 查看日志：scale logs"
    echo ""
    echo "常用命令："
    echo "- scale chat claude \"提示词\"  # 与 Claude 对话"
    echo "- scale generate claude --skill java-development --template controller --entity User  # 生成代码"
    echo "- scale workflow run tdd-workflow --task \"任务描述\" --agent claude  # 执行工作流"
    echo ""
    echo "文档："
    echo "- 项目规划：docs/project-plan.md"
    echo "- SCALE OS 指南：docs/scale-os-guide.md"
    echo ""
}

# 执行主函数
main "$@"
