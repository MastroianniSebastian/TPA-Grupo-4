package ar.edu.utn.frba.dds.modelo.georefapi;

import ar.edu.utn.frba.dds.modelo.ServicioDeGeoReferencia;
import ar.edu.utn.frba.dds.modelo.Ubicacion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeoRefApi implements ServicioDeGeoReferencia {
  //Sacar del archivo de config el valor de la URI
  private static final String URI = "https://apis.datos.gob.ar/georef/api/";

  @Override
  public Ubicacion getProvinciaByNombre(Ubicacion ubicacion) {
    ProvinciaWrapper provinciaWrapper = this.getProvinciasByNombre(ubicacion.getNombre()).get(0);
    ubicacion.setNombre(provinciaWrapper.getNombre());
    ubicacion.setId(provinciaWrapper.getId());
    ubicacion.setLatitud(provinciaWrapper.getCentroide().getLat());
    ubicacion.setLongitud(provinciaWrapper.getCentroide().getLon());
    return ubicacion;
  }

  @Override
  public List<Ubicacion> getMunicipiosByNombreDeProvincia(Ubicacion ubicacion) {
    List<MunicipioWrappper> list = this.getMunicipiosApiByNombreDeProvincia(
        ubicacion.getNombre());
    return list.stream().map(unMunicipio -> this.municipioWrapperToUbicacion(unMunicipio))
        .collect(Collectors.toList());
  }

  @Override
  public List<Ubicacion> getDepartamentosByNombreDeProvincia(Ubicacion ubicacion) {
    List<DepartamentoWrapper> list = this.getDeparamentosApiByNombreDeProvincia(
        ubicacion.getNombre());
    return list.stream().map(unaProvincia -> this.departamentoWrapperToUbicacion(unaProvincia))
        .collect(Collectors.toList());
  }

  @Override
  public List<Ubicacion> getProvincias() {
    List<ProvinciaWrapper> provinciaWrappers = this.getProvinciasAll();
    List<Ubicacion> response = new ArrayList<>();
    provinciaWrappers.stream().forEach(unaProvincia -> response.add(
        this.provinciaWrapperToUbicacion(unaProvincia)));
    return response;
  }

  @Override
  public Ubicacion getDepartamentoByLatitudAndLongitud(Ubicacion ubicacion) {
    UbicacionWrapper ubicacionWrapper = this.getUbicacionByLatAndLong(
        ubicacion.getLatitud(), ubicacion.getLongitud());
    ubicacion.setNombre(ubicacionWrapper.getDepartamento().getNombre());
    ubicacion.setId(ubicacion.getId());
    ubicacion.setLatitud(ubicacionWrapper.getLat());
    ubicacion.setLongitud(ubicacionWrapper.getLon());
    return ubicacion;
  }

  @Override
  public Ubicacion getMunicipioByLatitudAndLongitud(Ubicacion ubicacion) {
    UbicacionWrapper ubicacionWrapper = this.getUbicacionByLatAndLong(
        ubicacion.getLatitud(), ubicacion.getLongitud());
    ubicacion.setNombre(ubicacionWrapper.getMunicipio().getNombre());
    ubicacion.setId(Integer.parseInt(ubicacionWrapper.getMunicipio().getId()));
    ubicacion.setLatitud(ubicacionWrapper.getLat());
    ubicacion.setLongitud(ubicacionWrapper.getLon());
    return ubicacion;
  }

  @Override
  public Ubicacion getProvinciaByLatitudAndLongitud(Ubicacion ubicacion) {
    UbicacionWrapper ubicacionWrapper = this.getUbicacionByLatAndLong(
        ubicacion.getLatitud(), ubicacion.getLongitud());
    ubicacion.setNombre(ubicacionWrapper.getProvincia().getNombre());
    ubicacion.setId(ubicacionWrapper.getProvincia().getId());
    ubicacion.setLatitud(ubicacionWrapper.getLat());
    ubicacion.setLongitud(ubicacionWrapper.getLon());
    return ubicacion;
  }

  private Ubicacion provinciaWrapperToUbicacion(ProvinciaWrapper unaProvincia) {
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.setNombre(unaProvincia.getNombre());
    ubicacion.setId(unaProvincia.getId());
    ubicacion.setLatitud(unaProvincia.getCentroide().getLat());
    return ubicacion;
  }

  private Ubicacion departamentoWrapperToUbicacion(DepartamentoWrapper unDepartamento) {
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.setId(Integer.parseInt(unDepartamento.getId()));
    ubicacion.setNombre(unDepartamento.getNombre());
    ubicacion.setLongitud(unDepartamento.getCentroide().getLon());
    ubicacion.setLatitud(unDepartamento.getCentroide().getLat());
    return ubicacion;
  }

  private Ubicacion municipioWrapperToUbicacion(MunicipioWrappper unMunicipio) {
    Ubicacion ubicacion = new Ubicacion();
    ubicacion.setId(Integer.parseInt(unMunicipio.getId()));
    ubicacion.setNombre(unMunicipio.getNombre());
    ubicacion.setLongitud(unMunicipio.getCentroide().getLon());
    ubicacion.setLatitud(unMunicipio.getCentroide().getLat());
    return ubicacion;
  }


  private List<ProvinciaWrapper> getProvinciasByNombre(String nombre) {

    String uri = URI.concat("provincias?nombre=" + nombre);
    return this.getProvinciaByParameter(uri);
  }

  private List<ProvinciaWrapper> getById(Integer id) {

    String uri = URI.concat("provincias?id=" + id);
    return this.getProvinciaByParameter(uri);
  }

  private List<DepartamentoWrapper> getDeparamentosApiByNombreDeProvincia(String nombre) {

    String uri = URI.concat("departamentos?provincia=" + nombre + "&max=1000");
    //TODO traer todos de manera correcta y no con el max=1000
    HttpRequest request = null;
    try {
      request = HttpRequest.newBuilder().uri(new URI(uri)).GET().build();
    } catch (URISyntaxException e) {
      throw new GeoRefApiException("Error en URI", e);
    }
    HttpResponse<String> response = null;
    try {
      response = HttpClient.newBuilder().proxy(ProxySelector.getDefault())
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      throw new GeoRefApiException("Error al llamar servcio", e);
    } catch (InterruptedException e) {
      throw new GeoRefApiException("Error al llamar servcio", e);
    }
    String body = response.body();
    ObjectMapper objectMapper = new ObjectMapper();
    CommonDepartamentoWrapper geoRefApiMolde = null;
    try {
      geoRefApiMolde = objectMapper.readValue(body, CommonDepartamentoWrapper.class);
    } catch (JsonProcessingException e) {
      throw new GeoRefApiException("Error al castear Json", e);
    }
    List<DepartamentoWrapper> list = geoRefApiMolde.getDepartamentos();
    return list;
  }

  private List<ProvinciaWrapper> getProvinciasAll() {

    String uri = URI.concat("provincias");
    return this.getProvinciaByParameter(uri);
  }

  private List<ProvinciaWrapper> getProvinciaByParameter(String uri) {

    HttpRequest request = null;
    try {
      request = HttpRequest.newBuilder().uri(new URI(uri)).GET().build();
    } catch (URISyntaxException e) {
      throw new GeoRefApiException("Error en URI", e);
    }
    HttpResponse<String> response = null;
    try {
      response = HttpClient.newBuilder().proxy(ProxySelector.getDefault())
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      throw new GeoRefApiException("Error al llamar servcio", e);
    } catch (InterruptedException e) {
      throw new GeoRefApiException("Error al llamar servcio", e);
    }
    String body = response.body();
    ObjectMapper objectMapper = new ObjectMapper();
    CommonProvinciaWrapper geoRefApiMolde = null;
    try {
      geoRefApiMolde = objectMapper.readValue(body, CommonProvinciaWrapper.class);
    } catch (JsonProcessingException e) {
      throw new GeoRefApiException("Error al castear Json", e);
    }
    List<ProvinciaWrapper> list = geoRefApiMolde.getProvincias();
    return list;
  }


  private UbicacionWrapper getUbicacionByLatAndLong(Double latitud, Double longitud) {

    String uri = URI.concat("ubicacion?lat=" + latitud.toString() + "&lon=" + longitud.toString());
    HttpRequest request = null;
    try {
      request = HttpRequest.newBuilder().uri(new URI(uri)).GET().build();
    } catch (URISyntaxException e) {
      throw new GeoRefApiException("Error en URI", e);
    }
    HttpResponse<String> response = null;
    try {
      response = HttpClient.newBuilder().proxy(ProxySelector.getDefault())
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      throw new GeoRefApiException("Error al llamar servcio", e);
    } catch (InterruptedException e) {
      throw new GeoRefApiException("Error al llamar servcio", e);
    }
    String body = response.body();
    ObjectMapper objectMapper = new ObjectMapper();
    UbicacionResponseWrapper geoRefApiMolde = null;
    try {
      geoRefApiMolde = objectMapper.readValue(body, UbicacionResponseWrapper.class);
    } catch (JsonProcessingException e) {
      throw new GeoRefApiException("Error al castear Json", e);
    }
    UbicacionWrapper ubicacionWrapper = geoRefApiMolde.getUbicacion();
    return ubicacionWrapper;
  }


  private List<MunicipioWrappper> getMunicipiosApiByNombreDeProvincia(String nombre) {

    String uri = URI.concat("municipios?provincia=" + nombre + "&max=1000");
    //TODO traer todos de manera correcta y no con el max=1000
    HttpRequest request = null;
    try {
      request = HttpRequest.newBuilder().uri(new URI(uri)).GET().build();
    } catch (URISyntaxException e) {
      throw new GeoRefApiException("Error en URI", e);
    }
    HttpResponse<String> response = null;
    try {
      response = HttpClient.newBuilder().proxy(ProxySelector.getDefault())
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      throw new GeoRefApiException("Error al llamar servcio", e);
    } catch (InterruptedException e) {
      throw new GeoRefApiException("Error al llamar servcio", e);
    }
    String body = response.body();
    ObjectMapper objectMapper = new ObjectMapper();
    CommonMunicipioWrapper geoRefApiMolde = null;
    try {
      geoRefApiMolde = objectMapper.readValue(body, CommonMunicipioWrapper.class);
    } catch (JsonProcessingException e) {
      throw new GeoRefApiException("Error al castear Json", e);
    }
    List<MunicipioWrappper> list = geoRefApiMolde.getMunicipios();
    return list;
  }

}
