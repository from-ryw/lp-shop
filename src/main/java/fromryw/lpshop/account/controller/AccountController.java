package fromryw.lpshop.account.controller;

import fromryw.lpshop.account.dto.AccountJoinRequest;
import fromryw.lpshop.account.dto.AccountLoginRequest;
import fromryw.lpshop.account.etc.AccountConstants;
import fromryw.lpshop.account.helper.AccountHelper;
import fromryw.lpshop.block.service.BlockService;
import fromryw.lpshop.common.util.HttpUtils;
import fromryw.lpshop.common.util.TokenUtils;
import fromryw.lpshop.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountHelper accountHelper;
    private final BlockService blockService;
    private final MemberService memberService;

    /**
     * 회원가입
     * @param joinReq
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity<?> join(HttpServletRequest req, @RequestBody AccountJoinRequest joinReq) {
        // 입력 값이 비어 있다면
        if (!StringUtils.hasLength(joinReq.getName())
                || !StringUtils.hasLength(joinReq.getLoginId())
                || !StringUtils.hasLength(joinReq.getLoginPw())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 중복 로그인 아이디가 있다면
        if (memberService.find(joinReq.getLoginId()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 상태코드 409(충돌 데이터가 있음)
        }

        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        accountHelper.join(joinReq, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 로그인
     * @param req
     * @param res
     * @param loginReq
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest req, HttpServletResponse res, @RequestBody AccountLoginRequest loginReq) {
        // 입력 값이 비어 있다면
        if (!StringUtils.hasLength(loginReq.getLoginId())
                || !StringUtils.hasLength(loginReq.getLoginPw())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String result = accountHelper.login(loginReq, req, res);

        // 로그인 실패 시
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 로그인 여부 조회
     * @param req
     * @return
     */
    @GetMapping("check")
    public ResponseEntity<?> check(HttpServletRequest req) {
        return new ResponseEntity<>(accountHelper.isLoggedIn(req), HttpStatus.OK);
    }

    /**
     * 로그아웃
     * @param req
     * @param res
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req, HttpServletResponse res) {
        accountHelper.logout(req, res);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 액세스 토큰 재발급
     * @param req
     * @return
     */
    @GetMapping("/token")
    public ResponseEntity<?> regenerate(HttpServletRequest req) {
        String accessToken = "";
        String refreshToken = HttpUtils.getCookieValue(req, AccountConstants.REFRESH_TOKEN_NAME);

        // 리프레시 토큰이 유효하다면
        if (StringUtils.hasLength(refreshToken) && TokenUtils.isValid(refreshToken) && !blockService.has(refreshToken)) {
            // 리프레시 토큰의 내부 값 조회
            Map<String, Object> tokenBody = TokenUtils.getBody(refreshToken);

            // 리프레시 토큰의 회원 아이디 조회
            Integer memberId = (Integer) tokenBody.get(AccountConstants.MEMBER_ID_NAME);

            // 액세스 토큰 발급
            accessToken = TokenUtils.generate(AccountConstants.ACCESS_TOKEN_NAME, AccountConstants.MEMBER_ID_NAME, memberId, AccountConstants.ACCESS_TOKEN_EXP_MINUTES);
        }

        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }
}
