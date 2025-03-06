package fromryw.lpshop.review.dto;

import fromryw.lpshop.item.dto.ItemRead;
import fromryw.lpshop.review.entity.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {

    private Integer memberId;
    private Integer orderId;
    private Integer itemId;
    private Integer rating;
    private String comment;

    // 엔티티 객체로 변환
    public Review toEntity(Integer memberId) {
        return new Review(memberId, orderId, itemId, rating, comment);
    }
}
