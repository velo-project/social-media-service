package com.github.veloproject.socialmediaservices.infrastructure.tables;

import com.github.veloproject.socialmediaservices.domain.valueObjects.PostLikeId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@IdClass(PostLikeId.class)
@Entity
@Table(name = "tb_post_likes")
public class PostLikesTable {
    @Id
    @Column(name = "id_post", nullable = false)
    private Integer postId;

    @Id
    @Column(name = "liked_by", nullable = false)
    private Integer likedBy;

    @CreationTimestamp
    @Column(name = "liked_at", nullable = false)
    private LocalDateTime likedAt;
}
