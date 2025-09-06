-- V1__create_personne_table.sql
CREATE TABLE personne (
                          id SERIAL PRIMARY KEY,
                          nom VARCHAR(100) NOT NULL,
                          prenom VARCHAR(100) NOT NULL,
                          age INT NOT NULL
);
