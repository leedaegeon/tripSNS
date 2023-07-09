<template>
  <div class="row">
    <plan-list-item-component
      :list="list"
      @commentWrite="commentWrite"
      @likeToggle="likeToggle"
      @modComment="modComment"
      @rmComment="rmComment"
      @removePlan="removePlan"
    ></plan-list-item-component>
    <!-- <plan-list-item-component
      :list="list"
      @commentWrite="commentWrite"
      @likeToggle="likeToggle"
      @modComment="modComment"
      @rmComment="rmComment"
      @removePlan="removePlan"
    ></plan-list-item-component> -->
    <!-- <button @click="fetch($state)">스크롤</button> -->
  </div>
</template>

<script>
import PlanListItemComponent from "@/components/plan/board/PlanListItemComponent.vue";
import { planDetailList, planDetailListWithUserId, removePlan } from "@/api/plan";
import { addComment, removeComment, modifyComment } from "@/api/comment";
import { addWish, removeWish } from "@/api/wishlist";
import { mapState } from "vuex";
// import { componentsPlugin } from "bootstrap-vue";
const memberStore = "memberStore";

export default {
  name: "PlanListComponent",
  components: { PlanListItemComponent },
  data() {
    return {
      list: [],
      planId: "",
      showQuickMenu: true,

      plans: [],
      contentIds: [],
      attractionInfos: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  watch: {
    $route: "fetchData",
  },
  created() {
    this.fetchData();
    
  },
  methods: {
    
    
    fetchData() {
      console.log("fetch");
      if (this.userInfo === null) {
        planDetailList(({ data }) => {
          this.list = data;
        });
      } else {
        let param = { userId: this.userInfo.userId };
        planDetailListWithUserId(param, ({ data }) => {
          this.list = data;
        });
      }
    },
    likeToggle(isWish, param) {
      if (isWish) {
        console.log("좋아요 취소 시도");
        console.log(param);
        removeWish(param, () => {
          // this.renderKeyforWish += 1;
          console.log("좋아요 취소", this.renderKeyforWish);
          console.log(this.list);
          this.fetchData();
        });
      } else {
        console.log("좋아요 추가 시도");
        addWish(param, () => {
          // this.renderKeyforWish += 1;
          console.log("좋아요", this.renderKeyforWish);
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

<style scoped>
.sticky {
  position: fixed;
  margin-left: 80%;
  /* margin-bottom: */
  z-index: 7;
}
.row {
  display: flex;
  flex-direction: column;
  /* align-content: center; */
}
.outer-box {
  padding-top: 2rem;
  padding-bottom: 1rem;
  z-index: 5;
  /* position: relative; */
  padding-top: 4rem;
  padding-bottom: 4rem;
  margin-top: 4rem;
  background: var(--bg-page1);
  display: flex;

  justify-content: center;
  /* width:100rem; */
  /* box-shadow: rgba(0, 0, 0, 0.04) 0px -16px 16px; */
}
.outer-title {
  font-size: 1.25rem;
  margin-bottom: 2rem;
  /* text-align: center; */
  font-weight: 400;
  color: var(--text2);
  /* margin-left: 50rem; */
  display: flex;
  justify-content: center;
  margin-top: 2rem;
}

.inner-box {
  padding-left: 1rem;
  padding-right: 1rem;
  padding-bottom: 3rem;
  margin-left: 10rem;
  margin-right: 10rem;
  margin-left: 4rem;
  margin-right: 4rem;
  margin-bottom: 4rem;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  max-height: 20%;
  justify-content: center;
}
.card-box {
  height: 20rem;
  width: 20rem;
  background: var(--bg-element1);
  border-radius: 4px;
  box-shadow: rgba(0, 0, 0, 0.04) 0px 4px 16px 0px;
  transition: box-shadow 0.25s ease-in 0s, transform 0.25s ease-in 0s;
  margin: 1rem;
  /* overflow: hidden; */
  display: flex;

  flex-direction: column;
}
.selected-card-box {
  height: 50rem;
  width: 100%;

  background: var(--bg-element1);
  border-radius: 4px;
  box-shadow: rgba(0, 0, 0, 0.04) 0px 4px 16px 0px;
  transition: box-shadow 0.25s ease-in 0s, transform 0.25s ease-in 0s;
  margin: 1rem;

  display: flexbox;

  flex-direction: column;
}
.content-box {
  width: 20rem;
  flex-wrap: wrap;

  padding: 1rem;
  display: flex;
  flex: 1 1 0%;
  flex-direction: column;
}
.content {
  display: block;
  color: inherit;
  text-decoration: none;
}
.content-box .sub-info {
  font-size: 0.75rem;
  line-height: 1.5;
  color: var(--text3);
}
.content-box .sub-info .separator {
  margin-left: 0.25rem;
  margin-right: 0.25rem;
}
.others {
  padding: 0.625rem 1rem;
  border-top: 1px solid var(--border4);
  display: flex;
  font-size: 0.75rem;
  line-height: 1.5;
  -webkit-box-pack: justify;
  justify-content: space-between;
}
.others .userinfo {
  text-decoration: none;
  color: inherit;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}
.others .likes {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}
.content-box a.content {
  /* margin: inherit; */
  width: inherit;
  padding-right: 2rem;
  /* display: flex; */
  flex-wrap: wrap;
  flex-direction: column;
}
* {
  box-sizing: inherit;
}
</style>
