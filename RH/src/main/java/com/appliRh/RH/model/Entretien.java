package com.appliRh.RH.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entretien")
public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entretien")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_candidature", nullable = false)
    private Candidature candidature;

    @Column(name = "date_entretien", nullable = false)
    private LocalDateTime dateEntretien;

    @Column(name = "type_entretien", nullable = false, length = 50)
    private String typeEntretien;

    @Column(name = "compte_rendu", columnDefinition = "TEXT")
    private String compteRendu;

    protected Entretien() {
    }

    public Entretien(Candidature candidature, LocalDateTime dateEntretien,
                     String typeEntretien, String compteRendu) {
        this.candidature = candidature;
        this.dateEntretien = dateEntretien;
        this.typeEntretien = typeEntretien;
        this.compteRendu = compteRendu;
    }

    public Integer getId() { return id; }
    public Candidature getCandidature() { return candidature; }
    public void setCandidature(Candidature candidature) { this.candidature = candidature; }
    public LocalDateTime getDateEntretien() { return dateEntretien; }
    public void setDateEntretien(LocalDateTime dateEntretien) { this.dateEntretien = dateEntretien; }
    public String getTypeEntretien() { return typeEntretien; }
    public void setTypeEntretien(String typeEntretien) { this.typeEntretien = typeEntretien; }
    public String getCompteRendu() { return compteRendu; }
    public void setCompteRendu(String compteRendu) { this.compteRendu = compteRendu; }
}
