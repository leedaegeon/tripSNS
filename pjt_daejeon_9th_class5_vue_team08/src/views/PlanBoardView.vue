<template>
  <div>
    <h3></h3>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "PlanBoardView",
  components: {},
  data() {
    return {
      message: "",
      

    };
  },
  created() {
    this.$emit("page", "plan");
    
  },
  methods: {},
};
</script>

<style scoped></style>
