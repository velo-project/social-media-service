ALTER TABLE tb_posts
    ADD COLUMN deleted_post BOOLEAN;

ALTER TABLE tb_communities
    ADD COLUMN deleted_community BOOLEAN;

ALTER TABLE tb_comments
    ADD COLUMN deleted_comment BOOLEAN;