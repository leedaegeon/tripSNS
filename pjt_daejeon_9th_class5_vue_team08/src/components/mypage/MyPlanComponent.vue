<template>
  <div>
    <plan-list-item-component
      v-if="list.length != 0"
      :list="list"
      @commentWrite="commentWrite"
      @likeToggle="likeToggle"
      @modComment="modComment"
      @rmComment="rmComment"
      @removePlan="removePlan"
      @readMoreData="readMoreData($event)"

    ></plan-list-item-component>
    <!-- <plan-my-list-item-component
      v-if="list.length != 0"
      :list="list"
      @commentWrite="commentWrite"
      @likeToggle="likeToggle"
      @modComment="modComment"
      @rmComment="rmComment"
    ></plan-my-list-item-component> -->
    <b-container v-else class="mt-5">
      <b-row><b-col class="text-center">등록한 플랜이 없습니다! </b-col></b-row>
      <b-row>
        <b-col class="mt-5 text-center">
          <b-img
            :src="require('@/assets/plane.png')"
            id="empty_plan"
            class="d-inline-block align-top ml"
            alt="empty_plan"
            width="120px"
            height="120px"
          ></b-img>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import PlanListItemComponent from "@/components/plan/board/PlanListItemComponent.vue";
// import PlanMyListItemComponent from "@/components/plan/board/PlanMyListItemComponent.vue";
import { myPlanList, removePlan } from "@/api/plan";
import { addComment, removeComment, modifyComment } from "@/api/comment";
import { addWish, removeWish } from "@/api/wishlist";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "MyPlanComponent",
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
    this.$emit("btnType", "write");
  },
  methods: {
    fetchData() {
      let param = { userId: this.userInfo.userId };
      myPlanList(param, ({ data }) => (this.list = data));
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
    removePlan(planId) {
      removePlan(planId, () => {
        this.fetchData();
      });
    },
  },
};
</script>

<style scoped></style>
