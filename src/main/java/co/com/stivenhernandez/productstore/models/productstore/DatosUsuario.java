package co.com.stivenhernandez.productstore.models.productstore;

import co.com.stivenhernandez.productstore.models.builder.DatosUsuarioBuilder;

public class DatosUsuario {
    private String usuario;
    private String clave;

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }


    public DatosUsuario(DatosUsuarioBuilder builder) {
        this.usuario = builder.getUsuario();
        this.clave = builder.getClave();
    }
}
