package com.udeafood.controller;

import com.udeafood.model.Rol;
import com.udeafood.sevice.interfaces.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolController {

    private final IRolService iRolService;




    @GetMapping("/getAll")
    public ResponseEntity<List<Rol>> getAll(){
        return ResponseEntity.ok( iRolService.getAll());
    }
}
