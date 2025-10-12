package com.udeafood.controller;


import com.udeafood.DTO.PerfilTiendaDTO;
import com.udeafood.model.Tienda;
import com.udeafood.sevice.interfaces.ITiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tienda")
@RequiredArgsConstructor
public class TiendaController {

    private final ITiendaService iTiendaService;




    @GetMapping("/obtenerTodas")
    public ResponseEntity<List<Tienda>> getAll(){

        return ResponseEntity.ok( iTiendaService.getAll());
    }

    @GetMapping("/obtenerTodasPorTipo")
    public ResponseEntity<List<Tienda>> getAllByType(@RequestParam String tipoTienda){
        return ResponseEntity.ok(iTiendaService.getAllByType(tipoTienda));
    }


    @GetMapping("/buscarPorId")
    public ResponseEntity<Tienda> getTiendaById(@RequestParam Integer id){
        return ResponseEntity.ok(iTiendaService.getTiendaById(id));
    }

    @GetMapping("/perfilTienda")
    public ResponseEntity<PerfilTiendaDTO> getPerfilTienda(@RequestParam Integer id){
        return ResponseEntity.ok(iTiendaService.getPerfilTienda(id));
    }


    @GetMapping("/buscarPorNombre")
    public ResponseEntity<List<Tienda>> getTiendaByNombre(@RequestParam String nombre){
        return ResponseEntity.ok(iTiendaService.getTiendaByNombre(nombre));
    }


    @GetMapping("/buscarPorNombreCategoria")
    public ResponseEntity<List<Tienda>> getTiendaPorNombreCategoria(@RequestParam String nombreCategoria){
        return ResponseEntity.ok(iTiendaService.getTiendaPorNombreCategoria(nombreCategoria));
    }


    @GetMapping("/buscarPorIdUsuario")
    public ResponseEntity<List<Tienda>> getTiendaByUsuario(@RequestParam Integer idUsuario){
        return ResponseEntity.ok(iTiendaService.getTiendaByIdUsuario(idUsuario));
    }

    @GetMapping("/buscarPorNombreUsuario")
    public ResponseEntity<List<Tienda>> getTiendaByNombreUsuario(@RequestParam String nombreUsuario){
        return ResponseEntity.ok(iTiendaService.getTiendaByNombreUsuario(nombreUsuario));
    }

    @GetMapping("/buscarPorIdProducto")
    public ResponseEntity<Integer> getTiendaByIdProducto(@RequestParam Integer idProducto){
        return ResponseEntity.ok(iTiendaService.getIdTiendaByIdProducto(idProducto));
    }

}
