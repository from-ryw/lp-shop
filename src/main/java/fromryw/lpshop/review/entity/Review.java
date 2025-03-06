package fromryw.lpshop.review.entity;

import fromryw.lpshop.common.entity.BaseEntity;
import fromryw.lpshop.review.dto.ReviewRead;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer memberId;

    @Column(nullable = false)
    private Integer orderId;

    @Column(nullable = false)
    private Integer itemId;

    @Column(nullable = false)
    private Integer rating;

    @Lob
    @Column
    private String comment;

    public Review(Integer memberId, Integer orderId, Integer itemId, Integer rating, String comment) {
        this.memberId = memberId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.rating = rating;
        this.comment = comment;
    }

    // 리뷰 조회 DTO(ReviewRead)로 변환
    public ReviewRead toRead() {
        return ReviewRead.builder()
                .memberId(memberId)
                .orderId(orderId)
                .itemId(itemId)
                .rating(rating)
                .comment(comment)
                .updatedAt(getUpdatedAt())
                .build();
    }
}
