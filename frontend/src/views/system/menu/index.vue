<template>
  <div class="menu-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>菜单管理</span>
          <el-button type="primary">
            <el-icon><Plus /></el-icon> 新增
          </el-button>
        </div>
      </template>
      <el-table :data="[]" border row-key="menuId" :tree-props="{ children: 'children' }">
        <el-table-column label="菜单名称" prop="menuName" width="200" />
        <el-table-column label="图标" prop="icon" width="100" />
        <el-table-column label="排序" prop="orderNum" width="80" />
        <el-table-column label="权限标识" prop="perms" width="200" />
        <el-table-column label="组件路径" prop="component" width="200" />
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
