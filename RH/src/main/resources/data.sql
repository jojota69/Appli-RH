INSERT INTO role (libelle) VALUES
('Administrateur'),
('Manager'),
('Responsable RH'),
('Recruteur'),
('employee');

INSERT INTO departement (libelle) VALUES
('Ressources humaines'),
('Informatique'),
('Finance'),
('Marketing');

INSERT INTO employee (nom, prenom, email,mot_de_passe, id_role, id_dept) VALUES
('Dupont', 'Alice', 'alice.dupont@rh.local', 'password123', 3, 1),
('Martin', 'Thomas', 'thomas.martin@rh.local', 'password123', 2, 2),
('Bernard', 'Sophie', 'sophie.bernard@rh.local', 'password123', 4, 1),
('Petit', 'Lucas', 'lucas.petit@rh.local', 'password123', 1, 2),
('Robert', 'Emma', 'emma.robert@rh.local', 'password123', 1, 3);

INSERT INTO etat_demande (libelle) VALUES
('En attente'),
('Approuvee'),
('Rejetee'),
('Approuvee RH');

INSERT INTO type_demande (libelle) VALUES
('Nouveau poste'),
('Remplacement');
