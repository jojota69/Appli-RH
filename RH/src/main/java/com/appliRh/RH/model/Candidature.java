package com.appliRh.RH.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "candidature",
       uniqueConstraints = @UniqueConstraint(name = "uq_candidature_offre_candidat",
                                             columnNames = {"id_offre", "id_candidat"}))
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidature")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_offre", nullable = false)
    private Offre offre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_candidat", nullable = false)
    private Candidat candidat;

    @Column(name = "date_candidature", nullable = false)
    private LocalDate dateCandidature;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etat", nullable = false)
    private EtatCandidature etat;

    protected Candidature() {
    }

    public Candidature(Offre offre, Candidat candidat, LocalDate dateCandidature, EtatCandidature etat) {
        this.offre = offre;
        this.candidat = candidat;
        this.dateCandidature = dateCandidature;
        this.etat = etat;
    }

    public Integer getId() { return id; }
    public Offre getOffre() { return offre; }
    public void setOffre(Offre offre) { this.offre = offre; }
    public Candidat getCandidat() { return candidat; }
    public void setCandidat(Candidat candidat) { this.candidat = candidat; }
    public LocalDate getDateCandidature() { return dateCandidature; }
    public void setDateCandidature(LocalDate dateCandidature) { this.dateCandidature = dateCandidature; }
    public EtatCandidature getEtat() { return etat; }
    public void setEtat(EtatCandidature etat) { this.etat = etat; }
    public String getStatut() { return etat.getLibelle(); }
}
