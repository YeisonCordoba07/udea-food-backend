package com.udeafood.controller;

import com.udeafood.DTO.NuevaSeccionTiendaDTO;
import com.udeafood.model.SeccionTienda;
import com.udeafood.sevice.SeccionTiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/buscarPorIdTienda")
    public ResponseEntity<List<SeccionTienda>> getByTiendaId(@RequestParam Integer idTienda){
        return ResponseEntity.ok( seccionTiendaService.getByTiendaId(idTienda));
    }

    @PostMapping("crear")
    public ResponseEntity<?> create(@RequestBody NuevaSeccionTiendaDTO nuevaSeccionTiendaDTO){
       seccionTiendaService.create(nuevaSeccionTiendaDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body("Sección creada exitosamente");
    }
}
