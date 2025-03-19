package com.udeafood.sevice;

import com.udeafood.DTO.ProductoConImagenDTO;
import com.udeafood.DTO.ProductoDTO;
import com.udeafood.model.*;
import com.udeafood.repository.IProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
@RequiredArgsConstructor
public class ProductoService {

    private final IProductoRepository iProductoRepository;
    private final ImagenProductoService imagenProductoService;
    private final CategoriaService categoriaService;
    private final SeccionTiendaService seccionTiendaService;




    public List<Producto> getAll(){
        return iProductoRepository.findAll();
    }


    public List<Producto> getAllByIdSeccionTienda(Integer idSeccion){
        return iProductoRepository.findBySeccionTienda_IdSeccionTienda(idSeccion);
    }


    public List<Producto> getAllByIdTienda(Integer idTienda){
        return iProductoRepository.findAllByIdTienda(idTienda);
    }


    public ProductoConImagenDTO getByIdProducto(Integer idProducto){
        Optional<Producto> producto1 = iProductoRepository.findById(idProducto);

        if (producto1.isEmpty()) {
            return null; // Return empty object
        }
        List<ImagenProducto> listImagenProducto = imagenProductoService.getAllByIdProducto(idProducto);


        /* VERIFY
        * is not necessary send DTO
        * */
        return productToDTO(producto1, listImagenProducto);
    }

    private static ProductoConImagenDTO productToDTO(Optional<Producto> producto1, List<ImagenProducto> listImagenProducto) {
        ProductoConImagenDTO productoConImagenDTO = new ProductoConImagenDTO();

        if(producto1.isEmpty()){
            return null;
        }
        productoConImagenDTO.setIdProducto(producto1.get().getIdProducto());
        productoConImagenDTO.setNombre(producto1.get().getNombre());
        productoConImagenDTO.setDescripcion(producto1.get().getDescripcion());
        productoConImagenDTO.setPrecio(producto1.get().getPrecio());
        productoConImagenDTO.setDisponibilidad(producto1.get().getDisponibilidad());
        productoConImagenDTO.setImagenProductos(listImagenProducto);
        return productoConImagenDTO;
    }


    public List<Producto> getByNombreCategoria(String categoria){
        return iProductoRepository.findAllByNombreCategoria(categoria);
    }


    public List<Producto> getByNombreProducto(String nombre) {
        return iProductoRepository.findAllByNombre(nombre);
    }





    public void save(ProductoDTO productoDTO) {

        Producto newProducto = new Producto();
        newProducto.setNombre(productoDTO.getNombre());
        newProducto.setDescripcion(productoDTO.getDescripcion());
        newProducto.setPrecio(productoDTO.getPrecio());
        newProducto.setDisponibilidad(productoDTO.getDisponibilidad());

        //newProducto.setImagenesProducto(productoDTO.getImagenes());



        // Validate that selected categories exist
        List<Categoria> existingCategories = categoriaService.getAll();
        System.out.println("#############################################################");
        System.out.println("EXISTIS CATEGORIES: "+ existingCategories);

        List<Integer> existingCategoryIds = existingCategories.stream()
                .map(Categoria::getIdCategoria)
                .toList();

        List<Integer> categoryList = productoDTO.getCategorias().stream()
                .filter(id -> existingCategoryIds.contains(id))
                .toList();

        if (categoryList.size() != productoDTO.getCategorias().size()) {
            throw new IllegalArgumentException("Some categories do not exist. Error to create product");
        }
        List<Categoria> newCategoryList = new ArrayList<>();
        for(Integer idCategoria : categoryList){
            newCategoryList.add(categoriaService.getById(idCategoria));
        }

        newProducto.setCategorias(newCategoryList);



        // Validate that selected SeccionTienda exist and if not, create a default one
        List<SeccionTienda> existingSeccionTienda = seccionTiendaService.getByTiendaId(productoDTO.getIdTienda());

        if (existingSeccionTienda.isEmpty()) {
            // Create a default SeccionTienda if none exist
            SeccionTienda defaultSeccionTienda = new SeccionTienda();
            defaultSeccionTienda.setNombre("Productos");

            Tienda auxTienda = new Tienda();
            auxTienda.setIdTienda(productoDTO.getIdTienda());

            defaultSeccionTienda.setTienda(auxTienda);
            SeccionTienda savedSeccionTienda = seccionTiendaService.save(defaultSeccionTienda);
            newProducto.setSeccionTienda(savedSeccionTienda);
        } else {
            // Check if the selected SeccionTienda exists
            boolean seccionExists = existingSeccionTienda.stream()
                    .anyMatch(seccion -> (seccion.getIdSeccionTienda() == productoDTO.getIdSeccionTienda()));

            if (!seccionExists) {
                throw new IllegalArgumentException("Selected SeccionTienda does not exist. Error to create product");
            }

            // Set the existing SeccionTienda
            SeccionTienda auxSeccionTienda = new SeccionTienda();
            auxSeccionTienda.setIdSeccionTienda(productoDTO.getIdSeccionTienda());
            auxSeccionTienda.setNombre("thing nombre");
            newProducto.setSeccionTienda(auxSeccionTienda);
        }



        // Save the product
        Producto savedProducto = iProductoRepository.save(newProducto);


        // If the product has images, save them
        for(String enlaceImagen : productoDTO.getImagenes()){

                ImagenProducto newImagenProducto = new ImagenProducto();
                newImagenProducto.setEnlaceImagen(enlaceImagen);

                newImagenProducto.setProducto(savedProducto);
                imagenProductoService.save(newImagenProducto);

        }


    }
}
