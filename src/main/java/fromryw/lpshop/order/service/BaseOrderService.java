package fromryw.lpshop.order.service;

import fromryw.lpshop.cart.service.CartService;
import fromryw.lpshop.common.util.EncryptionUtils;
import fromryw.lpshop.item.dto.ItemRead;
import fromryw.lpshop.item.service.ItemService;
import fromryw.lpshop.order.dto.OrderRead;
import fromryw.lpshop.order.dto.OrderRequest;
import fromryw.lpshop.order.entity.Order;
import fromryw.lpshop.order.entity.OrderItem;
import fromryw.lpshop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final ItemService itemService;
    private final CartService cartService;

    // 주문 목록 조회
//    @Override
//    public List<OrderRead> findAll(Integer memberId) {
//        // 결과값을 주문 조회 DTO(OrderRead)로 변환 후 리턴
//        return orderRepository.findAllByMemberIdOrderByIdDesc(memberId).stream().map(Order::toRead).toList();
//    }
    // 주문 목록 조회(페이지네이션)
    @Override
    public Page<OrderRead> findAll(Integer memberId, Pageable pageable) {
        // 결과값을 주문 조회 DTO(OrderRead)로 변환 후 리턴
        Page<Order> orders = orderRepository.findAllByMemberIdOrderByIdDesc(memberId, pageable);
        return orders.map(Order::toRead);
    }

    // 주문 상세 조회
    @Override
    public OrderRead find(Integer id, Integer memberId) {
        Optional<Order> orderOptional = orderRepository.findByIdAndMemberId(id, memberId);

        if (orderOptional.isPresent()) {
            // 주문 조회 DTO(OrderRead)로 변환
            OrderRead order = orderOptional.get().toRead();

            // 주문 상품 목록 조회
            List<OrderItem> orderItems = orderItemService.findAll(order.getId());

            // 주문 상품 목록의 상품 아이디를 추출
            List<Integer> orderItemIds = orderItems.stream().map(OrderItem::getItemId).toList();

            // 주문 상품 리스트의 상품 ID에 해당하는 상품 목록을 조회
            List<ItemRead> items = itemService.findAll(orderItemIds);

            // 응답에 상품 리스트 데이터를 설정
            order.setItems(items);

            return order;
        }

        return null;
    }

    // 주문 내용 저장
    @Override
    public Integer order(OrderRequest orderReq, Integer memberId) {
        // 주문 상품의 최종 결제 금액을 계산
        List<ItemRead> items = itemService.findAll(orderReq.getItemIds());
        long amount = 0L;

        for (ItemRead item : items) {
            amount += item.getPrice() - item.getPrice().longValue() * item.getDiscountPer() / 100;
        }

        // 주문 요청에 최종 결제 금액 입력
        orderReq.setAmount(amount);

        // 결제 수단이 카드일 때 카드 번호 암호화
        if ("card".equals(orderReq.getPayment())) {
            orderReq.setCardNumber(EncryptionUtils.encrypt(orderReq.getCardNumber()));
        }

        // 주문 저장
        Order order = orderRepository.save(orderReq.toEntity(memberId));

        // 주문 상품 데이터 생성
        ArrayList<OrderItem> newOrderItems = new ArrayList<>();

        // 상품 아이디만큼 주문 상품 추가
        orderReq.getItemIds().forEach((itemId) -> {
            OrderItem newOrderItem = new OrderItem(order.getId(), itemId, memberId);
            newOrderItems.add(newOrderItem);
        });

        // 주문 상품 데이터 저장
        orderItemService.saveAll(newOrderItems);

        // 장바구니 데이터 삭제(특정 회원)
        if ("cart".equals(orderReq.getOrderType())) {
            cartService.removeAll(order.getMemberId());
        }

        return order.getId();
    }
}
