package fromryw.lpshop.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true) // 기존 값 유지하며 일부 필드 변경 가능
public class ReviewRead {

    private Integer memberId;
    private Integer orderId;
    private Integer itemId;
    private Integer rating;
    private String comment;
    private LocalDateTime updatedAt;
    private String memberName;
}
