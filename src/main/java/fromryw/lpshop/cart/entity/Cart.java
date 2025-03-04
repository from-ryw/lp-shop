package fromryw.lpshop.cart.entity;

import fromryw.lpshop.cart.dto.CartRead;
import fromryw.lpshop.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer memberId;

    @Column(nullable = false)
    private Integer itemId;

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
