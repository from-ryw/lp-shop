package fromryw.lpshop.order.service;

import fromryw.lpshop.order.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    // 주문 상품 목록 조회
    List<OrderItem> findAll(Integer orderId);

    // 주문 상품 조회(특정 주문의 특정 상품)
    OrderItem findByOrderIdAndItemId(Integer orderId, Integer itemId);

    // 주문 상품 데이터 목록 전체 저장
    void saveAll(List<OrderItem> orderItems);

    // 주문 상품 데이터 저장
    void save(OrderItem orderItem);
}
