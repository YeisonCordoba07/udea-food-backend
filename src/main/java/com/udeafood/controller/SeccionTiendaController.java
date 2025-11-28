package com.udeafood.controller;

import com.udeafood.DTO.NuevaSeccionTiendaDTO;
import com.udeafood.model.SeccionTienda;
import com.udeafood.sevice.interfaces.ISeccionTiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/seccionTienda")
@RequiredArgsConstructor
public class SeccionTiendaController {

    private final ISeccionTiendaService iSeccionTiendaService;




    @GetMapping("/getAll")
    public ResponseEntity<List<SeccionTienda>> getAll(){
        return ResponseEntity.ok( iSeccionTiendaService.getAll());
    }


    @GetMapping("/buscarPorIdTienda")
    public ResponseEntity<List<SeccionTienda>> getByTiendaId(@RequestParam Integer idTienda){
        return ResponseEntity.ok( iSeccionTiendaService.getByTiendaId(idTienda));
    }

    @PostMapping("crear")
    public ResponseEntity<?> create(@RequestBody NuevaSeccionTiendaDTO nuevaSeccionTiendaDTO){
       iSeccionTiendaService.create(nuevaSeccionTiendaDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "ección creada exitosamente");
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
