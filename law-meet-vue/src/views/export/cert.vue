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
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit
      highlight-current-row @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column align="center" width="100px" label="用户ID" prop="id" sortable />
      <el-table-column align="center" label="用户名" prop="name" />
      <el-table-column align="center" label="手机号码" prop="phone" />
      <el-table-column align="center" label="性别" prop="gender">
        <template slot-scope="scope">
          {{ genderDic[scope.row.gender] }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="身份证号" prop="idCard" />
      <el-table-column align="center" label="职业证号" prop="wkCard" />
      <el-table-column align="center" label="备案日期" prop="baDate" />
      <el-table-column align="center" label="职业资格证" prop="wkImage">
        <template slot-scope="scope">
          <img :src="scope.row.wkImage" width="120" height="80" />
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" prop="status">
        <template slot-scope="scope">
          {{ statusDic[scope.row.status] }}
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
      @pagination="getList" />

  </div>
</template>

<script>
  import { LsList, LsDownload } from '@/api/business/cert'
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
        downLoadList: [],
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        LsList(this.listQuery).then(response => {
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
        this.downLoadList = [];
        for (var key in val) {
          this.downLoadList.push(val[key].id);
        }
      },
      handleDownload() {
        //this.downloadLoading = true
        LsDownload(this.downLoadList).then(res => {
          const url = window.URL.createObjectURL(new Blob([res.data]));
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', 'excel_with_image.xlsx');
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          console.log(res)
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