package fromryw.lpshop.review.repository;

import fromryw.lpshop.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByItemIdOrderByUpdatedAtDesc(Integer itemId);
}
