package fromryw.lpshop.account.helper;

import fromryw.lpshop.account.dto.AccountJoinRequest;
import fromryw.lpshop.account.dto.AccountLoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AccountHelper {

    // 회원가입
    void join(AccountJoinRequest joinReq, Integer memberId);

    // 로그인
    String login(AccountLoginRequest loginReq, HttpServletRequest req, HttpServletResponse res);

    // 회원 아이디 조회
    Integer getMemberId(HttpServletRequest req);

    // 로그인 여부 확인
    boolean isLoggedIn(HttpServletRequest req);

    // 로그아웃
    void logout(HttpServletRequest req, HttpServletResponse res);
}
