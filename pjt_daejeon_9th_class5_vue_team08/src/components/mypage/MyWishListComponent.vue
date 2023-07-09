<template>
  <div>
    <plan-list-item-component
      v-if="list.length != 0"
      :list="list"
      @commentWrite="commentWrite"
      @likeToggle="likeToggle"
      @modComment="modComment"
      @rmComment="rmComment"
      @readMoreData="readMoreData($event)"

    ></plan-list-item-component>
    <b-container v-else class="mt-5">
      <b-row><b-col class="text-center"> 찜 목록이 없습니다! </b-col></b-row>
      <b-row>
        <b-col class="mt-5 text-center">
          <b-img
            :src="require('@/assets/cart.png')"
            id="empty_plan"
            class="d-inline-block align-top ml"
            alt="empty_plan"
            width="150px"
            height="150px"
          ></b-img>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import PlanListItemComponent from "@/components/plan/board/PlanListItemComponent.vue";
import { myWishPlanList } from "@/api/plan";
import { addComment, removeComment, modifyComment } from "@/api/comment";
import { addWish, removeWish } from "@/api/wishlist";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "MyWishListComponent",
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
    this.$emit("btnType", "like");
  },
  methods: {
    
    fetchData() {
      let param = { userId: this.userInfo.userId };
      myWishPlanList(param, ({ data }) => {
        this.list = data;
        console.log(this.list);
      });
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
