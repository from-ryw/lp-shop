package fromryw.lpshop.order.repository;

import fromryw.lpshop.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // 주문 목록 조회(id 기준 역순 정렬)
//    List<Order> findAllByMemberIdOrderByIdDesc(Integer memberId);
    // 주문 목록 조회(페이지네이션)
    Page<Order> findAllByMemberIdOrderByIdDesc(Integer memberId, Pageable pageable);

    // 주문 정보 조회(특정 아이디 및 특정 회원)
    Optional<Order> findByIdAndMemberId(Integer id, Integer memberId);
}
