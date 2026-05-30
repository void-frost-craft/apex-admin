<template>
  <div class="dept-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>部门管理</span>
          <el-button type="primary">
            <el-icon><Plus /></el-icon> 新增
          </el-button>
        </div>
      </template>
      <el-table :data="[]" border row-key="deptId" :tree-props="{ children: 'children' }">
        <el-table-column label="部门名称" prop="deptName" width="200" />
        <el-table-column label="排序" prop="orderNum" width="80" />
        <el-table-column label="负责人" prop="leader" width="120" />
        <el-table-column label="联系电话" prop="phone" width="120" />
        <el-table-column label="邮箱" prop="email" width="180" />
        <el-table-column label="状态" width="100" />
        <el-table-column label="创建时间" prop="createTime" width="180" />
        <el-table-column label="操作" width="180" />
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
