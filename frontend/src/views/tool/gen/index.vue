<template>
  <div class="gen-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>代码生成</span>
          <el-button type="primary">
            <el-icon><Download /></el-icon> 导入表
          </el-button>
        </div>
      </template>
      <el-table :data="[]" border>
        <el-table-column label="表名称" prop="tableName" width="200" />
        <el-table-column label="表描述" prop="tableComment" width="200" />
        <el-table-column label="实体类名称" prop="className" width="200" />
        <el-table-column label="创建时间" prop="createTime" width="180" />
        <el-table-column label="更新时间" prop="updateTime" width="180" />
        <el-table-column label="操作" width="200" />
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
