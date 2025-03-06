package fromryw.lpshop.item.dto;

import fromryw.lpshop.review.dto.ReviewRead;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true) // 기존 값 유지하며 일부 필드 변경 가능
public class ItemRead {

    private Integer id;
    private String name;
    private String artist;
    private String imgPath;
    private Integer price;
    private Integer discountPer;
    private String description;
    private String descriptionImgPath;
    private ReviewRead review;
    private List<ReviewRead> reviews;
}
