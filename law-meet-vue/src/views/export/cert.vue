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
      <el-table-column align="center" label="律师姓名" prop="name" width="140"/>
      <el-table-column align="center" label="手机号码" prop="phone" width="140" />
      <el-table-column align="center" label="性别" prop="gender">
        <template slot-scope="scope">
          {{ genderDic[scope.row.gender] }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="最新照片" prop="image" width="140">
        <template slot-scope="scope">
          <el-image :src="scope.row.image" :preview-src-list="srcList" @click="preview(scope.row.image)" style="width: 120px;height: 80px;" />
        </template>
      </el-table-column>
      <el-table-column align="center" label="身份证号" prop="idCard" width="160" />
      <el-table-column align="center" label="执业证号" prop="wkCard" width="140" />
      <el-table-column align="center" label="所在城市" prop="city" width="140" />
      <el-table-column align="center" label="执证发放日期" prop="basDate" width="140" />
      <el-table-column align="center" label="执证截至日期" prop="baeDate" width="140" />
      <el-table-column align="center" label="职业资格证" prop="wkImage" width="140">
        <template slot-scope="scope">
          <el-image :src="scope.row.wkImage" :preview-src-list="srcList" @click="preview(scope.row.wkImage)" style="width: 120px;height: 80px;" />
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" prop="status" width="100">
        <template slot-scope="scope">
          {{ statusDic[scope.row.status] }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="审批备注" prop="approval" width="160" />
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
      @pagination="getList" />

  </div>
</template>

<script>
  import { LsList, LsDownload } from '@/api/business/cert'
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
        genderDic: ['男', '女','未知'],
        statusDic: ['未审核', '通过', '驳回'],
        downLoadList: [],
        srcList:[],
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        LsList(this.listQuery).then(response => {
          var data = response.data.data.records
          this.list = data.map(item => {
            return {
              ...item,
              basDate: item.basDate.split('T')[0],
              baeDate: item.baeDate.split('T')[0]
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
        axios.post('/admin-api/cert/download', this.downLoadList, {
          headers: { 'X-Dts-Admin-Token': getToken() },
          responseType: 'blob'
        }).then(res => {
          const blob = new Blob([res.data], { type: res.headers['content-type'] });
          const url = window.URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', '律师信息.xlsx'); // 设置下载文件名
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
      preview(val){
        this.srcList=[];
        this.srcList.push(val)
      }
    }
  }
</script>