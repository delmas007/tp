-- V1__creation_departement_table.sql
CREATE TABLE departement (
    id BIGSERIAL PRIMARY KEY,
    code BIGINT NOT NULL,
    designation VARCHAR(255) NOT NULL
);
