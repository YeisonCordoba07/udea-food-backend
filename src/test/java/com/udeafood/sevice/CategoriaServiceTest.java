package com.udeafood.sevice;

import com.udeafood.model.Categoria;
import com.udeafood.repository.ICategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @InjectMocks
    CategoriaService categoriaService;

    @Mock
    ICategoriaRepository iCategoriaRepository;



    @Test
    void getAllSuccessfully() {
        Categoria categoria1 = new Categoria();
        categoria1.setIdCategoria(100);
        Categoria categoria2 = new Categoria();
        categoria2.setIdCategoria(200);

        List<Categoria> listaCategoria = List.of(categoria1, categoria2);

        when(iCategoriaRepository.findAll()).thenReturn(listaCategoria);

        List<Categoria> respuesta = categoriaService.getAll();

        assertEquals(listaCategoria, respuesta);
        assertEquals(listaCategoria.size(), respuesta.size());
        assertEquals(listaCategoria.get(0).getIdCategoria(), respuesta.get(0).getIdCategoria());
        assertEquals(listaCategoria.get(1).getIdCategoria(), respuesta.get(1).getIdCategoria());
        verify(iCategoriaRepository, times(1)).findAll();

    }



    @Test
    void getAllEmptyList() {
        // Simula que la BD no tiene categorías
        when(iCategoriaRepository.findAll()).thenReturn(List.of());

        List<Categoria> respuesta = categoriaService.getAll();

        // Verifica que retorna una lista vacía
        assertTrue(respuesta.isEmpty(), "La lista debe estar vacía");
        
        // En el front llega como un JSON como: []
        verify(iCategoriaRepository, times(1)).findAll();

    }


    @Test
    void getById() {

        Categoria categoria1 = new Categoria();
        categoria1.setIdCategoria(100);

        when(iCategoriaRepository.findById(anyInt())).thenReturn(Optional.of(categoria1));

        Categoria respuesta = categoriaService.getById(anyInt());

        assertEquals(categoria1, respuesta);
        assertEquals(categoria1.getIdCategoria(), respuesta.getIdCategoria());

        verify(iCategoriaRepository, times(1)).findById(anyInt());
    }



    @Test
    void getByIdNotFound() {

        when(iCategoriaRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Verifica que lanza una excepción cuando no encuentra la categoria
        assertThrows(NoSuchElementException.class, () -> categoriaService.getById(anyInt()),
                     "Lanza NoSuchElementException cuando el id no existe");

        verify(iCategoriaRepository, times(1)).findById(anyInt());
    }
}