package com.udeafood.controller;


import com.udeafood.model.Usuario;
import com.udeafood.sevice.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;




    @GetMapping("/getAll")
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/buscarPorId")
    public ResponseEntity<Usuario> getUsuarioById(@RequestParam Integer id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }
}
