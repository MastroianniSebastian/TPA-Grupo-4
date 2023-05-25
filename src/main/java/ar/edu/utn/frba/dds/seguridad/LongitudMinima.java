package ar.edu.utn.frba.dds.seguridad;

public class LongitudMinima implements Validador {

  private Integer longitudMinima = 8;

  @Override
  public Boolean validarContrasenia(String contrasenia) {
    return contrasenia.length() >= longitudMinima;
  }
}
