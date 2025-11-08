package com.github.veloproject.socialmediaservices.infrastructure.tables;

import com.github.veloproject.socialmediaservices.domain.valueObjects.CommunityMemberId;
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
@Table(
        name = "tb_community_members"
)
@IdClass(CommunityMemberId.class)
public class CommunityMembersTable {
    @Id
    @Column(name = "id_community", nullable = false)
    private Integer communityId;

    @Id
    @Column(name = "id_user", nullable = false)
    private Integer userId;

    @CreationTimestamp
    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;
}
