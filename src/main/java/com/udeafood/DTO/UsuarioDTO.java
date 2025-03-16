package com.udeafood.DTO;

import com.udeafood.model.util.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// HACER VALIDACIONES
public class UsuarioDTO {
    private String usuario;
    private String correo;
    private String clave;

    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private String documento;
    private String celular;
    private String ubicacion;
    private String foto;
}
