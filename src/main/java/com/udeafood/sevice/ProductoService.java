package com.udeafood.sevice;

import com.udeafood.DTO.ProductoDTO;
import com.udeafood.DTO.ProductoRequestDTO;
import com.udeafood.DTO.SearchResult;
import com.udeafood.mapper.ProductoMapper;
import com.udeafood.model.*;
import com.udeafood.model.util.TipoTienda;
import com.udeafood.repository.IProductoRepository;
import com.udeafood.sevice.interfaces.ICategoriaService;
import com.udeafood.sevice.interfaces.IProductoService;
import com.udeafood.sevice.interfaces.ISeccionTiendaService;
import com.udeafood.sevice.interfaces.ITiendaService;
import com.udeafood.sevice.mongodb.IngredienteProductoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

    private final IProductoRepository iProductoRepository;
    private final ImagenProductoService imagenProductoService;
    private final ICategoriaService iCategoriaService;
    private final ISeccionTiendaService iSeccionTiendaService;
    private final ProductoMapper productoMapper;
    private final ITiendaService iTiendaService;
    private final IngredienteProductoService ingredienteProductoService;

    @Override
    public List<ProductoDTO> getAll() {
        return productoMapper.listProductoToListProductoDTO(iProductoRepository.findAll());
    }

    @Override
    public List<ProductoDTO> getAllByIdSeccionTienda(Integer idSeccion) {
        return productoMapper.listProductoToListProductoDTO(iProductoRepository.findBySeccionTienda_IdSeccionTienda(idSeccion));
    }

    @Override
    public List<ProductoDTO> getAllByIdTienda(Integer idTienda) {
        return productoMapper.listProductoToListProductoDTO(iProductoRepository.findAllByIdTienda(idTienda));
    }

    @Override
    public ProductoDTO getByIdProducto(Integer idProducto) {
        Optional<Producto> producto1 = iProductoRepository.findById(idProducto);

        // Return empty object
        return producto1.map(productoMapper::productoToProductoDTO).orElse(null);
    }

    @Override
    public List<ProductoDTO> getByNombreCategoria(String categoria) {
        return productoMapper.listProductoToListProductoDTO(iProductoRepository.findAllByNombreCategoria(categoria));
    }

    @Override
    public List<ProductoDTO> getByIdCategoria(Integer idCategoria) {
        return productoMapper.listProductoToListProductoDTO(iProductoRepository.findAllByIdCategoria(idCategoria));
    }

    @Override
    public SearchResult<ProductoDTO> getByNombreProducto(
            String nombre,
            String buscarEn,
            String ordenarPor,
            String tipoOrden,
            Integer page,
            Integer size
    ) {
        Sort.Direction direction = tipoOrden.equalsIgnoreCase("ascendente") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, ordenarPor);
        Pageable pageable = PageRequest.of(page, size, sort);

        TipoTienda tipo = null;

        if (buscarEn != null && !buscarEn.equalsIgnoreCase("todas")) {
            tipo = TipoTienda.valueOf(buscarEn.toUpperCase());
        }
        Page<Producto> productosPage = iProductoRepository.findAllByNombre(nombre, tipo, pageable);
        return new SearchResult<ProductoDTO>(
                productosPage.getContent().stream().map(productoMapper::productoToProductoDTO).toList(),
                productosPage.getNumber(),
                productosPage.getSize(),
                productosPage.getTotalElements(),
                productosPage.getTotalPages());
    }

    @Override
    public void save(ProductoRequestDTO productoRequestDTO) {

        Producto newProducto = new Producto();
        newProducto.setNombre(productoRequestDTO.getNombre());
        newProducto.setDescripcion(productoRequestDTO.getDescripcion());
        newProducto.setPrecio(productoRequestDTO.getPrecio());
        newProducto.setDisponibilidad(productoRequestDTO.getDisponibilidad());

        //newProducto.setImagenesProducto(productoDTO.getImagenes());

        newProducto.setCategorias(verifyCategories(productoRequestDTO.getCategorias()));


        // Validate that selected SeccionTienda exist and if not, create a default one
        List<SeccionTienda> existingSeccionTienda = iSeccionTiendaService.getByTiendaId(productoRequestDTO.getIdTienda());

        SeccionTienda seccionTienda = verifySeccionTienda(existingSeccionTienda, productoRequestDTO.getIdTienda(), productoRequestDTO.getIdSeccionTienda());

        newProducto.setSeccionTienda(seccionTienda);


        // Save the product
        Producto savedProducto = iProductoRepository.save(newProducto);


        // If the product has images, save them
        for (String enlaceImagen : productoRequestDTO.getImagenes()) {

            ImagenProducto newImagenProducto = new ImagenProducto();
            newImagenProducto.setEnlaceImagen(enlaceImagen);

            newImagenProducto.setProducto(savedProducto);
            imagenProductoService.save(newImagenProducto);

        }
        // Save the ingredients
        IngredienteProducto newIngredienteProducto = productoRequestDTO.getIngredienteProducto();
        if (newIngredienteProducto != null) {
            newIngredienteProducto.setIdProducto(savedProducto.getIdProducto());
            if (iTiendaService.existsById(newIngredienteProducto.getIdTienda())) {
                newIngredienteProducto.setIdTienda(newIngredienteProducto.getIdTienda());
            } else {
                throw new IllegalArgumentException("Selected Tienda does not exist. Error to create product");
            }
            if (newIngredienteProducto.getIngredientes() != null && !newIngredienteProducto.getIngredientes().isEmpty()) {

                if ( newIngredienteProducto.getIngredientes().stream().anyMatch(ingrediente ->
                        ingrediente.getNombre() == null ||
                        ingrediente.getNombre().isEmpty() ||
                        ingrediente.getOpciones() == null ||
                        ingrediente.getOpciones().isEmpty() ||
                        ingrediente.getOpciones().stream().anyMatch(opcion -> opcion.getNombre() == null || opcion.getNombre().isEmpty())))
                {
                    throw new IllegalArgumentException("El nombre del ingrediente está vacio o no tiene opciones. Error al crear el producto");
                }
            }


            // Save the ingredient product
            ingredienteProductoService.guardarIngredientes(newIngredienteProducto);
        }


    }





    protected List<Categoria> verifyCategories(List<Integer> categoriesIds) {
        System.out.println("Existing Categorias: " + categoriesIds);

        if(categoriesIds != null && !categoriesIds.isEmpty()){
            List<Categoria> categoryList = iCategoriaService.getAllByIds(categoriesIds);

            if(categoryList.size() != categoriesIds.size()) {
                throw new IllegalArgumentException("Alguna de las categorias no existe. El producto no se ha creado");
            }
            System.out.println("Final Categorias: " + categoryList);
            return categoryList;
        }

        return List.of();
    }


    protected SeccionTienda verifySeccionTienda(List<SeccionTienda> existingSeccionTienda, Integer idTienda, Integer idSeccionTienda) {

        if(existingSeccionTienda.isEmpty()){
            // Create a default SeccionTienda if none exist
            SeccionTienda defaultSeccionTienda = new SeccionTienda();
            defaultSeccionTienda.setNombre("Productos");

            Tienda tienda = iTiendaService.getTiendaById(idTienda);
            if(tienda == null){
                throw new IllegalArgumentException("El tienda no existe. Error al crear un producto");
            }
            defaultSeccionTienda.setTienda(tienda);
            return iSeccionTiendaService.saveDefault(defaultSeccionTienda);
        }else{
            // Check if the selected SeccionTienda exists
            boolean seccionExists = existingSeccionTienda.stream()
                    .anyMatch(seccion -> (seccion.getIdSeccionTienda() == idSeccionTienda));

            if (!seccionExists) {
                throw new IllegalArgumentException("La seccion seleccionada no existe. Error al crear producto");
            }

            return existingSeccionTienda.stream().filter(
                    seccion -> seccion.getIdSeccionTienda() == idSeccionTienda
            ).findFirst().orElse(null);

        }


    }



    @Override
    public void delete(Integer id) {
        if (!iProductoRepository.existsById(id)) {
            throw new IllegalArgumentException("El producto con el ID proporcionado no existe.");
        }

        // Eliminar el IngredienteProducto asociado al producto
        ingredienteProductoService.obtenerIngredientesPorProductoId(id)
                .ifPresent(ingredienteProducto -> ingredienteProductoService.eliminarPorId(ingredienteProducto.getId()));

        // Eliminar el producto
        iProductoRepository.deleteById(id);
    }
}
