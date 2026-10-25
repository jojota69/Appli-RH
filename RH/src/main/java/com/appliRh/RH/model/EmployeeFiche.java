package com.appliRh.RH.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "employee_fiche")
public class EmployeeFiche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fiche")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_emp", nullable = false, unique = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_poste", nullable = false)
    private Poste poste;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_dept", nullable = false)
    private Departement departement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_contrat", nullable = false)
    private Contrat contrat;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    protected EmployeeFiche() {
    }

    public EmployeeFiche(Employee employee, Poste poste, Departement departement,
                         Contrat contrat, LocalDate dateNaissance) {
        this.employee = employee;
        this.poste = poste;
        this.departement = departement;
        this.contrat = contrat;
        this.dateNaissance = dateNaissance;
    }

    public Integer getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public TypeContrat getTypeContrat() {
        return contrat == null ? null : contrat.getTypeContrat();
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        if (contrat != null) {
            contrat.setTypeContrat(typeContrat);
        }
    }

    public java.math.BigDecimal getSalaire() {
        return contrat == null ? null : contrat.getSalaire();
    }

    public void setSalaire(java.math.BigDecimal salaire) {
        if (contrat != null) {
            contrat.setSalaire(salaire);
        }
    }

    public LocalDate getDateDebut() {
        return contrat == null ? null : contrat.getDateDebut();
    }

    public void setDateDebut(LocalDate dateDebut) {
        if (contrat != null) {
            contrat.setDateDebut(dateDebut);
        }
    }

    public LocalDate getDateFin() {
        return contrat == null ? null : contrat.getDateFin();
    }

    public void setDateFin(LocalDate dateFin) {
        if (contrat != null) {
            contrat.setDateFin(dateFin);
        }
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
