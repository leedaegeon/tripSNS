<template>
  <b-container class="bv-example-row mt-5 rounded" style="background-color: rgba(217, 217, 217, 1)">
    <b-row>
      <b-col class="text-center mt-5">
        <h3>회원가입</h3>
      </b-col>
    </b-row>
    <user-input-item-component type="regist" />
  </b-container>
</template>

<script>
import UserInputItemComponent from "./item/UserInputItemComponent.vue";
export default {
  name: "UserRegistComponent",
  components: { UserInputItemComponent },
};
</script>

<style scoped></style>
