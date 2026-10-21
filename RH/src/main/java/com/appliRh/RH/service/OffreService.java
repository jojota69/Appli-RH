package com.appliRh.RH.service;

import com.appliRh.RH.model.Offre;
import com.appliRh.RH.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreService {

    @Autowired
    private OffreRepository offreRepository;

    public List<Offre> findAll() {
        return offreRepository.findAll();
    }

    public Optional<Offre> findById(Integer id) {
        return offreRepository.findById(id);
    }

    public Offre save(Offre offre) {
        return offreRepository.save(offre);
    }

    public void deleteById(Integer id) {
        offreRepository.deleteById(id);
    }
}