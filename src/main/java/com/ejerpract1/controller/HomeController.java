/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejerpract1.controller;

import com.ejerpract1.domain.Libro;
import com.ejerpract1.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author Juan
 */

@Controller
public class HomeController {

    private final LibroService libroService;

    public HomeController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        // Mostrar un libro nuevo destacado (tomamos el primero disponible para ejemplo)
        Libro destacado = libroService.listarTodos().stream().findFirst().orElse(null);
        model.addAttribute("destacado", destacado);
        return "home";
    }
}
