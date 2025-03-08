package com.udeafood.controller;


import com.udeafood.model.Tienda;
import com.udeafood.sevice.TiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tienda")
@RequiredArgsConstructor
public class TiendaController {

    private final TiendaService tiendaService;


    @GetMapping("/getAll")
    public ResponseEntity<List<Tienda>> getAll(){

        return ResponseEntity.ok( tiendaService.getAll());
    }

    @GetMapping("/buscarPorId")
    public ResponseEntity<Tienda> getTiendaById(@RequestParam Integer id){
        return ResponseEntity.ok(tiendaService.getTiendaById(id));
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<List<Tienda>> getTiendaByNombre(@RequestParam String nombre){
        return ResponseEntity.ok(tiendaService.getTiendaByNombre(nombre));
    }


}
