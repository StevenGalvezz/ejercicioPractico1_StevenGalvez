/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejerpract1.service;

import com.ejerpract1.domain.Libro;
import com.ejerpract1.repository.LibroRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author Juan
 */

@Service
public class LibroService {

    private final LibroRepository repo;
    private final FileStorageService storage;

    public LibroService(LibroRepository repo, FileStorageService storage) {
        this.repo = repo;
        this.storage = storage;
    }

    @Transactional(readOnly = true)
    public List<Libro> listarTodos() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Libro> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public Libro guardar(Libro libro, MultipartFile imagen) throws IOException {
        Libro saved = repo.save(libro);
        if (imagen != null && !imagen.isEmpty()) {
            String ruta = storage.store(imagen);
            saved.setRutaImagen(ruta);
            saved = repo.save(saved);
        }
        return saved;
    }

    @Transactional
    public void eliminar(Long id) {
        Optional<Libro> opt = repo.findById(id);
        if (opt.isPresent()) {
            var libro = opt.get();
            storage.delete(libro.getRutaImagen());
            repo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Libro no encontrado");
        }
    }
}
