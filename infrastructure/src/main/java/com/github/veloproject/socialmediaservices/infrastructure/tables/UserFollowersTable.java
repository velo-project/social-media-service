package com.github.veloproject.socialmediaservices.infrastructure.tables;

import com.github.veloproject.socialmediaservices.domain.valueObjects.UserFollowerId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(UserFollowerId.class)
@Table(
        name = "tb_user_followers"
)
public class UserFollowersTable {
    @Id
    @Column(name = "id_user", nullable = false)
    private Integer userId;

    @Id
    @Column(name = "id_follower", nullable = false)
    private Integer followerId;

    @CreationTimestamp
    @Column(name = "followed_at", nullable = false)
    private LocalDateTime followedAt;

    public UserFollowersTable(Integer userId, Integer followerId, LocalDateTime followedAt) {
        this.userId = userId;
        this.followerId = followerId;
        this.followedAt = followedAt;
    }
}
