package com.udeafood.sevice;

import com.udeafood.DTO.NuevaSeccionTiendaDTO;
import com.udeafood.model.SeccionTienda;
import com.udeafood.model.Tienda;
import com.udeafood.repository.ISeccionTiendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SeccionTiendaService {

    private final ISeccionTiendaRepository iSeccionTiendaRepository;
    private final TiendaService tiendaService;


    public List<SeccionTienda> getAll(){
        return iSeccionTiendaRepository.findAll();
    }


    public List<SeccionTienda> getByTiendaId(Integer idTienda){
        return iSeccionTiendaRepository.findAllByIdTienda(idTienda);
    }


    public SeccionTienda saveDefault(SeccionTienda defaultSeccionTienda) {
        return iSeccionTiendaRepository.save(defaultSeccionTienda);
    }


    public void create(NuevaSeccionTiendaDTO nuevaSeccionTiendaDTO) {

        if (nuevaSeccionTiendaDTO == null) {
            throw new IllegalArgumentException("El objeto no puede ser nulo");
        }

        if (nuevaSeccionTiendaDTO.getNombre() == null || nuevaSeccionTiendaDTO.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la sección no puede ser nulo o vacío");
        }

        if (nuevaSeccionTiendaDTO.getIdTienda() == null) {
            throw new IllegalArgumentException("El ID de la tienda no puede ser nulo");
        }

        Tienda tienda = tiendaService.getTiendaById(nuevaSeccionTiendaDTO.getIdTienda());
        if (tienda == null) {
            throw new IllegalArgumentException("La tienda con el ID proporcionado no existe");
        }

        SeccionTienda seccionTienda = new SeccionTienda();
        seccionTienda.setNombre(nuevaSeccionTiendaDTO.getNombre());
        seccionTienda.setTienda(tienda);

        iSeccionTiendaRepository.save(seccionTienda);
    }
}
