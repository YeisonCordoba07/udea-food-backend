package com.udeafood.sevice;

import com.udeafood.DTO.ProductoDTO;
import com.udeafood.DTO.ProductoRequestDTO;
import com.udeafood.mapper.ProductoMapper;
import com.udeafood.model.*;
import com.udeafood.repository.IProductoRepository;
import com.udeafood.sevice.interfaces.IProductoService;
import com.udeafood.sevice.mongodb.IngredienteProductoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

    private final IProductoRepository iProductoRepository;
    private final ImagenProductoService imagenProductoService;
    private final CategoriaService categoriaService;
    private final SeccionTiendaService seccionTiendaService;
    private final ProductoMapper productoMapper;
    private final TiendaService tiendaService;
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
    public List<ProductoDTO> getByNombreProducto(String nombre) {
        return productoMapper.listProductoToListProductoDTO(iProductoRepository.findAllByNombre(nombre));
    }

    @Override
    public void save(ProductoRequestDTO productoRequestDTO) {

        Producto newProducto = new Producto();
        newProducto.setNombre(productoRequestDTO.getNombre());
        newProducto.setDescripcion(productoRequestDTO.getDescripcion());
        newProducto.setPrecio(productoRequestDTO.getPrecio());
        newProducto.setDisponibilidad(productoRequestDTO.getDisponibilidad());

        //newProducto.setImagenesProducto(productoDTO.getImagenes());


        // Validate that selected categories exist
        List<Categoria> existingCategories = categoriaService.getAll();

        List<Integer> existingCategoryIds = existingCategories.stream()
                .map(Categoria::getIdCategoria)
                .toList();

        List<Integer> categoryList = productoRequestDTO.getCategorias().stream()
                .filter(categoria -> existingCategoryIds.contains(categoria))
                .toList();

        if (categoryList.size() != productoRequestDTO.getCategorias().size()) {
            throw new IllegalArgumentException("Some categories do not exist. Error to create product");
        }
        List<Categoria> newCategoryList = new ArrayList<>();
        for (int c : categoryList) {
            newCategoryList.add(categoriaService.getById(c));
        }

        newProducto.setCategorias(newCategoryList);


        // Validate that selected SeccionTienda exist and if not, create a default one
        List<SeccionTienda> existingSeccionTienda = seccionTiendaService.getByTiendaId(productoRequestDTO.getIdTienda());

        if (existingSeccionTienda.isEmpty()) {
            // Create a default SeccionTienda if none exist
            SeccionTienda defaultSeccionTienda = new SeccionTienda();
            defaultSeccionTienda.setNombre("Productos");

            Tienda auxTienda = new Tienda();
            auxTienda.setIdTienda(productoRequestDTO.getIdTienda());

            defaultSeccionTienda.setTienda(auxTienda);
            SeccionTienda savedSeccionTienda = seccionTiendaService.saveDefault(defaultSeccionTienda);
            newProducto.setSeccionTienda(savedSeccionTienda);
        } else {
            // Check if the selected SeccionTienda exists
            boolean seccionExists = existingSeccionTienda.stream()
                    .anyMatch(seccion -> (seccion.getIdSeccionTienda() == productoRequestDTO.getIdSeccionTienda()));

            if (!seccionExists) {
                throw new IllegalArgumentException("Selected SeccionTienda does not exist. Error to create product");
            }

            // Set the existing SeccionTienda
            SeccionTienda auxSeccionTienda = new SeccionTienda();
            auxSeccionTienda.setIdSeccionTienda(productoRequestDTO.getIdSeccionTienda());
            /*  THE NAME NO MATTER */
            auxSeccionTienda.setNombre("thing nombre");
            newProducto.setSeccionTienda(auxSeccionTienda);
        }


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
            if (tiendaService.existsById(newIngredienteProducto.getIdTienda())) {
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
}
