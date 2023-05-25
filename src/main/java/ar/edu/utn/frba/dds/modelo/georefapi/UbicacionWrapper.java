package ar.edu.utn.frba.dds.modelo.georefapi;

public class UbicacionWrapper {
  private DepartamentoWrapper departamento;
  private ProvinciaWrapper provincia;

  private MunicipioWrappper municipio;
  private Double lat;
  private Double lon;

  public UbicacionWrapper() {
  }

  public DepartamentoWrapper getDepartamento() {
    return departamento;
  }

  public ProvinciaWrapper getProvincia() {
    return provincia;
  }

  public Double getLat() {
    return lat;
  }

  public Double getLon() {
    return lon;
  }

  public MunicipioWrappper getMunicipio() {
    return municipio;
  }
}
