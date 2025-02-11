package fromryw.lpshop.item.entity;

import fromryw.lpshop.item.dto.ItemRead;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String artist;

    @Column(length = 100, nullable = false)
    private String imgPath;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer discountPer;

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

    // 상품 조회 DTO(ItemRead)로 변환
    public ItemRead toRead() {
        return ItemRead.builder()
                .id(id)
                .name(name)
                .artist(artist)
                .imgPath(imgPath)
                .price(price)
                .discountPer(discountPer)
                .build();
    }
}
