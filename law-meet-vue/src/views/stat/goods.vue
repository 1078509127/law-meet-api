<template>
  <div class="app-container">
    <ve-line :extend="chartExtend" :data="chartData" :settings="chartSettings"/>
  </div>
</template>

<script>
import { statGoods } from '@/api/business/stat'
import VeLine from 'v-charts/lib/line'

export default {
  components: { VeLine },
  data() {
    return {
      chartData: {},
      chartSettings: {},
      chartExtend: {}
    }
  },
  created() {
    statGoods().then(response => {
      this.chartData = response.data.data
      this.chartSettings = {
        labelMap: {
          'orders': '订单量',
          'products': '会面时长',
          'amount': '使用量'
        }
      }
      this.chartExtend = {
        xAxis: { boundaryGap: true }
      }
    })
  }

}
</script>
