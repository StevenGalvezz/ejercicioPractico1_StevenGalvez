/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejerpract1.controller;

import com.ejerpract1.domain.Libro;
import com.ejerpract1.service.CategoriaService;
import com.ejerpract1.service.LibroService;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Juan
 */

@Controller
@RequestMapping("/libro")
public class LibroController {

    private final LibroService service;
    private final CategoriaService categoriaService;

    public LibroController(LibroService service, CategoriaService categoriaService) {
        this.service = service;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("libros", service.listarTodos());
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("libro", new Libro());
        return "libro/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        var opt = service.obtenerPorId(id);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("error", "Libro no encontrado");
            return "redirect:/libro/listado";
        }
        model.addAttribute("libro", opt.get());
        model.addAttribute("categorias", categoriaService.listar());
        return "libro/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Libro libro, BindingResult br,
                          @RequestParam(name = "imagenFile", required = false) MultipartFile imagen,
                          RedirectAttributes redirectAttributes, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            return libro.getId() == null ? "libro/listado" : "libro/modifica";
        }
        try {
            service.guardar(libro, imagen);
            redirectAttributes.addFlashAttribute("todoOk", "Guardado correctamente");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar imagen");
        }
        return "redirect:/libro/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Long id, RedirectAttributes ra) {
        try {
            service.eliminar(id);
            ra.addFlashAttribute("todoOk", "Eliminado");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "No se pudo eliminar");
        }
        return "redirect:/libro/listado";
    }
}
