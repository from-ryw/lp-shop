// 인터셉터 적용을 위해 axios 주석 처리 후 httpRequester 사용
import httpRequester from "@/libs/httpRequester.js";

// 리뷰 조회
export const getReview = (id) => {
    return httpRequester.get(`/api/reviews/${id}`).catch(e => e.response);
}

// 리뷰 작성
export const addReview = (args) => {
    return httpRequester.post("/api/reviews", args).catch(e => e.response);
}
