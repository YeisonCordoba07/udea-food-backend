package com.udeafood.controller;


import com.udeafood.model.Usuario;
import com.udeafood.sevice.interfaces.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService iUsuarioService;




    @GetMapping("/getAll")
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(iUsuarioService.getAll());
    }

    @GetMapping("/buscarPorId")
    public ResponseEntity<Usuario> getUsuarioById(@RequestParam Integer id) {
        return ResponseEntity.ok(iUsuarioService.getUsuarioById(id));
    }
}
