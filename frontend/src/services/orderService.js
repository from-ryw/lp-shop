// 인터셉터 적용을 위해 axios 주석 처리 후 httpRequester 사용
// import axios from "axios";
import httpRequester from "@/libs/httpRequester.js";

// 주문 삽입
export const addOrder = (args) => {
    // return axios.post("/v1/api/orders", args).catch(e => e.response);
    return httpRequester.post("/v1/api/orders", args).catch(e => e.response);
}

// 주문 목록 조회
export const getOrders = (args) => {
    // return axios.get("/v1/api/orders", args).catch(e => e.response);
    return httpRequester.get("/v1/api/orders", args).catch(e => e.response);
}

// 주문 상세 조회
export const getOrder = (id) => {
    // return axios.get(`/v1/api/orders/${id}`).catch(e => e.response);
    return httpRequester.get(`/v1/api/orders/${id}`).catch(e => e.response);
}