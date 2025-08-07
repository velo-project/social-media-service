-- Tabela de usuários
CREATE TABLE tb_users
(
    id_user                INT PRIMARY KEY AUTO_INCREMENT,
    name                   VARCHAR(100) NOT NULL,
    email                  VARCHAR(100) NOT NULL UNIQUE,
    password               VARCHAR(255) NOT NULL,
    community_id_community INT
);

-- Tabela de comunidades
CREATE TABLE tb_community
(
    id_community INT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(100) NOT NULL,
    description  TEXT,
    created_by   INT          NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES tb_users (id_user)
);

-- Adicionando a chave estrangeira que estava faltando na tabela de usuários
ALTER TABLE tb_users
    ADD CONSTRAINT fk_user_community
        FOREIGN KEY (community_id_community) REFERENCES tb_community (id_community);

-- Tabela de posts
CREATE TABLE tb_posts
(
    id_post      INT PRIMARY KEY AUTO_INCREMENT,
    content      TEXT NOT NULL,
    community_id INT,
    user_id      INT  NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (community_id) REFERENCES tb_community (id_community),
    FOREIGN KEY (user_id) REFERENCES tb_users (id_user)
);

-- Tabela de comentários
CREATE TABLE tb_comment
(
    id_comment INT PRIMARY KEY AUTO_INCREMENT,
    content    TEXT NOT NULL,
    post_id    INT  NOT NULL,
    user_id    INT  NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES tb_posts (id_post),
    FOREIGN KEY (user_id) REFERENCES tb_users (id_user)
);

-- Tabela de hashtags
CREATE TABLE tb_hashtag
(
    id_hashtag  INT PRIMARY KEY AUTO_INCREMENT,
    tag         VARCHAR(50) UNIQUE NOT NULL,
    usage_count INT       DEFAULT 1,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de associação entre posts e hashtags
CREATE TABLE tb_post_hashtag
(
    post_id    INT NOT NULL,
    hashtag_id INT NOT NULL,
    PRIMARY KEY (post_id, hashtag_id),
    FOREIGN KEY (post_id) REFERENCES tb_posts (id_post),
    FOREIGN KEY (hashtag_id) REFERENCES tb_hashtag (id_hashtag)
);

-- Tabela de likes em posts
CREATE TABLE tb_like
(
    user_id    INT NOT NULL,
    post_id    INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, post_id),
    FOREIGN KEY (user_id) REFERENCES tb_users (id_user),
    FOREIGN KEY (post_id) REFERENCES tb_posts (id_post)
);