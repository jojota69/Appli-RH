package com.appliRh.RH.repository;

import com.appliRh.RH.model.DemandeBesoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface DemandeBesoinRepository extends JpaRepository<DemandeBesoin, Integer> {

	@Override
	@EntityGraph(attributePaths = {"departement", "manager", "etatDemande", "typeDemande", "typeContrat"})
	java.util.Optional<DemandeBesoin> findById(Integer id);

	@Override
	@EntityGraph(attributePaths = {"departement", "manager", "etatDemande", "typeDemande", "typeContrat"})
	List<DemandeBesoin> findAll();

	@EntityGraph(attributePaths = {"departement", "manager", "etatDemande", "typeDemande"})
	@Query("SELECT d FROM DemandeBesoin d WHERE d.departement.id = :idDept")
	List<DemandeBesoin> findDemandeDept(@Param("idDept") Integer idDept);

	@EntityGraph(attributePaths = {"departement", "manager", "etatDemande", "typeDemande", "typeContrat"})
	@Query("SELECT d FROM DemandeBesoin d WHERE d.etatDemande.id = :idEtat")
	List<DemandeBesoin> findByEtat(@Param("idEtat") Integer idEtat);

	@EntityGraph(attributePaths = {"departement", "manager", "etatDemande", "typeDemande"})
	@Query("SELECT d FROM DemandeBesoin d WHERE d.departement.id = :idDepartement AND d.etatDemande.id = :idEtat")
	List<DemandeBesoin> findByDepartementAndEtat(@Param("idDepartement") Integer idDepartement,
			@Param("idEtat") Integer idEtat);
}
