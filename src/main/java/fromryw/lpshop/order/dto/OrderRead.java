package fromryw.lpshop.order.dto;

import fromryw.lpshop.item.dto.ItemRead;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderRead {

    private Integer id;
    private String name;
    private String address;
    private String payment;
    private LocalDateTime createdAt;
    private Long amount;
    private List<ItemRead> items; // 주문 요청 응답 시 상품 목록으로 사용
}
