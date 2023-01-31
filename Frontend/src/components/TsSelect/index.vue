<template>
<el-select
	v-model="model"
	:placeholder="placeholder"
	:disabled='disabled'
	:clearable='clearable'
	:multiple='multiple'
	:filterable='filterable'
  :collapse-tags='collapseTags'
	:name='name'
  @change='change'
	@visible-change='visibleChange'
  :size="size"
  >
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
      :disabled="item.disabled"
      >
    </el-option>
  </el-select>
</template>

<script>
export default {
	props: {
		options: null,
		value: null,
		disabled: {
			type: Boolean
		},
		placeholder: {
			type: String
		},
		clearable: { // 单选时可删除
			type: Boolean,
			default: false
		},
		multiple: { // 是否可多选
			type: Boolean,
			default: false
		},
		filterable: { // 是否可搜索
			type: Boolean,
			default: false
		},
    collapseTags: {
      type: Boolean,
      default: false
    },
		name: {
			type: String
    },
    size: {
      type: String,
      default: 'mini'
    }
	},
	data() {
		return {
			model: this.value,
			inputDom: null
		}
	},
	watch: {
		model(val) {
			this.$emit('input', val)
		},
    value(val) {
      this.model = val
    }
	},
	methods: {

    // 选中值发生变化时触发
		change(val) {
			this.$emit('change', val)
		},

    // 下拉框出现/隐藏时触发 出现则为 true，隐藏则为 false
    visibleChange(val) {
      this.$emit('visible-change', val)
    },
    setValue(v) {
      this.value = v;
    }
	},
	mounted() {
		if (this.multiple && this.filterable) {
			this.inputDom = this.$el.querySelector('input')
			this.inputDom.addEventListener('focus', () => {
				this.inputDom.style.height = '12px'
			})
			this.inputDom.addEventListener('blur', () => {
				this.inputDom.style.height = 0
			})
		}
	}
}
</script>
