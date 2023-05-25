package ar.edu.utn.frba.dds.modelo.georefapi;

public class CentroideWrapper {
  private Double lat;
  private Double lon;

  public CentroideWrapper() {
  }

  public CentroideWrapper(Double lat, Double lon) {
    this.lat = lat;
    this.lon = lon;
  }

  public Double getLat() {
    return lat;
  }

  public Double getLon() {
    return lon;
  }

}
