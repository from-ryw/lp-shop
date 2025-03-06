package fromryw.lpshop.review.service;

import fromryw.lpshop.member.entity.Member;
import fromryw.lpshop.member.service.BaseMemberService;
import fromryw.lpshop.order.entity.OrderItem;
import fromryw.lpshop.order.service.BaseOrderItemService;
import fromryw.lpshop.review.dto.ReviewRead;
import fromryw.lpshop.review.dto.ReviewRequest;
import fromryw.lpshop.review.entity.Review;
import fromryw.lpshop.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BaseOrderItemService orderItemService;
    private final BaseMemberService memberService;

    // 리뷰 조회
    public ReviewRead findById(Integer id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        return reviewOptional.map(Review::toRead).orElse(null);
    }

    // 리뷰 조회 (itemId 기준 전체 조회)
    public List<ReviewRead> findAllByItemId(Integer itemId) {
        // 리뷰 조회
        List<Review> reviews = reviewRepository.findAllByItemIdOrderByUpdatedAtDesc(itemId);

        // 리뷰 작성자의 memberId 목록 추출
        List<Integer> memberIds = reviews.stream()
                .map(Review::getMemberId)
                .distinct()  // 중복 제거
                .toList();

        // 회원 정보 한 번의 쿼리로 조회 (Batch Fetching)
        Map<Integer, Member> memberMap = memberService.findAllByIds(memberIds).stream()
                .collect(Collectors.toMap(Member::getId, member -> member));

        // Review 엔티티 -> ReviewRead DTO 변환하면서 memberName 추가
        return reviews.stream()
                .map(review -> ReviewRead.builder()
                        .memberId(review.getMemberId())
                        .orderId(review.getOrderId())
                        .itemId(review.getItemId())
                        .rating(review.getRating())
                        .comment(review.getComment())
                        .updatedAt(review.getUpdatedAt())
                        .memberName(
                                Optional.ofNullable(memberMap.get(review.getMemberId()))
                                        .map(Member::getName)
                                        .map(name -> {
                                            int length = name.length();
                                            if (length == 2) return name.charAt(0) + "*";
                                            else if (length == 3) return name.charAt(0) + "*" + name.charAt(2);
                                            else return name.charAt(0) + "*".repeat(length - 2) + name.charAt(length - 1);
                                        })
                                        .orElse("*") // 회원 정보가 없을 경우 기본값 처리
                        )
                        .build())
                .toList();
    }
    
    // 리뷰 저장
    public ReviewRead save(ReviewRequest reviewReq, Integer memberId) {
        // 리뷰 저장
        Review review = reviewRepository.save(reviewReq.toEntity(memberId));

        // 리뷰 대상 주문 상품 조회
        OrderItem item = orderItemService.findByOrderIdAndItemId(reviewReq.getOrderId(), reviewReq.getItemId());
        Integer reviewId = review.getId();
        item.setReviewId(reviewId);

        // 리뷰 대상 주문 상품에 reviewId Update
        orderItemService.save(item);

        return review.toRead();
    }
}
