package ar.edu.utn.frba.dds.exceptions;

public class ArchivoNoEncontradoException extends RuntimeException {

  public ArchivoNoEncontradoException(String descripcion) {
    super(descripcion);
  }
}
