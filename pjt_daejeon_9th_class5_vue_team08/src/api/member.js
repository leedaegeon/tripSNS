import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

function registUser(user, success, fail) {
  api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
}

function modifyUser(user, success, fail) {
  api.put(`/user/modify`, JSON.stringify(user)).then(success).catch(fail);
}

function deleteUser(userid, success, fail) {
  api.delete(`/user/delete/${userid}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, registUser, modifyUser, deleteUser };
