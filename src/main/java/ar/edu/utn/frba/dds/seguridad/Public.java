package ar.edu.utn.frba.dds.seguridad;

import ar.edu.utn.frba.dds.exceptions.ArchivoNoEncontradoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Public {

  private String passWordCorrecta;
  private Integer intentosFallidos = 0;

  public String loggin(String pass) {

    if (pass.equals(passWordCorrecta)) {
      this.resetIntentosFallidos();
      return "Contraseña correcta";
    }
    if (intentosFallidos >= 3) {
      return "Usted ha intentado loggearse demasiadas veces";
    }
    intentosFallidos++;
    return "Contraseña incorrecta";
  }

  private void resetIntentosFallidos() {
    this.intentosFallidos = 0;
  }

  private void setPassWordCorrecta(String pass) {
    this.passWordCorrecta = pass;
    return;
  }

  public Public() {
    Thread currentThread = Thread.currentThread();
    ClassLoader contextClassLoader = currentThread.getContextClassLoader();
    URL resource = contextClassLoader.getResource("application.properties");
    String path = resource.getPath();
    File file = new File(path);
    Properties properties = new Properties();
    try {
      FileInputStream fileInputStream = new FileInputStream(file);
      properties.load(fileInputStream);
      fileInputStream.close();
      this.setPassWordCorrecta((String) properties.get("passwordDePrueba"));
    } catch (IOException e) {
      throw new ArchivoNoEncontradoException("El archivo de propiedades"
          + " no fue encontrado en el directorio");
    }
  }
}
