CREATE EXTENSION IF NOT EXISTS vector;

ALTER TABLE tb_communities
    ADD COLUMN embeddings_community vector(1536);