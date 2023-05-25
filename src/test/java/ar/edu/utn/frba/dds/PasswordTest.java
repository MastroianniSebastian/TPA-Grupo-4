package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.seguridad.DiezMilContraseniasMasUsadas;
import ar.edu.utn.frba.dds.seguridad.LongitudMinima;
import ar.edu.utn.frba.dds.seguridad.Public;
import ar.edu.utn.frba.dds.seguridad.ValidadorDeContrasenia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordTest {

  static DiezMilContraseniasMasUsadas diezMilContraseniasMasUsadas = new DiezMilContraseniasMasUsadas();
  static ValidadorDeContrasenia validadorDeContrasenia = new ValidadorDeContrasenia();
  static LongitudMinima longitudMinima = new LongitudMinima();

  Public publicClass = new Public();

  @BeforeAll
  static void initAll(){

  validadorDeContrasenia.agregarValidacion(diezMilContraseniasMasUsadas);
  validadorDeContrasenia.agregarValidacion(longitudMinima);
  }


  @Test
  public void contraseniaValida() {

    assertEquals(true, validadorDeContrasenia.validar("ksdfndsflsk"));

  }

  @Test
  public void contraseniaInvalidaPorSerComun() {

    assertEquals(false, validadorDeContrasenia.validar("123456"));

  }

  @Test
  public void contraseniaInvalidaPorLongitudNoCumpleMinimo() {

    assertEquals(false, validadorDeContrasenia.validar("adsf"));

  }

  @Test
  public void contraseniaValidaPorLongitudCumpleMinimo() {

    assertEquals(true, validadorDeContrasenia.validar("adsfdas3"));

  }

  @Test
  public void elLogginNoPuedeTenerMasDeTresIntentosFallidos() {

    assertEquals("Contraseña incorrecta", publicClass.loggin("adsfdaas3"));
    assertEquals("Contraseña incorrecta", publicClass.loggin("adads3"));
    assertEquals("Contraseña incorrecta", publicClass.loggin("adas3"));
    assertEquals("Usted ha intentado loggearse demasiadas veces", publicClass.loggin("adas3"));

  }

  @Test
  public void elLogginEsCorrecto() {
    assertEquals("Contraseña incorrecta", publicClass.loggin("adsfdaas3"));
    assertEquals("Contraseña correcta", publicClass.loggin("comparacion"));

  }

  @Test
  public void siPongoContraseniaCorrectaSeReseteaLosIntentos() {
    assertEquals("Contraseña incorrecta", publicClass.loggin("adsfdaas3"));
    assertEquals("Contraseña correcta", publicClass.loggin("comparacion"));
    assertEquals("Contraseña incorrecta", publicClass.loggin("adsfdaas3"));
    assertEquals("Contraseña incorrecta", publicClass.loggin("adsfdaas3"));
    assertEquals("Contraseña correcta", publicClass.loggin("comparacion"));
  }


}
