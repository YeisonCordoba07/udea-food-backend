package com.udeafood.sevice;

import com.udeafood.model.Usuario;
import com.udeafood.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository iUsuarioRepository;




    public List<Usuario> getAll(){
        return iUsuarioRepository.findAll();
    }

}
