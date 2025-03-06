package fromryw.lpshop.review.controller;

import fromryw.lpshop.account.helper.AccountHelper;
import fromryw.lpshop.review.dto.ReviewRead;
import fromryw.lpshop.review.dto.ReviewRequest;
import fromryw.lpshop.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final AccountHelper accountHelper;

    /**
     * 로그인 회원의 주문 상품에 대한 리뷰 저장
     * @param req
     * @param reviewReq
     * @return
     */
    @PostMapping
    public ResponseEntity<?> add(HttpServletRequest req, @RequestBody ReviewRequest reviewReq) {
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        // 리뷰 저장
        ReviewRead review = reviewService.save(reviewReq, memberId);

        return new ResponseEntity<>(Collections.singletonMap("review", review), HttpStatus.OK);
    }
}
