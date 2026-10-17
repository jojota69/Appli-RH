INSERT INTO role (id_role, libelle) VALUES
(1, 'Administrateur'),
(2, 'Manager'),
(3, 'Responsable RH'),
(4, 'Recruteur'),
(5,'employee');

INSERT INTO departement (id_dept, libelle) VALUES
(1, 'Ressources humaines'),
(2, 'Informatique'),
(3, 'Finance'),
(4, 'Marketing');

INSERT INTO poste (id_poste, libelle) VALUES
(1, 'Developpeur logiciel'),
(2, 'Analyste RH'),
(3, 'Comptable'),
(4, 'Charge de recrutement'),
(5, 'Chef de projet');

INSERT INTO employee (id_emp, nom, prenom, email,mot_de_passe, id_role, id_dept) VALUES
(1, 'Dupont', 'Alice', 'alice.dupont@rh.local', 'password123', 3, 1),
(2, 'Martin', 'Thomas', 'thomas.martin@rh.local', 'password123', 2, 2),
(3, 'Bernard', 'Sophie', 'sophie.bernard@rh.local', 'password123', 4, 1),
(4, 'Petit', 'Lucas', 'lucas.petit@rh.local', 'password123', 1, 2),
(5, 'Robert', 'Emma', 'emma.robert@rh.local', 'password123', 1, 3);
