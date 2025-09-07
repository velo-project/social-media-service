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
        name = "tb_communities"
)
public class CommunitiesTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(
            name = "created_by"
    )
    private Integer createdBy;

    @CreationTimestamp
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    public CommunitiesTable(Integer id, String name, String description, Integer createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}
