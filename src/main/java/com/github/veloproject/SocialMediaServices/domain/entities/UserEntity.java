package com.github.veloproject.SocialMediaServices.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id_user;

    private String name;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "community_id_community")
    private CommunityEntity community;
}
