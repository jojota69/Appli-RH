DROP TABLE IF EXISTS demande_besoin CASCADE;
DROP TABLE IF EXISTS employee_fiche CASCADE;
DROP TABLE IF EXISTS type_demande CASCADE;
DROP TABLE IF EXISTS type_contrat CASCADE;
DROP TABLE IF EXISTS etat_demande CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS poste CASCADE;
DROP TABLE IF EXISTS departement CASCADE;
DROP TABLE IF EXISTS role CASCADE;
DROP TABLE IF EXISTS budget_alloue CASCADE;

CREATE TABLE role (
    id_role INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE departement (
    id_dept INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE poste (
    id_poste INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE type_contrat (
    id_type INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE type_demande (
    id_type INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE etat_demande (
    id_etat INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE employee (
    id_emp INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    id_role INT NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    CONSTRAINT fk_employee_role
        FOREIGN KEY (id_role) REFERENCES role(id_role)
);

CREATE TABLE employee_fiche (
    id_fiche INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_emp INT NOT NULL,
    id_poste INT NOT NULL,
    id_dept INT NOT NULL,
    id_type_contrat INT NOT NULL,
    salaire NUMERIC(12, 2) NOT NULL,
    date_naissance DATE NOT NULL,
    CONSTRAINT fk_employee_fiche_employee
        FOREIGN KEY (id_emp) REFERENCES employee(id_emp),
    CONSTRAINT fk_employee_fiche_poste
        FOREIGN KEY (id_poste) REFERENCES poste(id_poste),
    CONSTRAINT fk_employee_fiche_departement
        FOREIGN KEY (id_dept) REFERENCES departement(id_dept),
    CONSTRAINT fk_employee_fiche_type_contrat
        FOREIGN KEY (id_type_contrat) REFERENCES type_contrat(id_type),
    CONSTRAINT uq_employee_fiche_employee UNIQUE (id_emp)
);

CREATE TABLE demande_besoin (
    id_besoin INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    intitule_poste VARCHAR(150) NOT NULL,
    id_dept INT NOT NULL,
    id_manager INT NOT NULL,
    effectif INT NOT NULL,
    motif TEXT NOT NULL,
    budget NUMERIC(12, 2) NOT NULL,
    date_souhaitee DATE NOT NULL,
    id_etat INT NOT NULL,
    id_type INT NOT NULL,
    id_type_contrat INT NOT NULL,
    CONSTRAINT fk_demande_besoin_type_contrat
        FOREIGN KEY (id_type_contrat) REFERENCES type_contrat(id_type),
    CONSTRAINT fk_demande_besoin_departement
        FOREIGN KEY (id_dept) REFERENCES departement(id_dept),
    CONSTRAINT fk_demande_besoin_manager
        FOREIGN KEY (id_manager) REFERENCES employee(id_emp),
    CONSTRAINT fk_demande_besoin_etat
        FOREIGN KEY (id_etat) REFERENCES etat_demande(id_etat),
    CONSTRAINT fk_demande_besoin_type
        FOREIGN KEY (id_type) REFERENCES type_demande(id_type)
);

CREATE TABLE budget_alloue (
    id_budget SERIAL PRIMARY KEY,
    valeur NUMERIC(15,2) NOT NULL,
    mois INTEGER NOT NULL,
    annee INTEGER NOT NULL,
    UNIQUE (mois, annee)
);
