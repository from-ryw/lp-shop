package fromryw.lpshop.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemRead {

    private Integer id;
    private String name;
    private String artist;
    private String imgPath;
    private Integer price;
    private Integer discountPer;
}
