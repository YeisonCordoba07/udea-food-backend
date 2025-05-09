package com.udeafood.sevice;

import com.udeafood.model.Usuario;
import com.udeafood.repository.IUsuarioRepository;
import com.udeafood.sevice.interfaces.IUsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final IUsuarioRepository iUsuarioRepository;



    @Override
    public List<Usuario> getAll(){
        return iUsuarioRepository.findAll();
    }


    @Override
    public Usuario getUsuarioById(Integer id) {
        return iUsuarioRepository.findById(id).orElse(null);
    }
}
