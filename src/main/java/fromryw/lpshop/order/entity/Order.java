package fromryw.lpshop.order.entity;

import fromryw.lpshop.order.dto.OrderRead;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
public class Order {

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

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    private Integer createdBy;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer updatedBy;

    public Order() {
    }

    public Order(Integer memberId, String name, String address, String payment, String cardNumber, Long amount) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.payment = payment;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.createdBy = memberId;
        this.updatedBy = memberId;
    }

    // 주문 조회 DTO(OrderRead)로 변환
    public OrderRead toRead() {
        return OrderRead.builder()
                .id(id)
                .name(name)
                .address(address)
                .payment(payment)
                .amount(amount)
                .createdAt(createdAt)
                .build();
    }
}
