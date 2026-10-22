DROP TABLE IF EXISTS demande_besoin CASCADE;
DROP TABLE IF EXISTS evaluation_candidat CASCADE;
DROP TABLE IF EXISTS entretien CASCADE;
DROP TABLE IF EXISTS candidature CASCADE;
DROP TABLE IF EXISTS etat_candidature CASCADE;
DROP TABLE IF EXISTS candidat CASCADE;
DROP TABLE IF EXISTS offre CASCADE;
DROP TABLE IF EXISTS etat_offre CASCADE;
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

CREATE TABLE etat_offre (
    id_etat INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE offre (
    id_offre INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_besoin INT NOT NULL,
    id_recruteur INT NOT NULL,
    titre VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    date_publication DATE NOT NULL,
    date_cloture DATE,
    id_etat INT NOT NULL,
    CONSTRAINT uq_offre_demande UNIQUE (id_besoin),
    CONSTRAINT fk_offre_demande
        FOREIGN KEY (id_besoin) REFERENCES demande_besoin(id_besoin),
    CONSTRAINT fk_offre_recruteur
        FOREIGN KEY (id_recruteur) REFERENCES employee(id_emp),
    CONSTRAINT ck_offre_dates
        CHECK (date_cloture IS NULL OR date_cloture >= date_publication),
    CONSTRAINT fk_offre_etat
        FOREIGN KEY (id_etat) REFERENCES etat_offre(id_etat)
);

CREATE TABLE candidat (
    id_candidat INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    telephone VARCHAR(30),
    cv_url VARCHAR(255)
);

CREATE TABLE etat_candidature (
    id_etat INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE candidature (
    id_candidature INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_offre INT NOT NULL,
    id_candidat INT NOT NULL,
    date_candidature DATE NOT NULL,
    id_etat INT NOT NULL,
    CONSTRAINT uq_candidature_offre_candidat UNIQUE (id_offre, id_candidat),
    CONSTRAINT fk_candidature_offre
        FOREIGN KEY (id_offre) REFERENCES offre(id_offre),
    CONSTRAINT fk_candidature_candidat
        FOREIGN KEY (id_candidat) REFERENCES candidat(id_candidat),
    CONSTRAINT fk_candidature_etat
        FOREIGN KEY (id_etat) REFERENCES etat_candidature(id_etat)
);

CREATE TABLE entretien (
    id_entretien INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_candidature INT NOT NULL,
    date_entretien TIMESTAMP NOT NULL,
    type_entretien VARCHAR(50) NOT NULL,
    compte_rendu TEXT,
    CONSTRAINT fk_entretien_candidature
        FOREIGN KEY (id_candidature) REFERENCES candidature(id_candidature),
    CONSTRAINT uq_entretien_candidature UNIQUE (id_candidature)
);

CREATE TABLE evaluation_candidat (
    id_evaluation INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_entretien INT NOT NULL,
    id_evaluateur INT NOT NULL,
    note NUMERIC(5, 2) NOT NULL,
    commentaire TEXT,
    date_evaluation DATE NOT NULL,
    CONSTRAINT fk_evaluation_entretien
        FOREIGN KEY (id_entretien) REFERENCES entretien(id_entretien),
    CONSTRAINT fk_evaluation_evaluateur
        FOREIGN KEY (id_evaluateur) REFERENCES employee(id_emp),
    CONSTRAINT ck_evaluation_note
        CHECK (note >= 0 AND note <= 20)
);
