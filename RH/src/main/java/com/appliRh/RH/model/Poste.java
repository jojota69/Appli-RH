package com.appliRh.RH.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "poste")
public class Poste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poste")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String libelle;

    protected Poste() {
    }

    public Poste(String libelle) {
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getInitiales() {
        if (libelle == null || libelle.isBlank()) {
            return "";
        }

        StringBuilder initiales = new StringBuilder();
        for (String mot : libelle.trim().split("\\s+")) {
            initiales.append(Character.toUpperCase(mot.charAt(0)));
        }
        return initiales.toString();
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
