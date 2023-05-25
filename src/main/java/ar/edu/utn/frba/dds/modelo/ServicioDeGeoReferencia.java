package ar.edu.utn.frba.dds.modelo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface ServicioDeGeoReferencia {

  public Ubicacion getProvinciaByNombre(Ubicacion ubicacion);

  public List<Ubicacion> getMunicipiosByNombreDeProvincia(Ubicacion ubicacion);

  public List<Ubicacion> getDepartamentosByNombreDeProvincia(Ubicacion ubicacion);

  public List<Ubicacion> getProvincias();

  public Ubicacion getDepartamentoByLatitudAndLongitud(Ubicacion ubicacion);

  public Ubicacion getMunicipioByLatitudAndLongitud(Ubicacion ubicacion);

  public Ubicacion getProvinciaByLatitudAndLongitud(Ubicacion ubicacion);
}

