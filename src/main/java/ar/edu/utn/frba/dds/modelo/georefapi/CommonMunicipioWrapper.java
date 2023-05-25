package ar.edu.utn.frba.dds.modelo.georefapi;

import java.util.ArrayList;
import java.util.List;

public class CommonMunicipioWrapper {

  private Integer cantidad;
  private Integer inicio;
  private Object parametros;
  private List<MunicipioWrappper> municipios;
  private Integer total;

  public CommonMunicipioWrapper() {
  }

  public CommonMunicipioWrapper(Integer cantidad, Integer inicio, Object parametros,
                                List<MunicipioWrappper> municipios, Integer total) {
    this.cantidad = cantidad;
    this.inicio = inicio;
    this.parametros = parametros;
    this.municipios = new ArrayList<>(municipios);
    this.total = total;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public Integer getInicio() {
    return inicio;
  }

  public Object getParametros() {
    return parametros;
  }

  public List<MunicipioWrappper> getMunicipios() {
    return municipios.stream().toList();
  }

  public Integer getTotal() {
    return total;
  }
}
