package fromryw.lpshop.order.repository;

import fromryw.lpshop.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    // 주문 상품 목록 조회(id 기준 정렬)
    List<OrderItem> findAllByOrderId(Integer orderId);

    // 주문 상품 조회(특정 주문의 특정 상품)
    OrderItem findByOrderIdAndItemId(Integer orderId, Integer itemId);
}
