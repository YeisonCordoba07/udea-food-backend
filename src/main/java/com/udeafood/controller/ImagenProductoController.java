package com.udeafood.controller;

import com.udeafood.model.ImagenProducto;
import com.udeafood.sevice.interfaces.IImagenProductoService;
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

    private final IImagenProductoService iImagenProductoService;



    @GetMapping("/getAll")
    public ResponseEntity<List<ImagenProducto>> getAll(){
        return ResponseEntity.ok( iImagenProductoService.getAll());
    }

    @GetMapping("/buscarPorIdProducto")
    public ResponseEntity<List<ImagenProducto>> buscarPorIdProducto(Integer idProducto){
        return ResponseEntity.ok( iImagenProductoService.getAllByIdProducto(idProducto));
    }

}
