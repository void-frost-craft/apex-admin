import request from './index'

// ========== 用户管理 ==========

// 用户列表
export function getUserList(params) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params
  })
}

// 用户详情
export function getUserInfo(userId) {
  return request({
    url: `/system/user/${userId}`,
    method: 'get'
  })
}

// 新增用户
export function addUser(data) {
  return request({
    url: '/system/user',
    method: 'post',
    data
  })
}

// 修改用户
export function updateUser(data) {
  return request({
    url: '/system/user',
    method: 'put',
    data
  })
}

// 删除用户
export function deleteUser(userIds) {
  return request({
    url: `/system/user/${userIds}`,
    method: 'delete'
  })
}

// ========== 角色管理 ==========

// 角色列表
export function getRoleList(params) {
  return request({
    url: '/system/role/list',
    method: 'get',
    params
  })
}

// 角色详情
export function getRoleInfo(roleId) {
  return request({
    url: `/system/role/${roleId}`,
    method: 'get'
  })
}

// ========== 菜单管理 ==========

// 菜单列表
export function getMenuList(params) {
  return request({
    url: '/system/menu/list',
    method: 'get',
    params
  })
}

// 菜单树
export function getMenuTree() {
  return request({
    url: '/system/menu/tree',
    method: 'get'
  })
}

// ========== 部门管理 ==========

// 部门列表
export function getDeptList(params) {
  return request({
    url: '/system/dept/list',
    method: 'get',
    params
  })
}

// 部门树
export function getDeptTree() {
  return request({
    url: '/system/dept/tree',
    method: 'get'
  })
}

// ========== 岗位管理 ==========

// 岗位列表
export function getPostList(params) {
  return request({
    url: '/system/post/list',
    method: 'get',
    params
  })
}
