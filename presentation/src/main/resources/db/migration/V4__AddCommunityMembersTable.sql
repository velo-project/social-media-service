CREATE TABLE tb_community_members
(
    id_community INT NOT NULL,
    id_user INT NOT NULL,
    joined_at TIMESTAMP WITHOUT TIME ZONE,
    PRIMARY KEY (id_community, id_user),
    FOREIGN KEY (id_community) REFERENCES tb_communities (id_community) ON DELETE CASCADE
);