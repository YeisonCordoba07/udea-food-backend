package com.udeafood.sevice;

import com.udeafood.DTO.ProductoConImagenDTO;
import com.udeafood.DTO.ProductoDTO;
import com.udeafood.model.*;
import com.udeafood.repository.IProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


    public List<Producto> getByIdSeccionTienda(Integer idSeccion){
        return iProductoRepository.findBySeccionTienda_IdSeccionTienda(idSeccion);
    }


    public List<Producto> getByIdTienda(Integer idTienda){
        return iProductoRepository.findAllByIdTienda(idTienda);
    }


    public ProductoConImagenDTO getByIdProducto(Integer idProducto){
        Optional<Producto> producto1 = iProductoRepository.findById(idProducto);

        if (producto1.isEmpty()) {
            return null; // Return empty object
        }
        List<ImagenProducto> listImagenProducto = imagenProductoService.getAllByIdProducto(idProducto);

        return getProductoConImagenDTO(producto1, listImagenProducto);
    }

    private static ProductoConImagenDTO getProductoConImagenDTO(Optional<Producto> producto1, List<ImagenProducto> listImagenProducto) {
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

        newProducto.setImagenesProducto(productoDTO.getImagenes());

        List<Categoria> categoryList = productoDTO.getCategorias();
        List<Categoria> existingCategories = categoriaService.getAll();
        for (Categoria category : categoryList) {
            if (!existingCategories.contains(category)) {
                throw new IllegalArgumentException("Category " + category.getNombre() + " does not exist. Error to create product");
            }
        }
        newProducto.setCategorias(categoryList);


        List<SeccionTienda> existingSeccionTienda = seccionTiendaService.getByTiendaId(productoDTO.getIdTienda());

        if(!existingSeccionTienda.contains(productoDTO.getSeccionTienda())){
            throw new IllegalArgumentException("Selected SeccionTienda does not exist. Error to create product");

        }else if(existingSeccionTienda.isEmpty()){

            Tienda auxTienda = new Tienda();
            auxTienda.setIdTienda(productoDTO.getIdTienda());

            SeccionTienda defaultSeccionTienda = new SeccionTienda();
            defaultSeccionTienda.setIdSeccionTienda(1);
            defaultSeccionTienda.setNombre("Productos");
            defaultSeccionTienda.setTienda(auxTienda);


        }else{
            newProducto.setSeccionTienda(productoDTO.getSeccionTienda());

        }

        for(ImagenProducto imagenProducto : productoDTO.getImagenes()){

            if(imagenProducto != null){

                ImagenProducto newImagenProducto = new ImagenProducto();
                newImagenProducto.setEnlaceImagen(imagenProducto.getEnlaceImagen());

                Producto auxProducto = new Producto();
                auxProducto.setIdProducto(newProducto.getIdProducto());

                newImagenProducto.setProducto(auxProducto);
                imagenProductoService.save(newImagenProducto);


            }
        }


        iProductoRepository.save(newProducto);
    }
}
