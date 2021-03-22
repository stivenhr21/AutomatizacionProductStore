package co.com.stivenhernandez.productstore.models.builder;

import co.com.stivenhernandez.productstore.models.productstore.DatosUsuario;
import utilities.builder.Builder;

import java.util.List;

public class DatosUsuarioBuilder implements Builder<DatosUsuario> {
  private String usuario;
  private String clave;

  public String getUsuario() {
    return usuario;
  }

  public String getClave() {
    return clave;
  }

  public DatosUsuarioBuilder(List<String> datos) {
    this.usuario = datos.get(0);
    this.clave = datos.get(1);
  }

  public static DatosUsuarioBuilder con(List<String> datos) {
    return new DatosUsuarioBuilder(datos);
  }

  @Override
  public DatosUsuario build() {
    return new DatosUsuario(this);
  }
}
