CREATE TABLE tb_comments
(
    id_comment SERIAL PRIMARY KEY,
    id_post INT NOT NULL,
    commented_by INT NOT NULL,
    content_comment TEXT NOT NULL,
    commented_at TIMESTAMP WITHOUT TIME ZONE,
    FOREIGN KEY (id_post) REFERENCES tb_posts(id_post) ON DELETE CASCADE
);
