/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejerpract1.service;

import com.ejerpract1.domain.Queja;
import com.ejerpract1.repository.QuejaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan
 */

@Service
public class QuejaService {

    private final QuejaRepository repo;

    public QuejaService(QuejaRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Queja guardar(Queja q) {
        return repo.save(q);
    }

    @Transactional(readOnly = true)
    public List<Queja> listar() {
        return repo.findAll();
    }
}
