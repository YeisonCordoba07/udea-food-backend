package com.udeafood.sevice.interfaces;

import com.udeafood.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> getAll();
    Usuario getUsuarioById(Integer id);
}
