package com.udeafood.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tienda")
public class TiendaController {


    @GetMapping
    public ResponseEntity<List<tienda>> getAllStores() {
        return ResponseEntity.ok(categoriaService.getAllCategories());
    }
}
