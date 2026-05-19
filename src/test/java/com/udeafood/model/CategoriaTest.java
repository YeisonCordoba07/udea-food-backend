package com.udeafood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    Categoria categoria;


    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
    }






    @Test
    void getIdCategoria() {

        assertEquals(1, categoria.getIdCategoria());
    }




    @Test
    void getNombre() {
        String nombreCategoria = "nombre categoria";
        categoria.setNombre(nombreCategoria);

        assertEquals(nombreCategoria, categoria.getNombre());
    }




    @Test
    void getDescripcion() {
        String descripcionCategoria = "descripcion categoria";
        categoria.setDescripcion(descripcionCategoria);

        assertEquals(descripcionCategoria, categoria.getDescripcion());
    }




    @Test
    void getProductos() {

        Producto producto1 = new Producto();
        producto1.setIdProducto(1);
        producto1.setNombre("nombre producto1");

        Producto producto2 = new Producto();
        producto2.setIdProducto(2);
        producto2.setNombre("nombre producto2");

        List<Producto> listaProductos = List.of(producto1, producto2);


        categoria.setProductos(listaProductos);

        assertEquals(producto1, categoria.getProductos().get(0));
        assertEquals(producto2, categoria.getProductos().get(1));
        assertEquals(producto1.getIdProducto(), categoria.getProductos().get(0).getIdProducto());
        assertEquals(producto2.getIdProducto(), categoria.getProductos().get(1).getIdProducto());
        assertEquals(producto1.getNombre(), categoria.getProductos().get(0).getNombre());
        assertEquals(producto2.getNombre(), categoria.getProductos().get(1).getNombre());
    }




    @Test
    void getTiendas() {

        Tienda tienda1 = new Tienda();
        tienda1.setIdTienda(1);
        tienda1.setNombre("nombre tienda1");

        Tienda tienda2 = new Tienda();
        tienda2.setIdTienda(2);
        tienda2.setNombre("nombre tienda2");

        List<Tienda> listaTiendas = List.of(tienda1, tienda2);


        categoria.setTiendas(listaTiendas);

        assertEquals(tienda1, categoria.getTiendas().get(0));
        assertEquals(tienda2, categoria.getTiendas().get(1));
        assertEquals(tienda1.getIdTienda(), categoria.getTiendas().get(0).getIdTienda());
        assertEquals(tienda2.getIdTienda(), categoria.getTiendas().get(1).getIdTienda());
        assertEquals(tienda1.getNombre(), categoria.getTiendas().get(0).getNombre());
        assertEquals(tienda2.getNombre(), categoria.getTiendas().get(1).getNombre());
    }




    @Test
    void setIdCategoria() {
        Integer idCategoria = 500;
        categoria.setIdCategoria(idCategoria);

        assertEquals(idCategoria, categoria.getIdCategoria());
    }



    @Test
    void setNombre() {
        String nombreCategoria = "nuevo nombre categoria";
        categoria.setNombre(nombreCategoria);

        assertEquals(nombreCategoria, categoria.getNombre());
    }



    @Test
    void setDescripcion() {
        String descripcionCategoria = "nuevo nombre categoria";
        categoria.setDescripcion(descripcionCategoria);

        assertEquals(descripcionCategoria, categoria.getDescripcion());
    }




    @Test
    void setProductos() {
        Producto producto3 = new Producto();
        producto3.setIdProducto(300);
        producto3.setNombre("nombre producto300");

        Producto producto4 = new Producto();
        producto4.setIdProducto(400);
        producto4.setNombre("nombre producto400");

        List<Producto> listaProductos = List.of(producto3, producto4);


        categoria.setProductos(listaProductos);

        assertEquals(producto3, categoria.getProductos().get(0));
        assertEquals(producto4, categoria.getProductos().get(1));
        assertEquals(producto3.getIdProducto(), categoria.getProductos().get(0).getIdProducto());
        assertEquals(producto4.getIdProducto(), categoria.getProductos().get(1).getIdProducto());
        assertEquals(producto3.getNombre(), categoria.getProductos().get(0).getNombre());
        assertEquals(producto4.getNombre(), categoria.getProductos().get(1).getNombre());
        assertEquals(listaProductos.size(), categoria.getProductos().size());
    }




    @Test
    void setTiendas() {
        Tienda tienda3 = new Tienda();
        tienda3.setIdTienda(300);
        tienda3.setNombre("nombre tienda300");

        Tienda tienda4 = new Tienda();
        tienda4.setIdTienda(400);
        tienda4.setNombre("nombre tienda400");

        List<Tienda> listaTiendas = List.of(tienda3, tienda4);


        categoria.setTiendas(listaTiendas);

        assertEquals(tienda3, categoria.getTiendas().get(0));
        assertEquals(tienda4, categoria.getTiendas().get(1));
        assertEquals(tienda3.getIdTienda(), categoria.getTiendas().get(0).getIdTienda());
        assertEquals(tienda4.getIdTienda(), categoria.getTiendas().get(1).getIdTienda());
        assertEquals(tienda3.getNombre(), categoria.getTiendas().get(0).getNombre());
        assertEquals(tienda4.getNombre(), categoria.getTiendas().get(1).getNombre());
        assertEquals(listaTiendas.size(), categoria.getTiendas().size());
    }


}