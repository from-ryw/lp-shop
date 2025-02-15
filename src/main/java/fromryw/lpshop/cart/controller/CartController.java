package fromryw.lpshop.cart.controller;

import fromryw.lpshop.account.helper.AccountHelper;
import fromryw.lpshop.cart.dto.CartRead;
import fromryw.lpshop.cart.dto.CartRequest;
import fromryw.lpshop.cart.service.CartService;
import fromryw.lpshop.item.dto.ItemRead;
import fromryw.lpshop.item.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final AccountHelper accountHelper;
    private final CartService cartService;
    private final ItemService itemService;

    /**
     * 로그인 회원의 장바구니에 있는 전체 상품 목록 조회
     * @param req
     * @return
     */
    @GetMapping("/items")
    public ResponseEntity<?> readAll(HttpServletRequest req) {
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        // 장바구니 목록 조회
        List<CartRead> carts = cartService.findAll(memberId);

        // 장바구니 안에 있는 상품 아이디로 상품을 조회
        List<Integer> itemIds = carts.stream().map(CartRead::getItemId).toList();
        List<ItemRead> items = itemService.findAll(itemIds);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    /**
     * 로그인 회원의 장바구니에 상품 데이터를 추가
     * @param req
     * @param cartReq
     * @return
     */
    @PostMapping
    public ResponseEntity<?> push(HttpServletRequest req, @RequestBody CartRequest cartReq) {
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        // 장바구니 데이터 조회(특정 상품)
        CartRead cart = cartService.find(memberId, cartReq.getItemId());

        // 장바구니 데이터가 없다면
        if (cart == null) {
            cartService.save(cartReq.toEntity(memberId));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 로그인 회원의 장바구니의 특정 상품 데이터를 삭제
     * @param req
     * @param itemId
     * @return
     */
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<?> remove(HttpServletRequest req, @PathVariable("itemId") Integer itemId) {
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        cartService.remove(memberId, itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
