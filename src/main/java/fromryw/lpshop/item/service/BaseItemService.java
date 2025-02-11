package fromryw.lpshop.item.service;

import fromryw.lpshop.item.dto.ItemRead;
import fromryw.lpshop.item.entity.Item;
import fromryw.lpshop.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseItemService implements ItemService {

    private final ItemRepository itemRepository;

    // 전체 상품 목록 조회
    @Override
    public List<ItemRead> findAll() {
        // 결과값들을 상품 조회 DTO(ItemRead)로 변환 후 리턴
        return itemRepository.findAll().stream().map(Item::toRead).toList();
    }

    // 상품 목록 조회(특정 아이디 리스트로 조회)
    @Override
    public List<ItemRead> findAll(List<Integer> ids) {
        // 결과값들을 상품 조회 DTO(ItemRead)로 변환 후 리턴
        return itemRepository.findAllByIdIn(ids).stream().map(Item::toRead).toList();
    }
}
