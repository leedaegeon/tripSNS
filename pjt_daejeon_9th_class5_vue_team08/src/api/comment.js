import { apiInstance } from "./index.js";

const api = apiInstance();

function getCommentList(planId, success) {
  api.get(`/planboard/comment/${planId}`).then(success);
}

function addComment(comment, success) {
  api.post(`/planboard/comment`, comment).then(success);
}

function modifyComment(comment, success) {
  api.put(`/planboard/comment`, JSON.stringify(comment)).then(success);
}

async function removeComment(commentNo, success, additional) {
  await api.delete(`/planboard/comment/${commentNo}`).then(success).then(additional);
}

export { getCommentList, addComment, modifyComment, removeComment };
