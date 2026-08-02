package com.udeafood.controller;

import com.udeafood.DTO.ProductoDTO;
import com.udeafood.DTO.ProductoRequestDTO;
import com.udeafood.DTO.SearchResult;
import com.udeafood.sevice.interfaces.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final IProductoService iProductoService;



    // GET -------------------------------------------------------------------------------
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductoDTO>> getAll(){
        return ResponseEntity.ok( iProductoService.getAll());
    }


    @GetMapping("/buscarPorIdSeccion")
    public ResponseEntity<List<ProductoDTO>> getByIdSeccion(@RequestParam Integer idSeccion){
        return ResponseEntity.ok(iProductoService.getAllByIdSeccionTienda(idSeccion));
    }


    @GetMapping("/buscarPorIdTienda")
    public ResponseEntity<List<ProductoDTO>> getByIdTienda(@RequestParam Integer idTienda){
        return ResponseEntity.ok(iProductoService.getAllByIdTienda(idTienda));
    }


    @GetMapping("/buscarPorIdProducto")
    public ResponseEntity<ProductoDTO> getByIdProducto(@RequestParam Integer idProducto){
        return ResponseEntity.ok(iProductoService.getByIdProducto(idProducto));
    }


    @GetMapping("/buscarPorNombreCategoria")
    public ResponseEntity<List<ProductoDTO>> getByNombreCategoria(@RequestParam String categoria){
        return ResponseEntity.ok(iProductoService.getByNombreCategoria(categoria));
    }

    @GetMapping("/buscarPorIdCategoria")
    public ResponseEntity<List<ProductoDTO>> getByIdCategoria(@RequestParam Integer idCategoria){
        return ResponseEntity.ok(iProductoService.getByIdCategoria(idCategoria));
    }

    @GetMapping("/buscarPorNombreProducto")
    public ResponseEntity<SearchResult<ProductoDTO>> getByNombreProducto(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "productos") String mostrarSolo,
            @RequestParam(defaultValue = "todas") String buscarEn,
            @RequestParam(defaultValue = "nombre") String ordenarPor,
            @RequestParam(defaultValue = "ascendente") String tipoOrden,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size){

        return ResponseEntity.ok(iProductoService.getByNombreProducto(nombre, mostrarSolo, buscarEn, ordenarPor, tipoOrden, page, size));
    }




    // POST ------------------------------------------------------------------------------
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> save(@RequestBody ProductoRequestDTO productoRequestDTO) {
        try {
            iProductoService.save(productoRequestDTO);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Creado con exito");

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error: " + e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(error);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error interno del servidor");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(error);
        }
    }


    // DELETE ------------------------------------------------------------------------------
    @DeleteMapping("/borrar")
    public ResponseEntity<?> delete(@RequestParam Integer id) {
        try {
            iProductoService.delete(id);
            return ResponseEntity.status(200).body("Eliminado con exito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }


}
