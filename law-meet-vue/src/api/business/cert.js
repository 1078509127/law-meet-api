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
    responseType: 'blob',
    url: '/cert/download',
    method: 'post',
    data
  })
}

export function LsApproval(data) {
  return request({
    url: '/cert/approval',
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
    url: '/meet/approval',
    method: 'post',
    data
  })
}


//公告
export function annSaveOrUpdate(data) {
  return request({
    url: '/announce/saveContent',
    method: 'post',
    data
  })
}

export function annGet(query) {
  return request({
    url: '/announce/getContent',
    method: 'get',
    params: query
  })
}

//意见反馈
export function feedGet(query) {
  return request({
    url: '/feedback/selectList',
    method: 'get',
    params: query
  })
}