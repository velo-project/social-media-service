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
        name = "tb_comments"
)
public class CommentsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment", nullable = false)
    private Integer id;

    @Column(name = "id_post", nullable = false)
    private Integer postId;

    @Column(name = "commented_by", nullable = false)
    private Integer commentedBy;

    @Column(name = "content_comment", nullable = false, columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "commented_at", nullable = false, updatable = false)
    private LocalDateTime commentedAt;

    public CommentsTable(Integer id, Integer postId, Integer commentedBy, String content, LocalDateTime commentedAt) {
        this.id = id;
        this.postId = postId;
        this.commentedBy = commentedBy;
        this.content = content;
        this.commentedAt = commentedAt;
    }
}
