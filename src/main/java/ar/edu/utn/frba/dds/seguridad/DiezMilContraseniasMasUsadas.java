package ar.edu.utn.frba.dds.seguridad;

import ar.edu.utn.frba.dds.exceptions.ArchivoNoEncontradoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiezMilContraseniasMasUsadas implements Validador {
  @Override
  public Boolean validarContrasenia(String contrasenia) {


    Thread currentThread = Thread.currentThread();
    ClassLoader contextClassLoader = currentThread.getContextClassLoader();
    URL resource = contextClassLoader.getResource("Top10000Password");
    String path = resource.getPath();
    File file = new File(path);
    try {
      Scanner sc = new Scanner(file, "UTF-8");
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        if (line.equals(contrasenia)) {
          return false;
        }
      }
    } catch (FileNotFoundException e) {
      throw new ArchivoNoEncontradoException("El archivo de las"
          + " peores contrasenias no pudo ser encontrado en el directorio");
    }
    return true;
  }
}
