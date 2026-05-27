package com.udeafood.sevice;

import com.udeafood.model.Categoria;
import com.udeafood.sevice.interfaces.ICategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    ICategoriaService iCategoriaService;

    @InjectMocks
    ProductoService productoService;



    @Test
    void verifyCategoriesWithValidIds() {
        // Arrange - Preparar datos
        Categoria categoria1 = new Categoria();
        categoria1.setIdCategoria(100);
        categoria1.setNombre("Comida Rápida");

        Categoria categoria2 = new Categoria();
        categoria2.setIdCategoria(200);
        categoria2.setNombre("Postres");

        List<Categoria> categoriasEsperadas = List.of(categoria1, categoria2);

        List<Integer> idsABuscar = List.of(100, 200);

        // Mock del servicio
        when(iCategoriaService.getAllByIds(idsABuscar)).thenReturn(categoriasEsperadas);

        // Act - Ejecutar
        List<Categoria> resultado = productoService.verifyCategories(idsABuscar);

        // Assert - Verificar
        assertEquals(2, resultado.size(), "Debe retornar 2 categorías");
        assertEquals(categoria1, resultado.get(0));
        assertEquals(categoria2, resultado.get(1));
        assertEquals(categoria1.getNombre(), resultado.get(0).getNombre());
        assertEquals(categoria2.getNombre(), resultado.get(1).getNombre());

        verify(iCategoriaService, times(1)).getAllByIds(idsABuscar);
    }


    @Test
    void verifyCategoriesWithSingleId() {
        // Arrange
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(5);
        categoria.setNombre("Bebidas");

        List<Integer> idsABuscar = List.of(5);
        when(iCategoriaService.getAllByIds(idsABuscar)).thenReturn(List.of(categoria));

        // Act
        List<Categoria> resultado = productoService.verifyCategories(idsABuscar);

        // Assert
        assertEquals(1, resultado.size());
        assertEquals(categoria.getNombre(), resultado.get(0).getNombre());
        verify(iCategoriaService, times(1)).getAllByIds(idsABuscar);
    }



    @Test
    void verifyCategoriesWithEmptyList() {
        // Arrange - Lista vacía
        List<Integer> idsABuscar = List.of();

        // Act
        List<Categoria> resultado = productoService.verifyCategories(idsABuscar);

        // Assert
        assertTrue(resultado.isEmpty(), "Debe retornar lista vacía");
        assertEquals(0, resultado.size());
        verify(iCategoriaService, times(0)).getAllByIds(idsABuscar);
    }



    @Test
    void verifyCategoriesWithNull() {

        // Act
        List<Categoria> resultado = productoService.verifyCategories(null);

        // Assert
        assertTrue(resultado.isEmpty(), "Cuando es null, debe retornar lista vacía");
        verify(iCategoriaService, times(0)).getAllByIds(List.of());
    }



    @Test
    void verifyCategoriesWithPartialMatch() {
        // Arrange - Se buscan 3 IDs pero solo 2 existen
        Categoria categoria1 = new Categoria();
        categoria1.setIdCategoria(100);

        Categoria categoria2 = new Categoria();
        categoria2.setIdCategoria(200);

        List<Integer> idsABuscar = List.of(100, 200, 9999); // 9999 no existe

        when(iCategoriaService.getAllByIds(idsABuscar))
                .thenReturn(List.of(categoria1, categoria2));


        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> productoService.verifyCategories(idsABuscar),
                "Debe lanzar excepción si faltan categorías");
        verify(iCategoriaService, times(1)).getAllByIds(idsABuscar);
    }


    @Test
    void verifyCategoriesWithNoMatchingIds() {
        // Arrange - Se buscan IDs que no existen
        List<Integer> idsABuscar = List.of(999, 888, 777);

        when(iCategoriaService.getAllByIds(idsABuscar))
                .thenReturn(List.of());

        // Assert
        assertThrows(IllegalArgumentException.class, () -> productoService.verifyCategories(idsABuscar),
                "Debe lanzar excepción si por lo menos una de las categorias no existe");
        verify(iCategoriaService, times(1)).getAllByIds(idsABuscar);
    }



    @Test
    void verifyCategoriesServiceNotCalledWithEmptyList() {

        // Act
        productoService.verifyCategories(List.of());

        // Assert
        verify(iCategoriaService, times(0)).getAllByIds(List.of());
    }


}