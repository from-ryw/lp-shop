package fromryw.lpshop.item.controller;

import fromryw.lpshop.item.dto.ItemRead;
import fromryw.lpshop.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * 상품 목록 전체 조회
     * @return
     */
    @GetMapping
    public ResponseEntity<?> readAll() {
        List<ItemRead> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    /**
     * 특정 상품 조회
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Integer id) {
        // 특정 상품 조회 조회
        ItemRead item = itemService.findById(id);

        // 특정 상품이 없는 경우
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
