package com.appliRh.RH.repository;

import com.appliRh.RH.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
