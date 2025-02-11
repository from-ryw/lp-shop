// 인터셉터 적용을 위해 axios 주석 처리 후 httpRequester 사용
// import axios from "axios";
import httpRequester from "@/libs/httpRequester.js";

// 회원가입
export const join = (args) => {
    // return axios.post("/v1/api/account/join", args).catch(e => e.response);
    return httpRequester.post("/v1/api/account/join", args).catch(e => e.response);
};

// 로그인
export const login = (args) => {
    // return axios.post("/v1/api/account/login", args).catch(e => e.response);
    return httpRequester.post("/v1/api/account/login", args).catch(e => e.response);
}

// 로그인 여부 확인
export const check = () => {
    // return axios.get("/v1/api/account/check").catch(e => e.response);
    return httpRequester.get("/v1/api/account/check").catch(e => e.response);
}

// 로그아웃
export const logout = () => {
    // return axios.post("/v1/api/account/logout").catch(e => e.response);
    return httpRequester.post("/v1/api/account/logout").catch(e => e.response);
}