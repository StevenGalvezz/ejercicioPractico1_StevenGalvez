/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejerpract1.controller;

import com.ejerpract1.domain.Categoria;
import com.ejerpract1.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Juan
 */

/**
 * CRUD básico de categorías (listado, crear, editar, eliminar)
 */
@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("categorias", service.listar());
        model.addAttribute("categoria", new Categoria());
        return "categoria/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Categoria categoria, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categorias", service.listar());
            return "categoria/listado";
        }
        service.guardar(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, Model model) {
        var opt = service.obtenerPorId(id);
        if (opt.isEmpty()) return "redirect:/categoria/listado";
        model.addAttribute("categoria", opt.get());
        return "categoria/modifica";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Long id) {
        service.eliminar(id);
        return "redirect:/categoria/listado";
    }
}
