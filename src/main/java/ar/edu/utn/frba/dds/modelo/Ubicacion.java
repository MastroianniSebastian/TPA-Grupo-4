package ar.edu.utn.frba.dds.modelo;

public class Ubicacion {
  private String nombre;

  //TODO sacar Id por que es del otro sistema no de este
  private Integer id;
  private Double latitud;
  private Double longitud;

  public Ubicacion() {
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Double getLatitud() {
    return latitud;
  }

  public void setLatitud(Double latitud) {
    this.latitud = latitud;
  }

  public Double getLongitud() {
    return longitud;
  }

  public void setLongitud(Double longitud) {
    this.longitud = longitud;
  }
}
