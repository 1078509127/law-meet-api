import request from '@/utils/request'

//认证
export function LsList(query) {
  return request({
    url: '/cert/list',
    method: 'get',
    params: query
  })
}

export function LsDownload(data) {
  return request({
    url: '/cert/download',
    method: 'post',
    data
  })
}

export function LsApproval(data) {
  return request({
    url: '/certApproval/approval',
    method: 'post',
    data
  })
}


//申请
export function sqList(query) {
  return request({
    url: '/meet/list',
    method: 'get',
    params: query
  })
}

export function sqDownload(data) {
  return request({
    url: '/meet/download',
    method: 'post',
    data
  })
}

export function sqApproval(data) {
  return request({
    url: '/meetApproval/approval',
    method: 'post',
    data
  })
}

