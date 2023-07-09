<template>
  <b-container class="bv-example-row mt-3 rounded" style="background-color: rgba(217, 217, 217, 1) margin-top:2rem">
    <b-row>
      <b-col class="text-center">
        <br />
        <h3>사용자 정보 수정</h3>
      </b-col>
    </b-row>
    <user-input-item-component type="modify" />
  </b-container>
</template>

<script>
import UserInputItemComponent from "./item/UserInputItemComponent.vue";
export default {
  name: "UserModifyComponent",
  components: { UserInputItemComponent },
};
</script>

<style scoped></style>
