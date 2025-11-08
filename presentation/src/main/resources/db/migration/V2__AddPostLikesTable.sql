CREATE TABLE tb_post_likes
(
    id_post INT NOT NULL,
    liked_by INT NOT NULL,
    liked_at TIMESTAMP WITHOUT TIME ZONE,
    PRIMARY KEY(id_post, liked_by),
    FOREIGN KEY (id_post) REFERENCES tb_posts(id_post) ON DELETE CASCADE
);