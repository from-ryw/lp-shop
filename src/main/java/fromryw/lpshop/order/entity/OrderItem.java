package fromryw.lpshop.order.entity;

import fromryw.lpshop.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer orderId;

    @Column(nullable = false)
    private Integer itemId;

    @Column
    private Integer reviewId;

    public OrderItem(Integer orderId, Integer itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }
}
