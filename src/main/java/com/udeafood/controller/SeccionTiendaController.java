package com.udeafood.controller;

import com.udeafood.model.SeccionTienda;
import com.udeafood.sevice.SeccionTiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/seccionTienda")
@RequiredArgsConstructor
public class SeccionTiendaController {

    private final SeccionTiendaService seccionTiendaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<SeccionTienda>> getAll(){
        return ResponseEntity.ok( seccionTiendaService.getAll());
    }
}
