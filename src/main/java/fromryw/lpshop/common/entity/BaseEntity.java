package fromryw.lpshop.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedBy
    @Column(nullable = false, updatable = true)
    private Integer createdBy;

    @LastModifiedBy
    @Column(nullable = false, updatable = true)
    private Integer updatedBy;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (this.createdBy == null || this.createdBy == 0) {
            this.createdBy = 0; // 기본값 설정
        }
        if (this.updatedBy == null || this.updatedBy == 0) {
            this.updatedBy = this.createdBy; // createdBy와 동일하게 설정
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();

        if (this.updatedBy == null || this.updatedBy == 0) {
            this.updatedBy = this.createdBy; // updatedBy가 0이면 createdBy 값 사용
        }
    }
}

