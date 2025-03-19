package com.udeafood.controller;

import com.udeafood.DTO.ProductoConImagenDTO;
import com.udeafood.DTO.ProductoDTO;
import com.udeafood.model.Producto;
import com.udeafood.sevice.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;



    // GET -------------------------------------------------------------------------------
    @GetMapping("/getAll")
    public ResponseEntity<List<Producto>> getAll(){
        return ResponseEntity.ok( productoService.getAll());
    }


    @GetMapping("/buscarPorIdSeccion")
    public ResponseEntity<List<Producto>> getByIdSeccion(@RequestParam Integer idSeccion){
        return ResponseEntity.ok(productoService.getByIdSeccionTienda(idSeccion));
    }


    @GetMapping("/buscarPorIdTienda")
    public ResponseEntity<List<Producto>> getByIdTienda(@RequestParam Integer idTienda){
        return ResponseEntity.ok(productoService.getByIdTienda(idTienda));
    }


    @GetMapping("/buscarPorIdProducto")
    public ResponseEntity<ProductoConImagenDTO> getByIdProducto(@RequestParam Integer idProducto){
        return ResponseEntity.ok(productoService.getByIdProducto(idProducto));
    }


    @GetMapping("/buscarPorNombreCategoria")
    public ResponseEntity<List<Producto>> getByNombreCategoria(@RequestParam String categoria){
        return ResponseEntity.ok(productoService.getByNombreCategoria(categoria));
    }

    @GetMapping("/buscarPorNombreProducto")
    public ResponseEntity<List<Producto>> getByNombreProducto(@RequestParam String nombre){
        return ResponseEntity.ok(productoService.getByNombreProducto(nombre));
    }




    // POST ------------------------------------------------------------------------------
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductoDTO productoDTO) {
        try {
            productoService.save(productoDTO);
            return ResponseEntity.status(201).body("Guardado con exito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }


}
