package fromryw.lpshop.interceptor;

import fromryw.lpshop.account.helper.AccountHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class ApiInterceptor implements HandlerInterceptor {

    private final AccountHelper accountHelper;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        // 로그인 회원 아이디가 없으면
        if (accountHelper.getMemberId(req) == null) {
            res.setStatus(401); // 401: Unauthorized: 권한 없음
            return false;
        }

        return true;
    }
}
