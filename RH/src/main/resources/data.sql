INSERT INTO role (libelle) VALUES
('Administrateur'),
('Manager'),
('Responsable RH'),
('Recruteur'),
('Employe');

INSERT INTO departement (libelle) VALUES
('Ressources humaines'),
('Informatique'),
('Finance'),
('Marketing');

INSERT INTO poste (libelle) VALUES
('Responsable ressources humaines'),
('Manager'),
('Developpeur logiciel'),
('Comptable'),
('Charge marketing'),
('Assistant ressources humaines'),
('Ingenieur reseau');

INSERT INTO type_contrat (libelle) VALUES
('Contrat a duree indeterminee'),
('Contrat a duree determinee'),
('Stage');

INSERT INTO type_demande (libelle) VALUES
('Creation de poste'),
('Remplacement'),
('Suppression de poste');

INSERT INTO etat_demande (libelle) VALUES
('Valide'),
('Rejete'),
('En cours de traitement');

INSERT INTO employee (nom, prenom, email, id_role, mot_de_passe) VALUES
('Dupont', 'Alice', 'alice.dupont@rh.local', 3, 'password123'),
('Martin', 'Thomas', 'thomas.martin@rh.local', 2, 'password123'),
('Bernard', 'Sophie', 'sophie.bernard@rh.local', 4, 'password123'),
('Petit', 'Lucas', 'lucas.petit@rh.local', 1, 'password123'),
('Robert', 'Emma', 'emma.robert@rh.local', 1, 'password123'),
('Moreau', 'Julien', 'julien.moreau@rh.local', 5, 'password123'),
('Leroy', 'Karim', 'karim.leroy@rh.local', 2, 'password123');

INSERT INTO employee_fiche
    (id_emp, id_poste, id_dept, id_type_contrat, salaire, date_naissance)
VALUES
    (1, 1, 1, 1, 4200.00, '1988-04-12'),
    (2, 2, 2, 1, 3500.00, '1990-08-23'),
    (3, 5, 1, 2, 3000.00, '1992-01-17'),
    (4, 3, 3, 1, 3800.00, '1986-11-05'),
    (5, 4, 4, 3, 1800.00, '1998-06-30'),
    (6, 6, 2, 1, 3900.00, '1991-09-14'),
    (7, 2, 3, 1, 3600.00, '1989-03-21');

INSERT INTO demande_besoin
    (intitule_poste, id_dept, id_manager, effectif, motif, budget,
    date_souhaitee, id_etat, id_type, id_type_contrat)
VALUES
    ('Manager', 2, 2, 2,
     'Renforcement de equipe technique', 7000.00,
    '2026-01-15', 1, 1, 1),
    ('Assistant ressources humaines', 1, 2, 1,
     'Remplacement de employe absent', 3000.00,
    '2026-02-01', 3, 2, 2);
