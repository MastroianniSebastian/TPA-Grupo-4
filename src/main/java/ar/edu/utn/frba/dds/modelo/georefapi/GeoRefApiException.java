package ar.edu.utn.frba.dds.modelo.georefapi;

public class GeoRefApiException extends RuntimeException {
  GeoRefApiException(String msj, Exception e) {
    super(msj, e);
  }

  GeoRefApiException(String msj, RuntimeException e) {
    super(msj, e);
  }
}
