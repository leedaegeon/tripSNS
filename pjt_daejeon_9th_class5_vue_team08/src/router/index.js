import Vue from "vue";
import VueRouter from "vue-router";

import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  // console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    // console.log("토큰 유효성 체크");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    var result = confirm("로그인이 필요한 페이지입니다.\n로그인 페이지로 이동하시겠습니까?");
    // next({ name: "login" });
    if (result) {
      // router.push(to.path);
      router.push({ name: "login" });
    }
  } else {
    // console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    redirect: "/plan",
  },

  {
    path: "/mypage",
    name: "mypage",
    component: () => import(/* webpackChunkName: "about" */ "../views/MyPageView.vue"),
    beforeEnter: onlyAuthUser,
    redirect: "/mypage/myplan",
    children: [
      {
        path: "myplan",
        name: "myplan",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/mypage/MyPlanComponent.vue"),
      },
      {
        path: "mycomment",
        name: "mycomment",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/mypage/MyCommentComponent.vue"),
      },
      {
        name: "mywishlist",
        path: "mywishlist",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/mypage/MyWishListComponent.vue"),
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: () => import(/* webpackChunkName: "about" */ "../views/NoticeBoardView.vue"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "nlist",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/notice/NoticeListComponent.vue"),
      },
      {
        path: "detail/:noticeNo",
        name: "ndetail",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/notice/NoticeDetailComponent.vue"),
      },
      {
        path: "write",
        name: "nwrite",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/notice/NoticeWriteComponent.vue"),
      },
      {
        path: "modify/:noticeNo",
        name: "nmodify",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/notice/NoticeModifyComponent.vue"),
      },
    ],
  },
  {
    path: "/plan",
    name: "plan",
    component: () => import(/* webpackChunkName: "about" */ "../views/PlanBoardView.vue"),
    redirect: "/plan/list",
    children: [
      {
        path: "list",
        name: "plist",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/plan/board/PlanListComponent.vue"),
      },
      
      {
        path: "write",
        name: "pwrite",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/plan/write/PlanWriteComponent.vue"),
      },
      {
        path: "modify/:planId",
        name: "pmodify",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "about" */ "../components/plan/write/PlanModifyComponent.vue"
          ),
      },
    ],
  },

 
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "about" */ "../views/UserView.vue"),
    redirect: "/user/login",
    children: [
      {
        path: "login",
        name: "login",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/user/UserLoginComponent.vue"),
      },
      {
        path: "regist",
        name: "regist",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/user/UserRegistComponent.vue"),
      },
      {
        path: "modify",
        name: "modify",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/user/UserModifyComponent.vue"),
      },
      {
        path: "list",
        name: "ulist",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/user/UserListComponent.vue"),
      },
      {
        path: "info",
        name: "userinfo",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/user/UserInfoComponent.vue"),
      },
      {
        path: "delete",
        name: "delete",
        component: () =>
          import(/* webpackChunkName: "about" */ "../components/user/UserDeleteComponent.vue"),
      },
    ],
  },
  
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
