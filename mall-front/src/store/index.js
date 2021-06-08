import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    query_params:{
      brandId:new Map(),
      attrs: new Map()
    }
  },
  mutations: {
    addBrandId(state, {id, name}){
      state.query_params.brandId.set(id, name)
    },
    delBrandId(state, id){
      state.query_params.brandId.delete(id)
    },
    addAttr(state, {id, name, value}){
      state.query_params.attrs.set(id, [name, value])
    },
    delAttr(state, id){
      state.query_params.attrs.delete(id)
    }
  },
  actions: {
  },
  modules: {
  }
})
