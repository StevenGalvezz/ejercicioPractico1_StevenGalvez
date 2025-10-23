/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejerpract1.controller;

import com.ejerpract1.domain.Queja;
import com.ejerpract1.service.QuejaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author Juan
 */
@Controller
@RequestMapping("/queja")
public class QuejaController {

    private final QuejaService service;

    public QuejaController(QuejaService service) {
        this.service = service;
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("queja", new Queja());
        return "queja/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Queja queja, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "queja/form";
        }
        service.guardar(queja);
        model.addAttribute("todoOk", "Gracias por su mensaje");
        return "queja/form";
    }
}
