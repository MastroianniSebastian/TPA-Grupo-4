package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.modelo.Ubicacion;
import ar.edu.utn.frba.dds.modelo.georefapi.GeoRefApi;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeoRefApiTest {

  @Test
  public void podriaTraerLasProvinciasPorNombre(){
    GeoRefApi geoRefApi = new GeoRefApi();
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.setNombre("Santiago+del+Estero");
    Assertions.assertEquals("Santiago del Estero", geoRefApi.getProvinciaByNombre(ubicacion).getNombre());
  }

  @Test
  public void podriaTraerElListadoDeMunicipiosPorNombreDeProvincia() {
    GeoRefApi geoRefApi = new GeoRefApi();
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.setNombre("Buenos+Aires");
    Assertions.assertEquals(135, geoRefApi.getMunicipiosByNombreDeProvincia(ubicacion).size());
  }

  @Test
  public void podriaTraerElListadoDeDepartamentosPorNombreDeProvincia() {
    GeoRefApi geoRefApi = new GeoRefApi();
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.setNombre("Buenos+Aires");
    Assertions.assertEquals(150, geoRefApi.getDepartamentosByNombreDeProvincia(ubicacion).size());
  }

  @Test
  public void podriaTraerElListadoDeProvincias() {
    GeoRefApi geoRefApi = new GeoRefApi();
    Assertions.assertEquals(24, geoRefApi.getProvincias().size());
  }

  @Test
  public void puedoSaberElDepartamentoSiQuisieraMedianteLatitudYLongitud() {
    GeoRefApi geoRefApi = new GeoRefApi();
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.setLatitud(-27.2741);
    ubicacion.setLongitud(-66.7529);
    Assertions.assertEquals("Belén", geoRefApi.getDepartamentoByLatitudAndLongitud(ubicacion).getNombre());
  }

  @Test
  public void puedoSaberElMunicipioSiQuisieraMedianteLatitudYLongitud() {
    GeoRefApi geoRefApi = new GeoRefApi();
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.setLatitud(-27.2741);
    ubicacion.setLongitud(-66.7529);
    Assertions.assertEquals("Hualfín", geoRefApi.getMunicipioByLatitudAndLongitud(ubicacion).getNombre());
  }

  @Test
  public void puedoSaberLaProvinciaSiQuisieraMedianteLatitudYLongitud() {
    GeoRefApi geoRefApi = new GeoRefApi();
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.setLatitud(-27.2741);
    ubicacion.setLongitud(-66.7529);
    Assertions.assertEquals("Catamarca", geoRefApi.getProvinciaByLatitudAndLongitud(ubicacion).getNombre());
  }

  @Test
  public void testEspacio() throws URISyntaxException, IOException, InterruptedException {
    GeoRefApi geoRefApi = new GeoRefApi();
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.setNombre("Buenos+Aires");
    Assertions.assertEquals("Buenos Aires", geoRefApi.getProvinciaByNombre(ubicacion).getNombre());
  }
}
