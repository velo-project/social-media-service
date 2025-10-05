CREATE TABLE tb_user_followers(
    id_user INT NOT NULL,
    id_following INT NOT NULL,
    PRIMARY KEY (id_user, id_following)
);
