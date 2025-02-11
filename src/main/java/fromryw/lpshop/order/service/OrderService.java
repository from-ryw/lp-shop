package fromryw.lpshop.order.service;

import fromryw.lpshop.order.dto.OrderRead;
import fromryw.lpshop.order.dto.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    // 주문 목록 조회
//    List<OrderRead> findAll(Integer memberId);
    // 주문 목록 조회(페이지네이션)
    Page<OrderRead> findAll(Integer memberId, Pageable pageable);

    // 주문 상세 조회
    OrderRead find(Integer id, Integer memberId);

    // 주문 내역 저장
    void order(OrderRequest orderReq, Integer memberId);
}
