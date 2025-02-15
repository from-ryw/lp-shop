import axios from "axios";
import {useAccountStore} from "@/stores/account.js";

const instance = axios.create();

// 인터셉터(응답 값을 받았을 때 실행되는 인터셉터 메서드)
instance.interceptors.response.use((res) => {
    return res;
}, async (err) => {
    switch (err.response.status) {
        case 400:
            window.alert("잘못된 요청입니다.");
            break;


        case 401:
            const config = err.config;

            // 재요청 여부
            if (config.retried) {
                window.alert("권한이 없습니다.");
                window.location.replace("/");
                return;
            }

            // 액세스 토큰이 만료된 것일도 있으므로 (쿠키에 있는) 리프레시 토큰으로 액세스 토큰 요청
            const res = await axios.get("/api/accounts/token");

            // 액세스 토큰
            const accessToken = res.data;

            // 계정 스토어
            const accountsStore = useAccountStore();

            // 계정 스토어의 액세스 토큰 변경
            accountsStore.setAccessToken(accessToken);

            // 요청 액세스 토큰 교체
            config.headers.authorization = `Bearer ${accountsStore.accessToken}`;

            // 중복 재요청 방지를 위한 프로퍼티 추가
            config.retried = true;

            // 재요청
            return instance(config);


        case 500:
            window.alert("오류가 있습니다. 관리자에게 문의해주세요.");
            break;
    }

    return Promise.reject(err);
});


// HTTP 요청 설정 생성
const generateConfig = () => {
    // 계정 스토어
    const accountsStore = useAccountStore();

    if (accountsStore.accessToken) {
        return {
            headers: {authorization: `Bearer ${accountsStore.accessToken}`}
        };
    }

    return {};
};

export default {
    get(url, params) {
        const config = generateConfig();
        config.params = params;
        return instance.get(url, config);
    },
    post(url, params) {
        return instance.post(url, params, generateConfig());
    },
    put(url, params) {
        return instance.put(url, params, generateConfig());
    },
    delete(url) {
        return instance.delete(url, generateConfig());
    },
};