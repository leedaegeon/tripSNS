<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="text-left">
        <h3>글작성</h3>
      </b-col>
    </b-row>
    <notice-input-item-component type="regist" />
  </b-container>
</template>
<script>
import NoticeInputItemComponent from "@/components/notice/item/NoticeInputItemComponent.vue";
export default {
  name: "NoticeWriteComponent",
  components: { NoticeInputItemComponent },
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
