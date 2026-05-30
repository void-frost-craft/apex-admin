<template>
  <div class="post-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>岗位管理</span>
          <el-button type="primary">
            <el-icon><Plus /></el-icon> 新增
          </el-button>
        </div>
      </template>
      <el-table :data="[]" border>
        <el-table-column label="岗位ID" prop="postId" width="80" />
        <el-table-column label="岗位编码" prop="postCode" width="150" />
        <el-table-column label="岗位名称" prop="postName" width="150" />
        <el-table-column label="排序" prop="postSort" width="80" />
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
