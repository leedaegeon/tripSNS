<template>
  <div>
    <my-page-header :btnColor="btnColor"></my-page-header>
    <router-view @btnType="btnType"/>
  </div>
</template>

<script>
import MyPageHeader from "@/components/header/MyPageHeader.vue";

export default {
  name: "MyPageView",
  components: { MyPageHeader },
  data() {
    return {
      message: "",
      btnColor:"",
    };
  },
  created() {
    this.$emit("page", "my");
  },
  methods: {
   btnType(btnType){
    this.btnColor = btnType;
   }
  },
};
</script>

<style scoped></style>
