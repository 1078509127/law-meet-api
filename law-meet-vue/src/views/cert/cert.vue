<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.userName" clearable size="small" class="filter-item" style="width: 200px;" placeholder="请输入用户名" />
      <el-input v-model="listQuery.mobile" clearable size="small" class="filter-item" style="width: 200px;" placeholder="请输入手机号" />
      <el-button class="filter-item" type="primary" size="mini" icon="el-icon-search" @click="handleFilter">查找</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中..." border fit highlight-current-row>
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
      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" :disabled="scope.row.status=== 1 ||scope.row.status=== 2" @click="approval(scope.row)">审批</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
      @pagination="getList" />

    <el-dialog :visible.sync="approveDialogVisible" title="审批">
      <el-form ref="approveForm" :rules="rules" :model="approveForm" status-icon label-position="left"
        label-width="100px" style="width: 70%; margin-left:50px;">
        <el-form-item label="是否通过" prop="status">
          <el-radio-group v-model="approveForm.status">
            <el-radio :label="1">通过</el-radio>
            <el-radio :label="2">拒绝</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审批备注" prop="traceMsg">
          <el-input type="textarea" v-model="approveForm.traceMsg" />
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
  import { LsList, LsApproval } from '@/api/business/cert'
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
        genderDic: ['男', '女','未知'],
        statusDic: ['未审核', '通过', '驳回'],
        detailDialogVisible: false,
        agencyDetail: {},
        rules: {
          status: [
            { required: true, message: '审批状态不能为空！', trigger: 'blur' }
          ]
        },
        approveDialogVisible: false,
        approveForm: {
          id: undefined,
          adminId: undefined,
          status: undefined,
          traceMsg: undefined
        },
        srcList:[]
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

      approval(row) {
        this.approveForm.id = row.id
        this.approveForm.adminId = this.$store.getters.user.id;
        this.approveDialogVisible = true
        this.$nextTick(() => {
          this.$refs['approveForm'].clearValidate()
        })
      },
      confirmApprove() {
        this.$refs['approveForm'].validate((valid) => {
          if (valid) {
            LsApproval(this.approveForm).then(response => {
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
      preview(val){
        this.srcList=[];
        this.srcList.push(val)
      }
    }
  }
</script>