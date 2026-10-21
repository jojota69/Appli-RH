package com.appliRh.RH.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "evaluation_candidat")
public class EvaluationCandidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluation")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_entretien", nullable = false)
    private Entretien entretien;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_evaluateur", nullable = false)
    private Employee evaluateur;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal note;

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    @Column(name = "date_evaluation", nullable = false)
    private LocalDate dateEvaluation;

    protected EvaluationCandidat() {
    }

    public EvaluationCandidat(Entretien entretien, Employee evaluateur, BigDecimal note,
                              String commentaire, LocalDate dateEvaluation) {
        this.entretien = entretien;
        this.evaluateur = evaluateur;
        this.note = note;
        this.commentaire = commentaire;
        this.dateEvaluation = dateEvaluation;
    }

    public Integer getId() { return id; }
    public Entretien getEntretien() { return entretien; }
    public void setEntretien(Entretien entretien) { this.entretien = entretien; }
    public Employee getEvaluateur() { return evaluateur; }
    public void setEvaluateur(Employee evaluateur) { this.evaluateur = evaluateur; }
    public BigDecimal getNote() { return note; }
    public void setNote(BigDecimal note) { this.note = note; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    public LocalDate getDateEvaluation() { return dateEvaluation; }
    public void setDateEvaluation(LocalDate dateEvaluation) { this.dateEvaluation = dateEvaluation; }
}
