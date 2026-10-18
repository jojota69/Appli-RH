package com.appliRh.RH.repository;

import com.appliRh.RH.model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@EntityGraph(attributePaths = {"role", "departement"})
	@Override
	Optional<Employee> findById(Integer id);

	@Query("select e from Employee e join fetch e.role where e.email = :email")
	Optional<Employee> findByEmail(@Param("email") String email);

	@Query("SELECT e FROM Employee e JOIN FETCH e.role LEFT JOIN FETCH e.departement WHERE e.departement.id = :idDept")
    List<Employee> findByDepartementId(@Param("idDept") Integer idDept);
}
