package ar.edu.utn.frba.dds.seguridad;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDeContrasenia {

  List<Validador> validaciones = new ArrayList<>();


  public Boolean validar(String contrasenia) {
    return this.validaciones.stream().allMatch(unaValidacion ->
        unaValidacion.validarContrasenia(contrasenia));
  }

  public void agregarValidacion(Validador unaValidacion) {
    validaciones.add(unaValidacion);
    return;
  }
}
