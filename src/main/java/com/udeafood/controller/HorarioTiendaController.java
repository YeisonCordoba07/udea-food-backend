package com.udeafood.controller;

import com.udeafood.model.HorarioTienda;
import com.udeafood.sevice.interfaces.IHorarioTiendaService;
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

    private final IHorarioTiendaService iHorarioTiendaService;




    @GetMapping("/getAll")
    public ResponseEntity<List<HorarioTienda>> getAll(){
        return ResponseEntity.ok( iHorarioTiendaService.getAll());
    }

    @GetMapping("/buscarPorIdTienda")
    public ResponseEntity<List<HorarioTienda>> getByIdTienda(Integer idTienda){
        return ResponseEntity.ok( iHorarioTiendaService.getByIdTienda(idTienda));
    }
}
