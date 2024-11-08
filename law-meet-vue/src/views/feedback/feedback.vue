<template>
    <div class="app-container">
  
      <!-- 查询和其他操作 -->
      <div class="filter-container">
        <el-input v-model="listQuery.phone" clearable size="small" class="filter-item" style="width: 200px;" placeholder="请输入手机号" />
        <el-button class="filter-item" type="primary" size="mini" icon="el-icon-search" @click="handleFilter">查找</el-button>
      </div>
  
      <!-- 查询结果 -->
      <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中..." border fit highlight-current-row>
        <el-table-column align="center" label="反馈类型" prop="type"/>
        <el-table-column align="center" label="手机号码" prop="phone" />
        <el-table-column align="center" label="反馈问题" prop="content" />
        <el-table-column align="center" label="创建时间" prop="createTime" />
      </el-table>
  
      <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  
    </div>
  </template>
  
  <script>
    import { feedGet } from '@/api/business/cert'
    import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
  
    export default {
      components: { Pagination },
      data() {
        return {
          list: null,
          total: 0,
          listLoading: true,
          listQuery: {
            page: 1,
            limit: 20,
            phone: undefined,
          },
        }
      },
      created() {
        this.getList()
      },
      methods: {
        getList() {
          this.listLoading = true
          feedGet(this.listQuery).then(response => {
            var data = response.data.data.records
          this.list = data.map(item => {
            return {
              ...item,
              createTime: item.createTime.replace('T',' '),
            };
          });
            this.total = response.data.data.total
            this.listLoading = false
          }).catch((err) => {
            this.list = []
            this.total = 0
            this.listLoading = false
          })
        },
  
        handleFilter() {
          this.listQuery.page = 1
          this.getList()
        },
      }
    }
  </script>