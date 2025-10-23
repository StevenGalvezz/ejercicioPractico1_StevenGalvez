/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejerpract1.service;

import com.ejerpract1.domain.Categoria;
import com.ejerpract1.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Juan
 */

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public Categoria guardar(Categoria categoria) {
        return repo.save(categoria);
    }

    @Transactional
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
