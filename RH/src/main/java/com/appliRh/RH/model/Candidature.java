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

    @Column(nullable = false, length = 50)
    private String statut;

    protected Candidature() {
    }

    public Candidature(Offre offre, Candidat candidat, LocalDate dateCandidature, String statut) {
        this.offre = offre;
        this.candidat = candidat;
        this.dateCandidature = dateCandidature;
        this.statut = statut;
    }

    public Integer getId() { return id; }
    public Offre getOffre() { return offre; }
    public void setOffre(Offre offre) { this.offre = offre; }
    public Candidat getCandidat() { return candidat; }
    public void setCandidat(Candidat candidat) { this.candidat = candidat; }
    public LocalDate getDateCandidature() { return dateCandidature; }
    public void setDateCandidature(LocalDate dateCandidature) { this.dateCandidature = dateCandidature; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
