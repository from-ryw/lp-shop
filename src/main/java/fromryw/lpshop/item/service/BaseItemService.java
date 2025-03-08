package fromryw.lpshop.item.service;

import fromryw.lpshop.item.dto.DiscountPerRange;
import fromryw.lpshop.item.dto.ItemRead;
import fromryw.lpshop.item.entity.Item;
import fromryw.lpshop.item.repository.ItemRepository;
import fromryw.lpshop.item.specification.ItemSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // 특정 상품 조회
    @Override
    public ItemRead findById(Integer id) {
        Optional<Item> itemOptional = itemRepository.findById(id);

        // 상품 조회 DTO(ItemRead)로 변환 후 반환
        return itemOptional.map(Item::toRead).orElse(null);
    }

    // 검색조건 적용 상품 목록 조회
    @Override
    public List<ItemRead> searchItems(String searchKeyword, List<DiscountPerRange> discountPers, String sort) {
        Specification<Item> spec = Specification.where(null); // 초기값: 모든 데이터 조회

        // 검색어 필터 적용
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            spec = spec.and(ItemSpecification.containsSearchKeyword(searchKeyword));
        }

        // 할인율 필터 적용(여러 개의 범위를 `OR` 조건으로 추가)
        if (discountPers != null && !discountPers.isEmpty()) {
            Specification<Item> discountPerSpec = Specification.where(null);
            for (DiscountPerRange discountPer : discountPers) {
                System.out.println("discountPer = " + discountPer);
                discountPerSpec = discountPerSpec.or(ItemSpecification.hasDiscountPerInRange(discountPer.getMin(), discountPer.getMax()));
            }
            spec = spec.and(discountPerSpec);
        }

        // 정렬 적용
        Sort sortOption = getSortOption(sort);

        // 결과값들을 상품 조회 DTO(ItemRead)로 변환 후 리턴
        return itemRepository.findAll(spec, sortOption)
                .stream().map(Item::toRead)
                .collect(Collectors.toList());
    }

    // 정렬 (기본 최신순)
    private Sort getSortOption(String sort) {
        if ("discount".equals(sort)) { // 할인율
            return Sort.by(Sort.Direction.DESC, "discountPer");
        } else if ("price_asc".equals(sort)) { // 낮은 가격
            return Sort.by(Sort.Direction.ASC, "price");
        } else if ("price_desc".equals(sort)) { // 높은 가격
            return Sort.by(Sort.Direction.DESC, "price");
        }
        return Sort.by(Sort.Direction.DESC, "createdAt"); // 기본 정렬: 최신순
    }
}
