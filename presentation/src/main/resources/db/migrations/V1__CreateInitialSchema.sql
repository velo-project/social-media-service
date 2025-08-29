CREATE TABLE tb_posts
(
    id_post SERIAL PRIMARY KEY,
    content_post TEXT NOT NULL,
    posted_by REFERENCES tb_users(id_user),
    id_community REFERENCES tb_communities(id_community),
    posted_at TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE tb_hashtags
(
    id_hashtag SERIAL PRIMARY KEY,
    tag_hashtag VARCHAR(25) UNIQUE NOT NULL,
    usage_count_hashtag INTEGER NOT NULL
);

CREATE TABLE tb_post_hashtags
(
    id_post INT NOT NULL,
    id_hashtag INT NOT NULL,
    PRIMARY KEY(id_post, id_hashtag),
    FOREIGN KEY (id_post) REFERENCES tb_posts(id_post) ON DELETE CASCADE,
    FOREIGN KEY (id_hashtag) REFERENCES tb_hashtags(id_hashtag) ON DELETE CASCADE
);

CREATE TABLE tb_communities
(
    id_community SERIAL PRIMARY KEY,
    name_community VARCHAR(50) NOT NULL,
    description_community VARCHAR(255),
    created_by REFERENCES tb_users(id_user),
    created_at TIMESTAMP WITHOUT TIME ZONE
);
