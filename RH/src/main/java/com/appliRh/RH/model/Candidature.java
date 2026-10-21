package com.appliRh.RH.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidature")
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
}
