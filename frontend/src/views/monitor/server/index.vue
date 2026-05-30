<template>
  <div class="server-container">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>CPU 信息</span>
          </template>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="核心数">4</el-descriptions-item>
            <el-descriptions-item label="用户使用率">20%</el-descriptions-item>
            <el-descriptions-item label="系统使用率">10%</el-descriptions-item>
            <el-descriptions-item label="当前空闲率">70%</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>内存信息</span>
          </template>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="总内存">16 GB</el-descriptions-item>
            <el-descriptions-item label="已使用">8 GB</el-descriptions-item>
            <el-descriptions-item label="剩余">8 GB</el-descriptions-item>
            <el-descriptions-item label="使用率">50%</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20" class="mt-20">
      <el-col :span="24">
        <el-card>
          <template #header>
            <span>服务器信息</span>
          </template>
          <el-descriptions :column="2" border>
            <el-descriptions-item label="服务器名称">Apex-Server</el-descriptions-item>
            <el-descriptions-item label="操作系统">Windows 11</el-descriptions-item>
            <el-descriptions-item label="服务器IP">192.168.1.100</el-descriptions-item>
            <el-descriptions-item label="系统架构">amd64</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
</script>

<style scoped>
.mt-20 {
  margin-top: 20px;
}
</style>
