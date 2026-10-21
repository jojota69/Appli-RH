package com.appliRh.RH.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "offre")
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_offre")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_besoin", nullable = false, unique = true)
    private DemandeBesoin besoin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_recruteur", nullable = false)
    private Employee recruteur;

    @Column(nullable = false, length = 150)
    private String titre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_publication", nullable = false)
    private LocalDate datePublication;

    @Column(name = "date_cloture")
    private LocalDate dateCloture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etat", nullable = false)
    private EtatOffre etat;

    protected Offre() {
    }

    public Offre(DemandeBesoin besoin, Employee recruteur, String titre, String description,
                 LocalDate datePublication, LocalDate dateCloture, EtatOffre etat) {
        this.besoin = besoin;
        this.recruteur = recruteur;
        this.titre = titre;
        this.description = description;
        this.datePublication = datePublication;
        this.dateCloture = dateCloture;
        this.etat = etat;
    }

    public Integer getId() {
        return id;
    }

    public DemandeBesoin getBesoin() {
        return besoin;
    }

    public void setBesoin(DemandeBesoin besoin) {
        this.besoin = besoin;
    }

    public Employee getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(Employee recruteur) {
        this.recruteur = recruteur;
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

    public EtatOffre getEtat() {
        return etat;
    }

    public void setEtat(EtatOffre etat) {
        this.etat = etat;
    }

    public Integer getIdBesoin() {
        return besoin == null ? null : besoin.getId();
    }

    public Integer getIdRecruteur() {
        return recruteur == null ? null : recruteur.getId();
    }

    public String getStatut() {
        return etat == null ? null : etat.getLibelle();
    }
}
