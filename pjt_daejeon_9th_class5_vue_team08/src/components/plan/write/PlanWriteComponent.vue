<template>
  
    <plan-input-item-component inputType="regist"/>
 
</template>
<script>
import PlanInputItemComponent from "./PlanInputItemComponent.vue";

export default {
  name: "PlanWriteComponent",
  components: { PlanInputItemComponent },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
