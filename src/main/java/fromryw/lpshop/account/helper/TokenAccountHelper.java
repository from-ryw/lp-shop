package fromryw.lpshop.account.helper;

import fromryw.lpshop.account.dto.AccountJoinRequest;
import fromryw.lpshop.account.dto.AccountLoginRequest;
import fromryw.lpshop.account.etc.AccountConstants;
import fromryw.lpshop.block.service.BlockService;
import fromryw.lpshop.common.util.HttpUtils;
import fromryw.lpshop.common.util.TokenUtils;
import fromryw.lpshop.member.entity.Member;
import fromryw.lpshop.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Primary // 구현체의 우선 순위를 나타내는 어노테이션 => AccountHelper의 구현체가 2개(SessionAccountHelper, TokenAccountHelper)가 되는데 해당 구현체가 우선적으로 의존성 주입됨
@RequiredArgsConstructor
public class TokenAccountHelper implements AccountHelper {

    private final MemberService memberService;
    private final BlockService blockService;

    // 액세스 토큰 조회
    private String getAccessToken(HttpServletRequest req) {
        return HttpUtils.getBearerToken(req);
    }

    // 리프레시 토큰 조회
    private String getRefreshToken(HttpServletRequest req) {
        return HttpUtils.getCookieValue(req, AccountConstants.REFRESH_TOKEN_NAME);
    }

    // 회원 아이디 조회
    private Integer getMemberId(String token) {
        if (TokenUtils.isValid(token)) {
            Map<String, Object> tokenBody = TokenUtils.getBody(token);
            return (Integer) tokenBody.get(AccountConstants.MEMBER_ID_NAME);
        }

        return null;
    }

    // 회원가입
    @Override
    public void join(AccountJoinRequest joinReq, Integer memberId) {
        memberService.save(joinReq.getName(), joinReq.getLoginId(), joinReq.getLoginPw());
    }

    // 로그인
    @Override
    public String login(AccountLoginRequest joinReq, HttpServletRequest req, HttpServletResponse res) {
        Member member = memberService.find(joinReq.getLoginId(), joinReq.getLoginPw());

        // 회원 데이터가 없으면
        if (member == null) {
            return null;
        }

        // 회원 아이디
        Integer memberId = member.getId();

        // 액세스 토큰 발급
        String accessToken = TokenUtils.generate(AccountConstants.ACCESS_TOKEN_NAME, AccountConstants.MEMBER_ID_NAME, memberId, AccountConstants.ACCESS_TOKEN_EXP_MINUTES);

        // 리프레스 토큰 발급
        String refreshToken = TokenUtils.generate(AccountConstants.REFRESH_TOKEN_NAME, AccountConstants.MEMBER_ID_NAME, memberId, AccountConstants.REFRESH_TOKEN_EXP_MINUTES);

        // 리프레시 토큰 쿠키 저장(웹 브라우저 종료 시 삭제되도록 하기 위해 유효 시간 0으로 입력)
        HttpUtils.setCookie(res, AccountConstants.REFRESH_TOKEN_NAME, refreshToken, 0);

        return accessToken;
    }

    // 회원 아이디 조회
    @Override
    public Integer getMemberId(HttpServletRequest req) {
        // 액세스 토큰으로 회원 아이디 조회
        return this.getMemberId(getAccessToken(req));
    }

    // 로그인 여부 확인
    @Override
    public boolean isLoggedIn(HttpServletRequest req) {
        // 액세스 토큰이 유효하다면
        if (TokenUtils.isValid(getAccessToken(req))) {
            return true;
        }

        // 리프레시 토큰 조회
        String refreshToken = getRefreshToken(req);

        // 리프레시 토큰의 유효성 확인(토큰 차단 데이터가 없는지도 같이 확인)
        return TokenUtils.isValid(refreshToken) && !blockService.has(refreshToken);
    }

    // 로그아웃
    @Override
    public void logout(HttpServletRequest req, HttpServletResponse res) {
        // 리프레시 토큰 조회
        String refreshToken = getRefreshToken(req);
        
        // 리프레시 토큰이 있다면
        if (refreshToken != null) {
            // 쿠키에서 삭제
            HttpUtils.removeCookie(res, AccountConstants.REFRESH_TOKEN_NAME);
        
            // 토큰 차단 데이터에 해당 토큰이 없다면
            if (!blockService.has(refreshToken)) {
                // 차단 토큰으로 추가
                blockService.add(refreshToken);
            }
        }
    }
}
