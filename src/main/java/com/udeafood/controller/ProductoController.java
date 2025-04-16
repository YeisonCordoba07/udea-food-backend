package com.udeafood.controller;

import com.udeafood.DTO.ProductoDTO;
import com.udeafood.DTO.ProductoRequestDTO;
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
    public ResponseEntity<List<ProductoDTO>> getAll(){
        return ResponseEntity.ok( productoService.getAll());
    }


    @GetMapping("/buscarPorIdSeccion")
    public ResponseEntity<List<ProductoDTO>> getByIdSeccion(@RequestParam Integer idSeccion){
        return ResponseEntity.ok(productoService.getAllByIdSeccionTienda(idSeccion));
    }


    @GetMapping("/buscarPorIdTienda")
    public ResponseEntity<List<ProductoDTO>> getByIdTienda(@RequestParam Integer idTienda){
        return ResponseEntity.ok(productoService.getAllByIdTienda(idTienda));
    }


    @GetMapping("/buscarPorIdProducto")
    public ResponseEntity<ProductoDTO> getByIdProducto(@RequestParam Integer idProducto){
        return ResponseEntity.ok(productoService.getByIdProducto(idProducto));
    }


    @GetMapping("/buscarPorNombreCategoria")
    public ResponseEntity<List<ProductoDTO>> getByNombreCategoria(@RequestParam String categoria){
        return ResponseEntity.ok(productoService.getByNombreCategoria(categoria));
    }

    @GetMapping("/buscarPorNombreProducto")
    public ResponseEntity<List<ProductoDTO>> getByNombreProducto(@RequestParam String nombre){
        return ResponseEntity.ok(productoService.getByNombreProducto(nombre));
    }




    // POST ------------------------------------------------------------------------------
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductoRequestDTO productoRequestDTO) {
        try {
            productoService.save(productoRequestDTO);
            return ResponseEntity.status(201).body("Guardado con exito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }


}
