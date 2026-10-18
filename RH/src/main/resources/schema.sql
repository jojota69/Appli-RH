DROP TABLE IF EXISTS demande_besoin CASCADE;
DROP TABLE IF EXISTS type_demande CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS etat_demande CASCADE;
DROP TABLE IF EXISTS poste CASCADE;
DROP TABLE IF EXISTS departement CASCADE;
DROP TABLE IF EXISTS role CASCADE;

CREATE TABLE role (
    id_role SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE departement (
    id_dept SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE poste (
    id_poste SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE etat_demande (
    id_etat SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE employee (
    id_emp SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    id_role INT NOT NULL,
    id_dept INT NOT NULL,
    CONSTRAINT fk_employee_role FOREIGN KEY (id_role) REFERENCES role(id_role),
    CONSTRAINT fk_employee_dept FOREIGN KEY (id_dept) REFERENCES departement(id_dept)
);

CREATE TABLE type_demande (
    id_type SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE demande_besoin (
    id_besoin SERIAL PRIMARY KEY,
    id_poste INT NOT NULL,
    id_dept INT NOT NULL,
    id_manager INT NOT NULL,
    effectif INT NOT NULL,
    motif TEXT NOT NULL,
    budget NUMERIC(12, 2) NOT NULL,
    date_souhaitee DATE NOT NULL,
    id_etat INT NOT NULL,
    id_typedemande INT NOT NULL,
    CONSTRAINT fk_demande_poste FOREIGN KEY (id_poste) REFERENCES poste(id_poste),
    CONSTRAINT fk_demande_dept FOREIGN KEY (id_dept) REFERENCES departement(id_dept),
    CONSTRAINT fk_demande_manager FOREIGN KEY (id_manager) REFERENCES employee(id_emp),
    CONSTRAINT fk_demande_etat FOREIGN KEY (id_etat) REFERENCES etat_demande(id_etat),
    CONSTRAINT fk_demande_typedemande FOREIGN KEY (id_typedemande) REFERENCES type_demande(id_type)
);

