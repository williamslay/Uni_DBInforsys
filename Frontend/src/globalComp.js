import appContainer from "@/components/AppContainer/index"
import tsSelect from '@/components/TsSelect'
import tsTable from "@/components/TsTable"

export function initGlobalComp(Vue) {
  Vue.component('app-container', appContainer)
  Vue.component('tsSelect', tsSelect)
  Vue.component('tsTable', tsTable)
}
