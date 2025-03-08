package com.udeafood.controller;

import com.udeafood.model.ImagenProducto;
import com.udeafood.sevice.ImagenProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/imagenProducto")
@RequiredArgsConstructor
public class ImagenProductoController {

    private final ImagenProductoService imagenProductoService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ImagenProducto>> getAll(){
        return ResponseEntity.ok( imagenProductoService.getAll());
    }

}
