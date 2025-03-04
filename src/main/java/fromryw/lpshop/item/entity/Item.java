package fromryw.lpshop.item.entity;

import fromryw.lpshop.common.entity.BaseEntity;
import fromryw.lpshop.item.dto.ItemRead;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "items")
public class Item extends BaseEntity {

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

    @Lob
    @Column
    private String description;

    @Column(length = 100)
    private String descriptionImgPath;

    // 상품 조회 DTO(ItemRead)로 변환
    public ItemRead toRead() {
        return ItemRead.builder()
                .id(id)
                .name(name)
                .artist(artist)
                .imgPath(imgPath)
                .price(price)
                .discountPer(discountPer)
                .description(description)
                .descriptionImgPath(descriptionImgPath)
                .build();
    }
}
