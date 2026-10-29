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

INSERT INTO etat_contrat (libelle) VALUES
('Actif'),
('Termine');

INSERT INTO contrat
(id_type, id_etat, salaire, date_debut, date_fin)
VALUES
(1, 1, 4200.00, '2020-01-15', NULL),
(1, 1, 3500.00, '2021-03-01', NULL),
(1, 1, 3200.00, '2022-06-01', NULL),
(1, 1, 5000.00, '2019-09-01', NULL),
(1, 1, 4800.00, '2020-02-01', NULL),
(1, 1, 3000.00, '2023-01-10', NULL),
(1, 1, 3400.00, '2022-04-12', NULL);

INSERT INTO type_demande (libelle) VALUES
('Creation de poste'),
('Remplacement'),
('Suppression de poste');

INSERT INTO etat_demande (libelle) VALUES
('Valide'),
('Rejete'),
('En cours de traitement'),
('Valide RH');

INSERT INTO employee
(nom, prenom, email, id_role, mot_de_passe)
VALUES
('Dupont', 'Alice', 'alice.dupont@rh.local', 3, 'password123'),
('Martin', 'Thomas', 'thomas.martin@rh.local', 2, 'password123'),
('Bernard', 'Sophie', 'sophie.bernard@rh.local', 4, 'password123'),
('Petit', 'Lucas', 'lucas.petit@rh.local', 1, 'password123'),
('Robert', 'Emma', 'emma.robert@rh.local', 1, 'password123'),
('Moreau', 'Julien', 'julien.moreau@rh.local', 5, 'password123'),
('Leroy', 'Karim', 'karim.leroy@rh.local', 2, 'password123');

INSERT INTO employee_fiche
(id_emp, id_poste, id_dept, id_contrat, date_naissance)
VALUES
(1, 1, 1, 1, '1988-04-12'),
(2, 2, 2, 2, '1990-08-23'),
(3, 6, 1, 3, '1992-01-17'),
(4, 8, 1, 4, '1986-11-05'),
(5, 8, 4, 5, '1988-06-30'),
(6, 3, 2, 6, '1991-09-14'),
(7, 2, 3, 7, '1989-03-21');

INSERT INTO budget_alloue
(valeur, mois, annee)
VALUES
(100000.00, 1, 2026),
(120000.00, 2, 2026),
(150000.00, 3, 2026),
(130000.00, 4, 2026),
(160000.00, 5, 2026),
(140000.00, 6, 2026);

INSERT INTO demande_besoin
(
    intitule_poste,
    id_dept,
    id_manager,
    effectif,
    motif,
    budget,
    date_souhaitee,
    id_etat,
    id_type,
    id_type_contrat
)
VALUES
(
    'Developpeur logiciel',
    2,
    2,
    2,
    'Renforcement de equipe de developpement pour plusieurs nouveaux projets.',
    7000.00,
    '2026-04-01',
    1,
    1,
    1
),
(
    'Assistant ressources humaines',
    1,
    1,
    1,
    'Augmentation du volume de travail du service ressources humaines.',
    2800.00,
    '2026-05-01',
    4,
    1,
    1
),
(
    'Ingenieur reseau',
    2,
    2,
    1,
    'Mise en place et securisation de la nouvelle infrastructure reseau.',
    4000.00,
    '2026-06-01',
    1,
    1,
    1
),
(
    'Comptable',
    3,
    7,
    1,
    'Augmentation du volume des operations comptables.',
    3200.00,
    '2026-07-01',
    3,
    1,
    1
);

INSERT INTO etat_offre (libelle) VALUES
('Publiee'),
('Fermee'),
('Suspendue'),
('Termine');

INSERT INTO offre
(
    id_besoin,
    id_recruteur,
    titre,
    description,
    date_publication,
    date_cloture,
    id_etat
)
VALUES
(
    1,
    3,
    'Developpeur logiciel',
    'Recherche de developpeurs logiciels pour renforcer le service informatique et participer aux nouveaux projets.',
    '2026-03-01',
    '2026-03-31',
    1
),
(
    2,
    3,
    'Assistant ressources humaines',
    'Recherche dun assistant ressources humaines pour renforcer le service RH.',
    '2026-03-05',
    '2026-04-05',
    1
),
(
    3,
    3,
    'Ingenieur reseau',
    'Recherche dun ingenieur reseau pour administrer et securiser linfrastructure informatique.',
    '2026-03-10',
    NULL,
    1
);

INSERT INTO candidat
(
    nom,
    prenom,
    email,
    telephone,
    cv_url
)
VALUES
('Diallo', 'Nina', 'nina.diallo@example.com', '0601020304', '/cv/nina-diallo.pdf'),
('Roux', 'Hugo', 'hugo.roux@example.com', '0605060708', '/cv/hugo-roux.pdf'),
('Morel', 'Clara', 'clara.morel@example.com', '0608091011', '/cv/clara-morel.pdf'),
('Garcia', 'Louis', 'louis.garcia@example.com', '0612131415', '/cv/louis-garcia.pdf'),
('Fofana', 'Sara', 'sara.fofana@example.com', '0616171819', '/cv/sara-fofana.pdf'),
('Rakoto', 'Andry', 'andry.rakoto@example.com', '0620212223', '/cv/andry-rakoto.pdf'),
('Rasoanaivo', 'Miora', 'miora.rasoanaivo@example.com', '0624252627', '/cv/miora-rasoanaivo.pdf');

INSERT INTO etat_candidature (libelle) VALUES
('Recue'),
('Preselectionnee'),
('Acceptee'),
('Refusee'),
('Proposee');

INSERT INTO candidature
(
    id_offre,
    id_candidat,
    date_candidature,
    id_etat
)
VALUES
(1, 1, '2026-03-03', 2),
(1, 2, '2026-03-04', 2),
(1, 3, '2026-03-05', 1),
(2, 4, '2026-03-08', 2),
(2, 5, '2026-03-09', 1),
(3, 6, '2026-03-12', 2),
(3, 7, '2026-03-13', 1);

INSERT INTO entretien
(
    id_candidature,
    date_entretien,
    type_entretien,
    compte_rendu
)
VALUES
(1, '2026-03-15 10:00:00', 'Technique', 'Bonne maitrise de Java et bonne experience avec Spring Boot.'),
(2, '2026-03-16 09:30:00', 'Technique', 'Bonne connaissance du developpement web et des bases de donnees.'),
(4, '2026-03-18 14:00:00', 'RH', 'Bonne communication et bonne connaissance des procedures administratives.'),
(6, '2026-03-20 10:00:00', 'Technique', 'Bonne connaissance des reseaux, Linux et de la securite informatique.');

INSERT INTO evaluation_candidat
(
    id_entretien,
    id_evaluateur,
    note,
    commentaire,
    date_evaluation
)
VALUES
(1, 2, 17.50, 'Tres bon niveau technique et bonne maitrise de Java.', '2026-03-15'),
(2, 2, 15.50, 'Bon profil mais moins dexperience sur Spring Boot.', '2026-03-16'),
(3, 1, 16.00, 'Profil serieux, organise et bonne communication.', '2026-03-18'),
(4, 2, 18.00, 'Excellent niveau technique et bonne connaissance des reseaux.', '2026-03-20');

INSERT INTO candidature_contrat
(
    id_candidature,
    id_type_contrat,
    date_debut,
    date_fin,
    salaire
)
VALUES
(1, 1, '2026-04-01', NULL, 3200.00),
(6, 1, '2026-04-15', NULL, 4000.00);