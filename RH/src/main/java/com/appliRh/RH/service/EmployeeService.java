package com.appliRh.RH.service;

import com.appliRh.RH.model.Employee;
import com.appliRh.RH.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employe introuvable : " + id));
    }

    @Transactional(readOnly = true)
    public Employee findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Employe introuvable avec l'email : " + email));
    }

    @Transactional
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Transactional
    public Employee update(Integer id, Employee employee) {
        Employee existing = findById(id);
        existing.setNom(employee.getNom());
        existing.setPrenom(employee.getPrenom());
        existing.setEmail(employee.getEmail());
        existing.setRole(employee.getRole());
        existing.setMotDePasse(employee.getMotDePasse());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }

    public List<Employee> getEmployeesByDepartement(Integer id) {
        return repository.findByDepartementId(id);
    }

    @Transactional(readOnly = true)
    public List<Employee> findByRole(Integer roleId) {
        return repository.findByRoleId(roleId);
    }
}
