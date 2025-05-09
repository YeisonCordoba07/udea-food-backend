package com.udeafood.controller;

import com.udeafood.model.Categoria;
import com.udeafood.sevice.CategoriaService;
import com.udeafood.sevice.interfaces.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final ICategoriaService iCategoriaService;




    @GetMapping("/getAll")
    public ResponseEntity<List<Categoria>> getAll(){
        return ResponseEntity.ok(iCategoriaService.getAll());
    }
}
