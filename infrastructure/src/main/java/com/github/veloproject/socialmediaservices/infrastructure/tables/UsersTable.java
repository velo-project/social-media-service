package com.github.veloproject.socialmediaservices.infrastructure.tables;

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
        name = "tb_users"
)
public class UsersTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_user")
    private Integer id;

    @Column(
            name = "name_user",
            length = 100,
            nullable = false
    )
    private String name;

    @Column(
            name = "nickname_user",
            length = 20,
            nullable = false,
            unique = true
    )
    private String nickname;

    @Column(
            name = "banner_photo_url_user",
            columnDefinition = "TEXT"
    )
    private String bannerPhotoUrl;

    @Column(
            name = "profile_photo_url_user",
            columnDefinition = "TEXT"
    )
    private String profilePhotoUrl;

    @Column(
            name = "blocked_user"
    )
    private Boolean isBlocked;

    @Column(
            name = "deleted_user"
    )
    private Boolean isDeleted;


    @CreationTimestamp
    @Column(
            name = "registered_at"
    )
    private LocalDateTime registeredAt;

    public UsersTable(Integer id, String name, String nickname, String bannerPhotoUrl, String profilePhotoUrl, Boolean isBlocked, Boolean isDeleted, LocalDateTime registeredAt) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.bannerPhotoUrl = bannerPhotoUrl;
        this.profilePhotoUrl = profilePhotoUrl;
        this.isBlocked = isBlocked;
        this.isDeleted = isDeleted;
        this.registeredAt = registeredAt;
    }
}
