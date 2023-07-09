<template>
  <div>
    <b-container class="bv-example-row mt-5 text-center">
      <h3>자유 게시판</h3>
      <router-view></router-view>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "NoticeBoardView",
  components: {},

  data() {
    return {
      message: "",
    };
  },
  created() {
    this.$emit("page", "notice");
  },
  methods: {},
};
</script>

<style scoped>
h3 {
  margin: 2rem;
  font-size: 36pt;
}
</style>
