import request from '@/utils/request'

export function changePassword(data) {
  return request({
    url: '/auth/password',
    method: 'post',
    data
  })
}
