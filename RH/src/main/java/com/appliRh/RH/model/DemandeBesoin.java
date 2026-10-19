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
@Table(name = "demande_besoin")
public class DemandeBesoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_besoin")
    private Integer id;

    @Column(name = "intitule_poste", nullable = false, length = 150)
    private String intitulePoste;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_dept", nullable = false)
    private Departement departement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_manager", nullable = false)
    private Employee manager;

    @Column(nullable = false)
    private Integer effectif;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String motif;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal budget;

    @Column(name = "date_souhaitee", nullable = false)
    private LocalDate dateSouhaitee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etat", nullable = false)
    private EtatDemande etatDemande;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_type", nullable = false)
    private TypeDemande typeDemande;

    protected DemandeBesoin() {
    }

    public DemandeBesoin(String intitulePoste, Departement departement, Employee manager,
                         Integer effectif, String motif, BigDecimal budget,
                         LocalDate dateSouhaitee, EtatDemande etatDemande,
                         TypeDemande typeDemande) {
        this.intitulePoste = intitulePoste;
        this.departement = departement;
        this.manager = manager;
        this.effectif = effectif;
        this.motif = motif;
        this.budget = budget;
        this.dateSouhaitee = dateSouhaitee;
        this.etatDemande = etatDemande;
        this.typeDemande = typeDemande;
    }

    public Integer getId() {
        return id;
    }

    public String getIntitulePoste() {
        return intitulePoste;
    }

    public String getInitiales() {
        if (intitulePoste == null || intitulePoste.isBlank()) {
            return "";
        }

        StringBuilder initiales = new StringBuilder();
        for (String mot : intitulePoste.trim().split("\\s+")) {
            initiales.append(Character.toUpperCase(mot.charAt(0)));
        }
        return initiales.toString();
    }

    public void setIntitulePoste(String intitulePoste) {
        this.intitulePoste = intitulePoste;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Integer getEffectif() {
        return effectif;
    }

    public void setEffectif(Integer effectif) {
        this.effectif = effectif;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public LocalDate getDateSouhaitee() {
        return dateSouhaitee;
    }

    public void setDateSouhaitee(LocalDate dateSouhaitee) {
        this.dateSouhaitee = dateSouhaitee;
    }

    public EtatDemande getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(EtatDemande etatDemande) {
        this.etatDemande = etatDemande;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }
}
