<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <template #header>
            <div class="card-header">
              <span>用户数量</span>
              <el-icon><User /></el-icon>
            </div>
          </template>
          <div class="stat-value">1,234</div>
          <div class="stat-label">总用户数</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <template #header>
            <div class="card-header">
              <span>今日访问</span>
              <el-icon><View /></el-icon>
            </div>
          </template>
          <div class="stat-value">5,678</div>
          <div class="stat-label">今日访问量</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <template #header>
            <div class="card-header">
              <span>订单数量</span>
              <el-icon><ShoppingCart /></el-icon>
            </div>
          </template>
          <div class="stat-value">890</div>
          <div class="stat-label">本月订单</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <template #header>
            <div class="card-header">
              <span>收入金额</span>
              <el-icon><Money /></el-icon>
            </div>
          </template>
          <div class="stat-value">¥123,456</div>
          <div class="stat-label">本月收入</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="mt-20">
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>快捷操作</span>
          </template>
          <el-row :gutter="10">
            <el-col :span="6" v-for="item in shortcuts" :key="item.path">
              <el-button class="shortcut-btn" @click="$router.push(item.path)">
                <el-icon><component :is="item.icon" /></el-icon>
                <span>{{ item.title }}</span>
              </el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>系统信息</span>
          </template>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="系统版本">Apex Admin v1.0.0</el-descriptions-item>
            <el-descriptions-item label="Spring Boot">3.2.5</el-descriptions-item>
            <el-descriptions-item label="Vue 版本">3.4.21</el-descriptions-item>
            <el-descriptions-item label="Element Plus">2.7.3</el-descriptions-item>
            <el-descriptions-item label="数据库">MySQL 8.0</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
const shortcuts = [
  { title: '用户管理', path: '/system/user', icon: 'User' },
  { title: '角色管理', path: '/system/role', icon: 'UserFilled' },
  { title: '菜单管理', path: '/system/menu', icon: 'Menu' },
  { title: '部门管理', path: '/system/dept', icon: 'OfficeBuilding' }
]
</script>

<style lang="scss" scoped>
.stat-card {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .stat-value {
    font-size: 28px;
    font-weight: bold;
    color: #409EFF;
    text-align: center;
    margin: 10px 0;
  }

  .stat-label {
    text-align: center;
    color: #909399;
    font-size: 14px;
  }
}

.mt-20 {
  margin-top: 20px;
}

.shortcut-btn {
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  .el-icon {
    font-size: 24px;
    margin-bottom: 5px;
  }

  span {
    font-size: 12px;
  }
}
</style>
