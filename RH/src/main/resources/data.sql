INSERT INTO role (libelle) VALUES
('Direction'),
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
('Ingenieur reseau'),
('Direction');

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
('En cours de traitement'),
('Valide RH');

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
    (4, 8, 3, 1, 3800.00, '1986-11-05'),
    (5, 8, 4, 3, 1800.00, '1998-06-30'),
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
    '2026-02-01', 3, 2, 2),
    ('Ingenieur reseau', 2, 2, 1,
     'Nouveau projet de securite informatique', 5000.00,
    '2026-03-10', 1, 1, 1);

INSERT INTO budget_alloue (valeur, mois, annee)
VALUES (100000.00, 1, 2026);

INSERT INTO etat_offre (libelle) VALUES
('Publiee'),
('Fermee'),
('Suspendue'),
('Termine');

INSERT INTO offre
    (id_besoin, id_recruteur, titre, description, date_publication, date_cloture, id_etat)
VALUES
    (1, 3, 'Manager technique',
     'Encadrer une equipe technique et accompagner les projets informatiques.',
     '2026-01-20', NULL, 1);

INSERT INTO candidat
    (nom, prenom, email, telephone, cv_url)
VALUES
    ('Diallo', 'Nina', 'nina.diallo@example.com', '0601020304', '/cv/nina-diallo.pdf'),
    ('Roux', 'Hugo', 'hugo.roux@example.com', '0605060708', '/cv/hugo-roux.pdf');

INSERT INTO etat_candidature (libelle) VALUES
('Recue'),
('Preselectionnee'),
('Acceptee'),
('Refusee');

INSERT INTO candidature
    (id_offre, id_candidat, date_candidature, id_etat)
VALUES
    (1, 1, '2026-01-23', 2),
    (1, 2, '2026-01-24', 1);

INSERT INTO entretien
    (id_candidature, date_entretien, type_entretien, compte_rendu)
VALUES
    (1, '2026-01-28 10:00:00', 'Visio', 'Bonne experience en management technique.');

INSERT INTO evaluation_candidat
    (id_entretien, id_evaluateur, note, commentaire, date_evaluation)
VALUES
    (1, 1, 16.50, 'Profil pertinent pour le poste.', '2026-01-28');
