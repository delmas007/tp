-- Associer Dupont Jean à Informatique (id = 1 si c'est le premier département)
UPDATE personne
SET departement = (SELECT id FROM departement WHERE code = 101)
WHERE nom = 'Dupont' AND prenom = 'Jean';

-- Associer Martin Claire à Mathématiques
UPDATE personne
SET departement = (SELECT id FROM departement WHERE code = 102)
WHERE nom = 'Martin' AND prenom = 'Claire';

-- Associer Lefevre Sophie à Physique
UPDATE personne
SET departement = (SELECT id FROM departement WHERE code = 103)
WHERE nom = 'Lefevre' AND prenom = 'Sophie';

-- Associer Moreau Luc à Informatique
UPDATE personne
SET departement = (SELECT id FROM departement WHERE code = 101)
WHERE nom = 'Moreau' AND prenom = 'Luc';
