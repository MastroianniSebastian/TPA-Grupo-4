package ar.edu.utn.frba.dds.modelo.georefapi;

import java.util.ArrayList;
import java.util.List;

public class CommonProvinciaWrapper<T> {
  private Integer cantidad;
  private Integer inicio;
  private Object parametros;
  private List<ProvinciaWrapper> provincias;
  private Integer total;

  public CommonProvinciaWrapper() {
  }

  public CommonProvinciaWrapper(Integer cantidad, Integer inicio, Object parametros,
                                List<ProvinciaWrapper> provincias, Integer total) {
    this.cantidad = cantidad;
    this.inicio = inicio;
    this.parametros = parametros;
    this.provincias = new ArrayList<>(provincias);
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

  public List<ProvinciaWrapper> getProvincias() {
    return provincias.stream().toList();
  }


  public Integer getTotal() {
    return total;
  }

}
