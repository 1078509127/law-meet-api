<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.name" clearable size="mini" class="filter-item" style="width: 200px;"
        placeholder="请输入行政区域名称" />
      <el-input v-model="listQuery.code" clearable size="mini" class="filter-item" style="width: 200px;"
        placeholder="请输入行政区域编码" />
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search"
        @click="handleFilter">查找</el-button>
      <el-button :loading="downloadLoading" size="mini" class="filter-item" type="warning" icon="el-icon-download"
        @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column align="center" label="用户ID" prop="userId" width="100" />
      <el-table-column align="center" label="律师姓名" prop="name" />
      <el-table-column align="center" label="律师电话" prop="phone" />
      <el-table-column align="center" label="会见人" prop="people" />
      <el-table-column align="center" label="会见开始时间" prop="startTime" />
      <el-table-column align="center" label="会见结束时间" prop="endTime" />
      <el-table-column align="center" label="会见地点" prop="address" />
      <el-table-column align="center" label="预约说明" prop="remark" />
      <el-table-column align="center" label="审批状态" prop="status">
        <template slot-scope="scope">
          {{statusDic[scope.row.status]}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="审批备注" prop="approval" />
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
      @pagination="getList" />

  </div>
</template>

<script>
  import { sqList } from '@/api/business/cert'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
  import axios from 'axios'
  import { getToken } from '@/utils/auth'

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
        downLoadList: [],
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        sqList(this.listQuery).then(response => {
          var data = response.data.data.records
          this.list = data.map(item => {
            return {
              ...item,
              startTime: item.startTime.replace('T', ' '),
              endTime: item.endTime.replace('T', ' ')
            };
          });
          this.total = response.data.data.total
          this.listLoading = false
        }).catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
      },
      handleSelectionChange(val) {
        this.downLoadList = [];
        for (var key in val) {
          this.downLoadList.push(val[key].id);
        }
      },
      handleDownload() {
        if (this.downLoadList.length == 0) {
          this.$message({
            showClose: true,
            message: '请选择导出数据',
            type: 'error'
          });
          return;
        }
        this.downloadLoading = true
        axios.post('/admin-api/meet/download', this.downLoadList, {
          headers: { 'X-Dts-Admin-Token': getToken() },
          responseType: 'blob'
        }).then(res => {
          const blob = new Blob([res.data], { type: res.headers['content-type'] });
          const url = window.URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', '预约信息.xlsx');
          document.body.appendChild(link);
          link.click();
          window.URL.revokeObjectURL(url);
          this.downloadLoading = false
        }).catch(error => {
          console.error('下载失败：', error);
        });
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getList()
      },
    }
  }
</script>