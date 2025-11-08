CREATE TABLE tb_user_followers(
    id_user INT NOT NULL,
    id_follower INT NOT NULL,
    followed_at TIMESTAMP WITHOUT TIME ZONE,
    PRIMARY KEY (id_user, id_follower)
);
