package com.appliRh.RH.service;

import com.appliRh.RH.model.Role;
import com.appliRh.RH.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Role findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role introuvable : " + id));
    }

    @Transactional
    public Role create(Role role) {
        return repository.save(role);
    }

    @Transactional
    public Role update(Integer id, Role role) {
        Role existing = findById(id);
        existing.setLibelle(role.getLibelle());
        return repository.save(existing);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}
