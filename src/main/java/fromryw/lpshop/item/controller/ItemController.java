package fromryw.lpshop.item.controller;

import fromryw.lpshop.item.dto.ItemRead;
import fromryw.lpshop.item.dto.SearchRequest;
import fromryw.lpshop.item.service.ItemService;
import fromryw.lpshop.review.dto.ReviewRead;
import fromryw.lpshop.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ReviewService reviewService;

    /**
     * 검색조건 적용된 상품 조회
     * @return
     */
    @PostMapping
    public ResponseEntity<?> searchItems(@RequestBody(required = false) SearchRequest request) {
        System.out.println("request.getDiscountPers() = " + request.getDiscountPers());
        List<ItemRead> items = itemService.searchItems(request.getSearchKeyword(), request.getDiscountPers(), request.getSort());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    /**
     * 특정 상품 조회
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Integer id) {
        // 특정 상품 조회
        ItemRead item = itemService.findById(id);

        // 특정 상품이 없는 경우
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 특정 상품의 리뷰 조회
        List<ReviewRead> reviews = reviewService.findAllByItemId(id);

        // 특정 상품의 정보와 리뷰
        ItemRead itemWithReviews = item.toBuilder()
                .reviews(reviews)
                .build();

        return new ResponseEntity<>(itemWithReviews, HttpStatus.OK);
    }
}
