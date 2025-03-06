package fromryw.lpshop.order.entity;

import fromryw.lpshop.common.entity.BaseEntity;
import fromryw.lpshop.order.dto.OrderRead;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer memberId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String address;

    @Column(length = 10, nullable = false)
    private String payment;

    @Column(length = 50) // 암호화 적용을 위해 16에서 50으로 변경
    private String cardNumber;

    @Column(nullable = false)
    private Long amount;

    public Order(Integer memberId, String name, String address, String payment, String cardNumber, Long amount) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.payment = payment;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    // 주문 조회 DTO(OrderRead)로 변환
    public OrderRead toRead() {
        return OrderRead.builder()
                .id(id)
                .name(name)
                .address(address)
                .payment(payment)
                .amount(amount)
                .createdAt(getCreatedAt()) // BaseEntity에서 상속받음
                .build();
    }
}
