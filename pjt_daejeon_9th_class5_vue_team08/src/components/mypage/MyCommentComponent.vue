<template>
  <div>
    <plan-list-item-component
      v-if="this.list.length != 0"
      :list="list"
      @commentWrite="commentWrite"
      @likeToggle="likeToggle"
      @modComment="modComment"
      @rmComment="rmComment"
      @readMoreData="readMoreData($event)"

    ></plan-list-item-component>
    <b-container v-else class="mt-5">
      <b-row><b-col class="text-center">댓글을 남긴 플랜이 없습니다! </b-col></b-row>
      <b-row>
        <b-col class="mt-5 text-center">
          <b-img
            :src="require('@/assets/comment.png')"
            id="empty_plan"
            class="d-inline-block align-top ml"
            alt="empty_plan"
            width="100px"
            height="100px"
          ></b-img>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import PlanListItemComponent from "@/components/plan/board/PlanListItemComponent.vue";
import { myCommentPlanList } from "@/api/plan";
import { addComment, removeComment, modifyComment } from "@/api/comment";
import { addWish, removeWish } from "@/api/wishlist";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "MyCommentComponent",
  components: { PlanListItemComponent },
  data() {
    return {
      list: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.fetchData();
    this.$emit("btnType", "comment");
  },
  methods: {
    fetchData() {
      let param = { userId: this.userInfo.userId };
      myCommentPlanList(param, ({ data }) => (this.list = data));
    },

    likeToggle(isWish, param) {
      if (isWish) {
        console.log("좋아요 취소 시도");
        console.log(param);
        removeWish(param, () => {
          console.log("좋아요 취소");
          console.log(this.list);
          this.fetchData();
        });
      } else {
        console.log("좋아요 추가 시도");
        addWish(param, () => {
          console.log("좋아요");
          console.log(this.list);
          this.fetchData();
        });
      }
    },
    commentWrite(newComment) {
      addComment(newComment, () => {
        this.fetchData();
      });
    },
    modComment(commentObj) {
      modifyComment(commentObj, () => {
        this.fetchData();
      });
    },
    rmComment(commentNo) {
      removeComment(commentNo, () => {
        this.fetchData();
      });
    },
  },
};
</script>

<style scoped></style>
