package ar.edu.utn.frba.dds.seguridad;

import ar.edu.utn.frba.dds.exceptions.ArchivoNoEncontradoException;

public interface Validador {

  public Boolean validarContrasenia(String contrasenia) throws ArchivoNoEncontradoException;
}
