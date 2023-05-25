package ar.edu.utn.frba.dds.modelo.georefapi;

import java.util.ArrayList;
import java.util.List;

public class CommonDepartamentoWrapper {

  private Integer cantidad;
  private Integer inicio;
  private Object parametros;
  private List<DepartamentoWrapper> departamentos;
  private Integer total;

  public CommonDepartamentoWrapper() {
  }

  public CommonDepartamentoWrapper(Integer cantidad, Integer inicio, Object parametros,
                                   List<DepartamentoWrapper> departamentos, Integer total) {
    this.cantidad = cantidad;
    this.inicio = inicio;
    this.parametros = parametros;
    this.departamentos = new ArrayList<>(departamentos);
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

  public List<DepartamentoWrapper> getDepartamentos() {
    List<DepartamentoWrapper> departamentoWrappers = departamentos.stream().toList();
    return departamentoWrappers;
  }

  public Integer getTotal() {
    return total;
  }
}
