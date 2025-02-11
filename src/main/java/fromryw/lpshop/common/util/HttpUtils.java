package fromryw.lpshop.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HttpUtils {

    // 세션 입력
    // 로그인에 성공한 사용자의 정보를 저장할 때 사용
    public static void setSession(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
    }

    // 세션 값 조회
    // 로그인 사용자의 회원의 아이디를 조회하는 목적으로 사용
    public static Object getSessionValue(HttpServletRequest req, String key) {
        return req.getSession().getAttribute(key);
    }

    // 세션 삭제
    // 로그아웃을 요청한 사용자의 세션을 삭제하는 목적으로 사용
    public static void removeSession(HttpServletRequest req, String key) {
        req.getSession().removeAttribute(key);
    }

    // 쿠키 입력
    public static void setCookie(HttpServletResponse res, String name, String value, int expSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true); // 해당 쿠키 서버에서만 접근 가능
        cookie.setPath("/");

        // 유효 시간이 0보다 크다면 유효 시간 지정
        if (expSeconds > 0) {
            cookie.setMaxAge(expSeconds);
        }

        res.addCookie(cookie);
    }

    // 쿠키 값 조회
    public static String getCookieValue(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    // 쿠키 삭제
    public static void removeCookie(HttpServletResponse res, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

    // 토큰 조회
    // OAuth 2.0 인가 프레임워크에서 사용하는 Bearer 토큰
    public static String getBearerToken(HttpServletRequest req) {
        String authorization = req.getHeader("Authorization");

        if (authorization != null) {
            return authorization.replace("Bearer", "").trim(); // Bearer 값 조회
        }

        return null;
    }
}
