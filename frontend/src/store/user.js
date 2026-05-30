import { defineStore } from 'pinia'
import { login, logout, getUserInfo } from '@/api/auth'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    userInfo: {},
    roles: [],
    permissions: []
  }),

  actions: {
    // 登录
    async login(loginForm) {
      const res = await login(loginForm)
      this.token = res.data.accessToken
      localStorage.setItem('token', res.data.accessToken)
      return res
    },

    // 获取用户信息
    async getInfo() {
      const res = await getUserInfo()
      this.userInfo = res.data
      this.roles = res.data.roles || []
      this.permissions = res.data.permissions || []
      return res
    },

    // 退出登录
    async logout() {
      await logout()
      this.token = ''
      this.userInfo = {}
      this.roles = []
      this.permissions = []
      localStorage.removeItem('token')
    },

    // 重置 Token
    resetToken() {
      this.token = ''
      this.roles = []
      this.permissions = []
      localStorage.removeItem('token')
    }
  }
})
