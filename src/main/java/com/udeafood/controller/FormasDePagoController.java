package com.udeafood.controller;

import com.udeafood.model.FormasDePago;
import com.udeafood.sevice.FormasDePagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/formasDePago")
@RequiredArgsConstructor
public class FormasDePagoController {
    private final FormasDePagoService formasDePagoService;

    @GetMapping("/getAll")
    public ResponseEntity<List<FormasDePago>> getAll(){
        return ResponseEntity.ok( formasDePagoService.getAll());
    }
}
