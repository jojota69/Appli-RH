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

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contrat")
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrat")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_type", nullable = false)
    private TypeContrat typeContrat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etat", nullable = false)
    private EtatContrat etat;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salaire;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    protected Contrat() {
    }

    public Contrat(TypeContrat typeContrat, EtatContrat etat, BigDecimal salaire,
                   LocalDate dateDebut, LocalDate dateFin) {
        this.typeContrat = typeContrat;
        this.etat = etat;
        this.salaire = salaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Integer getId() {
        return id;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public EtatContrat getEtat() {
        return etat;
    }

    public void setEtat(EtatContrat etat) {
        this.etat = etat;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}
