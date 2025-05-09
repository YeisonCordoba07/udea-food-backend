package com.udeafood.controller.mongodb;

import com.udeafood.model.IngredienteProducto;
import com.udeafood.sevice.mongodb.IngredienteProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ingrediente")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IngredienteProductoController {
    private final IngredienteProductoService ingredienteProductoService;



    @PostMapping("/crear")
    public ResponseEntity<IngredienteProducto> guardar(@RequestBody IngredienteProducto ingredientes) {
        IngredienteProducto guardado = ingredienteProductoService.guardarIngredientes(ingredientes);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<IngredienteProducto> obtenerPorProducto(@PathVariable Integer productoId) {
        Optional<IngredienteProducto> resultado = ingredienteProductoService.obtenerIngredientesPorProductoId(productoId);
        return resultado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
