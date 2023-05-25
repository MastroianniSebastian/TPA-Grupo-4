package ar.edu.utn.frba.dds.modelo.georefapi;

public class ProvinciaWrapper {
  private Integer id;
  private String nombre;

  private CentroideWrapper centroide;

  public ProvinciaWrapper() {
  }

  public ProvinciaWrapper(Integer id, String nombre, CentroideWrapper centroide) {
    this.id = id;
    this.nombre = nombre;
    this.centroide = centroide;
  }

  public Integer getId() {
    return id;
  }


  public String getNombre() {
    return nombre;
  }

  public CentroideWrapper getCentroide() {
    return centroide;
  }

}
