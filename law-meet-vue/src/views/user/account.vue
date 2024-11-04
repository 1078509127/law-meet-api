<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.name" clearable size="small" class="filter-item" style="width: 200px;" placeholder="请输入用户名"/>
      <el-input v-model="listQuery.phone" clearable size="small" class="filter-item" style="width: 200px;" placeholder="请输入手机号"/>
      <el-select v-model="listQuery.status" multiple size="small" style="width: 200px" class="filter-item" placeholder="请选择提现状态">
        <el-option v-for="(key, value) in statusMap" :key="key" :label="key" :value="value"/>
      </el-select>
      <el-button class="filter-item" type="primary" size="small" icon="el-icon-search" @click="handleFilter">查找</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
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
      <el-table-column align="center" label="审批备注" prop="birthday"/>
      <el-table-column align="center" label="操作" width="120" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['GET /admin/brokerage/approve']" type="primary" size="mini" :disabled="scope.row.status=== 1 ||scope.row.status=== 2" @click="approval(scope.row)">审批</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :visible.sync="approveDialogVisible" title="审批">
      <el-form ref="approveForm" :rules="rules" :model="approveForm" status-icon label-position="left" label-width="100px" style="width: 70%; margin-left:50px;">
        <el-form-item label="是否通过" prop="status">
          <el-radio-group v-model="approveForm.status">
            <el-radio :label="1">通过</el-radio>
            <el-radio :label="2">拒绝</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审批备注" prop="traceMsg">
          <el-input type="textarea" v-model="approveForm.traceMsg"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="approveDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmApprove">审批</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { sqList } from '@/api/business/cert'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import checkPermission from '@/utils/permission' // 权限判断函数

const statusMap = {
  0: '提现申请',
  1: '审批通过',
  2: '审批拒绝'
}

const typeMap = {
  0: '系统结算',
  1: '用户申请'
}

export default {
  name: 'Account',
  components: { Pagination },
  filters: {
    statusFilter(status) {
      return statusMap[status]
    },
    typeFilter(type) {
      return typeMap[type]
    }
  },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        name: undefined,
        phone: undefined,
        status: [],
      },
      statusDic:['未审核','通过','驳回'],
      statusMap,
      typeMap,
      rules: {
        status: [
          { required: true, message: '审批状态不能为空！', trigger: 'blur' }
        ]
      },
      approveDialogVisible: false,
      approveForm: {
        id: undefined,
        status: undefined,
        traceMsg: undefined
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    checkPermission,
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
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    approval(row) {
      this.approveForm.id = row.id
      this.approveDialogVisible = true
      this.$nextTick(() => {
        this.$refs['approveForm'].clearValidate()
      })
    },
    confirmApprove() {
      console.log(this.approveForm)
      this.$refs['approveForm'].validate((valid) => {
        if (valid) {
          approveTrace(this.approveForm).then(response => {
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
    }
  }
}
</script>
