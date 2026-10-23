package com.appliRh.RH.repository;

import com.appliRh.RH.model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@EntityGraph(attributePaths = {"role", "fiche", "fiche.employee", "fiche.poste", "fiche.departement", "fiche.contrat", "fiche.contrat.typeContrat", "fiche.contrat.etat"})
	@Override
	Optional<Employee> findById(Integer id);

	@Query("select e from Employee e join fetch e.role where e.email = :email")
	Optional<Employee> findByEmail(@Param("email") String email);

	@Query("""
		SELECT e FROM Employee e
		JOIN FETCH e.role
		JOIN FETCH e.fiche f
		JOIN FETCH f.poste
		JOIN FETCH f.departement
		JOIN FETCH f.contrat c
		JOIN FETCH c.typeContrat
		JOIN FETCH c.etat
		WHERE f.departement.id = :idDept
		""")
    List<Employee> findByDepartementId(@Param("idDept") Integer idDept);

	@Query("SELECT e FROM Employee e JOIN FETCH e.role WHERE e.role.id = :roleId")
	List<Employee> findByRoleId(@Param("roleId") Integer roleId);
}
