package com.appliRh.RH.repository;

import com.appliRh.RH.model.DemandeBesoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface DemandeBesoinRepository extends JpaRepository<DemandeBesoin, Integer> {

	@Override
	@EntityGraph(attributePaths = {"departement", "manager", "etatDemande", "typeDemande"})
	java.util.Optional<DemandeBesoin> findById(Integer id);

	@Override
	@EntityGraph(attributePaths = {"departement", "manager", "etatDemande", "typeDemande"})
	List<DemandeBesoin> findAll();

	@EntityGraph(attributePaths = {"departement", "manager", "etatDemande", "typeDemande"})
	@Query("SELECT d FROM DemandeBesoin d WHERE d.departement.id = :idDept")
	List<DemandeBesoin> findDemandeDept(@Param("idDept") Integer idDept);
}
