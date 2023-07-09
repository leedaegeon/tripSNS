import { apiInstance } from "./index.js";

const api = apiInstance();

function planList(success) {
  api.get(`/planboard`).then(success);
}

function planDetailList(success) {
  api.get(`/planboard/detail`).then(success);
}

function planDetail(planId, success) {
  api.get(`/planboard/detail/${planId}`).then(success);
}

function planDetailListWithUserId(userId, success) {
  api.get(`/planboard/detail-with-userid`, { params: userId }).then(success);
}

function planWrite(planBoard, success) {
  api.post(`/planboard`, planBoard).then(success);
}

function removePlan(planId, success) {
  api.delete(`/planboard/${planId}`).then(success);
}

function planModify(planBoard, success) {
  api.put(`/planboard`, planBoard).then(success);
}

function myPlanList(userId, success) {
  api.get(`/planboard/myplan`, { params: userId }).then(success);
}

function myCommentPlanList(userId, success) {
  api.get(`/planboard/commented`, { params: userId }).then(success);
}

function myWishPlanList(userId, success) {
  api.get(`/planboard/wish`, { params: userId }).then(success);
}

export {
  planList,
  planDetailList,
  planDetail,
  planWrite,
  removePlan,
  planModify,
  myPlanList,
  myCommentPlanList,
  myWishPlanList,
  planDetailListWithUserId,
};
