package fromryw.lpshop.item.specification;

import fromryw.lpshop.item.entity.Item;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification {

    // 검색어 (상품명, 아티스트명)
    public static Specification<Item> containsSearchKeyword(String searchKeyword) {
        return (root, query, criteriaBuilder) -> {
            if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
                return null; // 검색어가 없으면 조건 적용 X
            }
            String likePattern = "%" +  searchKeyword + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), likePattern),
                    criteriaBuilder.like(root.get("artist"), likePattern)
            );
        };
    }

    // 할인율 범위 (min ~ max)
    public static Specification<Item> hasDiscountPerInRange(Integer min, Integer max) {
        return (root, query, criteriaBuilder) -> {
            if (min == null || max == null) {
                return null; // 검색어가 없으면 조건 적용 X
            }
            return criteriaBuilder.between(root.get("discountPer"), min, max);
        };
    }

    // 정렬
    public static Specification<Item> sortBy(String sort) {
        return (root, query, criteriaBuilder) -> {
            if ("discount".equals(sort)) { // 할인율 높은 순
                query.orderBy(criteriaBuilder.desc(root.get("discountRate")));
            } else if ("price_asc".equals(sort)) { // 낮은 가격 순
                query.orderBy(criteriaBuilder.asc(root.get("price")));
            } else if ("price_desc".equals(sort)) { // 높은 가격 순
                query.orderBy(criteriaBuilder.desc(root.get("price")));
            } else {
                query.orderBy(criteriaBuilder.desc(root.get("createdAt"))); // 최신순
            }
            return criteriaBuilder.conjunction();
        };
    }
}
