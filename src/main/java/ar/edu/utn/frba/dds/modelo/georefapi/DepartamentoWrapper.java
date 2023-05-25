package ar.edu.utn.frba.dds.modelo.georefapi;

public class DepartamentoWrapper {
  private CentroideWrapper centroide;
  private String id;
  private String nombre;
  private ProvinciaWrapper provincia;

  public DepartamentoWrapper() {
  }

  public DepartamentoWrapper(CentroideWrapper centroide, String id,
                             String nombre, ProvinciaWrapper provincia) {
    this.centroide = centroide;
    this.id = id;
    this.nombre = nombre;
    this.provincia = provincia;
  }

  public CentroideWrapper getCentroide() {
    return this.centroide;
  }

  public String getId() {
    return this.id;
  }

  public String getNombre() {
    return this.nombre;
  }

  public ProvinciaWrapper getProvincia() {
    return this.provincia;
  }


}
