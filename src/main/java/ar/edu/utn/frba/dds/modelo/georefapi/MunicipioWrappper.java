package ar.edu.utn.frba.dds.modelo.georefapi;

public class MunicipioWrappper {

  private String id;
  private String nombre;

  private CentroideWrapper centroide;

  private ProvinciaWrapper provincia;

  public MunicipioWrappper() {
  }

  public MunicipioWrappper(String id, String nombre, CentroideWrapper centroide,
                           ProvinciaWrapper provincia) {
    this.id = id;
    this.nombre = nombre;
    this.centroide = centroide;
    this.provincia = provincia;
  }

  public String getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public CentroideWrapper getCentroide() {
    return centroide;
  }

  public ProvinciaWrapper getProvincia() {
    return provincia;
  }
}
