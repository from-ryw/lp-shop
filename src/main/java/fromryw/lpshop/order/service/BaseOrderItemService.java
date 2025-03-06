package fromryw.lpshop.order.service;

import fromryw.lpshop.order.entity.OrderItem;
import fromryw.lpshop.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseOrderItemService implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    // 주문 목록 조회
    @Override
    public List<OrderItem> findAll(Integer orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }

    // 특정 주문 상품 데이터 조회(특정 주문의 특정 상품)
    @Override
    public OrderItem findByOrderIdAndItemId(Integer orderId, Integer itemId) {
        return orderItemRepository.findByOrderIdAndItemId(orderId, itemId);
    }

    // 주문 상품 데이터 목록 전체 저장
    @Override
    public void saveAll(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }

    // 주문 상품 데이터 저장
    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
