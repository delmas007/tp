ALTER TABLE personne
    ADD CONSTRAINT fk_personne_departement
        FOREIGN KEY (departement)
            REFERENCES departement(id)
            ON DELETE SET NULL
            ON UPDATE CASCADE;