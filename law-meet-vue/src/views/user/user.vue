<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.userName" clearable size="small" class="filter-item" style="width: 200px;" placeholder="请输入用户名"/>
      <el-input v-model="listQuery.mobile" clearable size="small" class="filter-item" style="width: 200px;" placeholder="请输入手机号"/>
      <el-button class="filter-item" type="primary" size="mini" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button :loading="downloadLoading" size="mini" class="filter-item" type="warning" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中..." border fit highlight-current-row>
      <el-table-column align="center" width="100px" label="用户ID" prop="id" sortable/>
      <el-table-column align="center" label="用户名" prop="name"/>
      <el-table-column align="center" label="手机号码" prop="phone"/>
      <el-table-column align="center" label="性别" prop="gender">
        <template slot-scope="scope">
          {{ genderDic[scope.row.gender] }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="身份证号" prop="idCard"/>
      <el-table-column align="center" label="职业证号" prop="wkCard"/>
      <el-table-column align="center" label="备案日期" prop="baDate"/>
      <el-table-column align="center" label="职业资格证" prop="wkImage">
        <template slot-scope="scope">
          <img :src="scope.row.wkImage" width="120" height="80"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" prop="status">
        <template slot-scope="scope">
          {{ statusDic[scope.row.status] }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" :disabled="scope.row.status=== 1 ||scope.row.status=== 2" @click="handleDetail(scope.row)">审批</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :visible.sync="detailDialogVisible" title="代理详情" width="700">
      <el-form :data="agencyDetail" label-position="left">
        <el-form-item label="佣金比例(%)">
          <span>{{ agencyDetail.settlementRate }}</span>
        </el-form-item>
        <el-form-item label="推广二维码">
          <img :src="agencyDetail.shareUrl" width="300">
        </el-form-item>
      </el-form>
    </el-dialog>
    <!-- 代理审批 -->
    <el-dialog :visible.sync="approveDialogVisible" title="代理审批">
      <el-form ref="approveForm" :model="approveForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="佣金比例(%)" prop="settlementRate">
          <el-input v-model="approveForm.settlementRate"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="approveDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmApprove">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { LsList } from '@/api/business/cert'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'User',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        userName: undefined,
        mobile: undefined,
      },
      downloadLoading: false,
      genderDic: ['未知', '男', '女'],
      statusDic: ['未审核', '通过', '驳回'],
      detailDialogVisible: false,
      agencyDetail: {},
      approveDialogVisible: false,
      approveForm: {
        userId: undefined,
        settlementRate: undefined
      }
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
    handleDetail(row) {
      this.agencyDetail = {
        shareUrl: undefined,
        settlementRate: undefined
      }
      this.detailDialogVisible = true
    },
    handleApproveAgency(row) {
      this.approveForm.userId = row.id

      this.approveDialogVisible = true
      this.$nextTick(() => {
        this.$refs['approveForm'].clearValidate()
      })
    },
    confirmApprove() {
      this.$refs['approveForm'].validate((valid) => {
        if (valid) {
          approveAgency(this.approveForm).then(response => {
            this.approveDialogVisible = false
            this.$notify.success({
              title: '成功',
              message: '审批成功'
            })
            this.getList()
          }).catch(response => {
            this.$notify.error({
              title: '审批失败',
              message: response.data.errmsg
            })
          })
        }
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['用户名', '手机号码', '性别', '生日', '状态']
        const filterVal = ['userName', 'mobile', 'gender', 'birthday', 'status']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户信息')
        this.downloadLoading = false
      })
    }
  }
}
</script>
