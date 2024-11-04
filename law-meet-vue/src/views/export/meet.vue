<template>
    <div class="app-container">
  
      <!-- 查询和其他操作 -->
      <div class="filter-container">
        <el-input v-model="listQuery.name" clearable size="mini" class="filter-item" style="width: 200px;" placeholder="请输入行政区域名称"/>
        <el-input v-model="listQuery.code" clearable size="mini" class="filter-item" style="width: 200px;" placeholder="请输入行政区域编码"/>
        <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
        <el-button :loading="downloadLoading" size="mini" class="filter-item" type="warning" icon="el-icon-download" @click="handleDownload">导出</el-button>
      </div>
  
      <!-- 查询结果 -->
      <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column align="center" width="100px" label="用户ID" prop="userId" sortable/>
        <el-table-column align="center" label="用户名" prop="name"/>
        <el-table-column align="center" label="手机号码" prop="phone"/>
        <el-table-column align="center" label="预约地址" prop="address"/>
        <el-table-column align="center" label="预约时间" prop="startTime"/>
        <el-table-column align="center" label="预约说明" prop="remark"/>
        <el-table-column align="center" label="审批状态" prop="status">
          <template slot-scope="scope">
            {{statusDic[scope.row.status]}}
          </template>
        </el-table-column>
        <el-table-column align="center" label="审批备注" prop="approval"/>
      </el-table>
  
      <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  
    </div>
  </template>
  
  <script>
  import { sqList } from '@/api/business/cert'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
  
  export default {
    name: 'Region',
    components: { Pagination },
    data() {
      return {
        list: undefined,
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 20,
          name: undefined,
          code: undefined
        },
        downloadLoading: false,
        genderDic: ['未知', '男', '女'],
        statusDic: ['未审核', '通过', '驳回'],
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        sqList(this.listQuery).then(response => {
            this.list = response.data.data.records
            this.total = response.data.data.total
            this.listLoading = false
        }).catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
      },
      handleSelectionChange(val) {
        console.log(val)
      },
      handleDownload() {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          const tHeader = ['用户ID', '用户名', '手机号码', '预约地址', '预约时间','预约说明','审核状态','审批备注']
          const filterVal = ['userId', 'name', 'phone', 'address', 'startTime','remark','status','approval']
          excel.export_json_to_excel2(tHeader, this.list, filterVal, '行政区域信息')
          this.downloadLoading = false
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getList()
      },
    }
  }
  </script>
  