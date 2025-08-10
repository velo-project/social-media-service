package com.github.veloproject.socialmediaservices.infrastructure.tables;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "tb_communities"
)
public class CommunitiesTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(
            name = "id_community"
    )
    private Integer id;

    @Column(
            name = "name_community",
            length = 50
    )
    private String name;

    @Column(
            name = "description_community",
            length = 255
    )
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "created_by"
    )
    private UsersTable createdBy;

    @CreationTimestamp
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;
}
