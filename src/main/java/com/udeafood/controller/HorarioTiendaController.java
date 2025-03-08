package com.udeafood.controller;

import com.udeafood.model.HorarioTienda;
import com.udeafood.model.Tienda;
import com.udeafood.sevice.HorarioTiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/horarioTienda")
@RequiredArgsConstructor
public class HorarioTiendaController {

    private final HorarioTiendaService horarioTiendaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<HorarioTienda>> getAll(){
        return ResponseEntity.ok( horarioTiendaService.getAll());
    }
}
