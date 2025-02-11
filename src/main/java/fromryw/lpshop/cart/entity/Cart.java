package fromryw.lpshop.cart.entity;

import fromryw.lpshop.cart.dto.CartRead;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer memberId;

    @Column(nullable = false)
    private Integer itemId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

//    @Column(nullable = false, updatable = false)
//    private Integer createdBy;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;

//    @Column(nullable = false)
//    private Integer updatedBy;

    public Cart() {
    }

    public Cart(Integer memberId, Integer itemId) {
        this.memberId = memberId;
        this.itemId = itemId;
    }

    // 장바구니 조회 DTO(CartRead)로 변환
    public CartRead toRead() {
        return CartRead.builder()
                .id(id)
                .itemId(itemId)
                .build();
    }
}
