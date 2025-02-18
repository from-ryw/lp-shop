package fromryw.lpshop.order.controller;

import fromryw.lpshop.account.helper.AccountHelper;
import fromryw.lpshop.order.dto.OrderRead;
import fromryw.lpshop.order.dto.OrderRequest;
import fromryw.lpshop.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final AccountHelper accountHelper;
    private final OrderService orderService;

    /**
     * 로그인 회원이 전체 주문 목록 조회(페이지네이션)
     * @param req
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<?> readAll(HttpServletRequest req, Pageable pageable) {
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        // 주문 목록
        Page<OrderRead> orders = orderService.findAll(memberId, pageable);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * 로그인 회원의 특정 주문 내역 조회
     * @param req
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> read(HttpServletRequest req, @PathVariable("id") Integer id) {
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        // 주문 조회
        OrderRead order = orderService.find(id, memberId);

        // 주문 데이터가 없는 경우
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    /**
     * 로그인 회원의 주문 추가
     * @param req
     * @param orderReq
     * @return
     */
    @PostMapping
    public ResponseEntity<?> add(HttpServletRequest req, @RequestBody OrderRequest orderReq) {
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        // 주문 입력
        Integer orderId = orderService.order(orderReq, memberId);

        return new ResponseEntity<>(Collections.singletonMap("id", orderId), HttpStatus.OK);
    }
}
