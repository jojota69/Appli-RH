package com.appliRh.RH.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity 
@Table(name = "offre")
public class Offre {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_offre")
    private Integer id;

    @Column(name = "id_besoin")
    private Integer idBesoin;

    @Column(name = "id_recruteur")
    private Integer idRecruteur;

    @Column(name = "titre")
    private String titre;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_publication")
    private LocalDate datePublication;

    @Column(name = "date_cloture")
    private LocalDate dateCloture;

    @Column(name = "statut")
    private String statut;

    public Offre() {
    }

    public Offre(Integer idBesoin, Integer idRecruteur, String titre, String description, LocalDate datePublication, LocalDate dateCloture, String statut) {
        this.idBesoin = idBesoin;
        this.idRecruteur = idRecruteur;
        this.titre = titre;
        this.description = description;
        this.datePublication = datePublication;
        this.dateCloture = dateCloture;
        this.statut = statut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdBesoin() {
        return idBesoin;
    }

    public void setIdBesoin(Integer idBesoin) {
        this.idBesoin = idBesoin;
    }

    public Integer getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(Integer idRecruteur) {
        this.idRecruteur = idRecruteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public LocalDate getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDate dateCloture) {
        this.dateCloture = dateCloture;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}