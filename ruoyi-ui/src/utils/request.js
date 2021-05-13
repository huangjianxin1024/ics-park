import axios from 'axios'
import store from '@/store'
import storage from 'store'
import md5 from 'md5'
import { VueAxios } from './axios'
import { notification } from 'ant-design-vue'
import { ACCESS_TOKEN } from '@/store/mutation-types'

const baseURL = process.env.VUE_APP_API_BASE_URL
const appId = process.env.VUE_APP_ID
const key = process.env.VUE_APP_KEY

// 创建 axios 实例
const request = axios.create({
  baseURL: baseURL, // api base_url
  timeout: 6000 // 请求超时时间
})
export const pureAxios = axios.create({
  baseURL: baseURL, // api base_url
  timeout: 6000 // 请求超时时间
})

const err = (error) => {
  if (error.response) {
    const data = error.response.data
    const token = storage.get(ACCESS_TOKEN)
    if (error.response.status === 403) {
      notification.error({
        message: 'Forbidden',
        description: data.message
      })
    }
    if (error.response.status === 401 && !(data.result && data.result.isLogin)) {
      notification.error({
        message: 'Unauthorized',
        description: 'Authorization verification failed'
      })
      if (token) {
        store.dispatch('Logout').then(() => {
          setTimeout(() => {
            window.location.reload()
          }, 1500)
        })
      }
    }
  }
  return Promise.reject(error)
}

// request interceptor
request.interceptors.request.use(config => {
  const token = storage.get(ACCESS_TOKEN)
  if (token) {
    config.headers['token'] = token // 让每个请求携带自定义 token 请根据实际情况自行修改
    config.headers['appId'] = appId
    config.headers['sign'] = createSign()
  }
  return config
}, err)

pureAxios.interceptors.request.use(config => {
  const token = storage.get(ACCESS_TOKEN)
  if (token) {
    config.headers['token'] = token // 让每个请求携带自定义 token 请根据实际情况自行修改
    config.headers['appId'] = appId
    config.headers['sign'] = createSign()
  }
  return config
}, err)

// response interceptor
request.interceptors.response.use((response) => {
  return response.data
}, err)

const installer = {
  vm: {},
  install (Vue) {
    Vue.use(VueAxios, request)
  }
}

export default request

export {
  installer as VueAxios,
  request as axios
}

/**
 * 生成签名
 *
 * @param params
 * @returns {*}
 */
export function createSign () {
  const params = {}
  params.appId = appId
  params.key = key

  let keyArr = []
  for (const key in params) {
    keyArr.push(key)
  }
  keyArr = keyArr.sort()
  const keys = keyArr.map((v) => params[v] ? v + '=' + params[v] : '').join('&')
  return md5(keys)
}
