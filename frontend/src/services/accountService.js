// 인터셉터 적용을 위해 axios 주석 처리 후 httpRequester 사용
import httpRequester from "@/libs/httpRequester.js";

// 회원가입
export const join = (args) => {
    return httpRequester.post("/api/accounts/join", args).catch(e => e.response);
};

// 로그인
export const login = (args) => {
    return httpRequester.post("/api/accounts/login", args).catch(e => e.response);
}

// 로그인 여부 확인
export const check = () => {
    return httpRequester.get("/api/accounts/check").catch(e => e.response);
}

// 로그아웃
export const logout = () => {
    return httpRequester.post("/api/accounts/logout").catch(e => e.response);
}