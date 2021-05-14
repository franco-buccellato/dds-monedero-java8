package dds.monedero.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Deposito implements Movimiento {
  private LocalDate fecha;
  //En ningún lenguaje de programación usen jamás doubles para modelar dinero en el mundo real
  //siempre usen numeros de precision arbitraria, como BigDecimal en Java y similares
  private double monto;
  //private boolean esDeposito;

  //No debería poner como boolean "esDeposito"
  //Debería crear la interface "Movimeinto"
  //Crear las clases "Deposito" y "Extraccion" que implementan "Movimiento"
  public Deposito(LocalDate unaFecha, double unMonto) {
    fecha = unaFecha;
    monto = unMonto;
  }

  public double getMonto() {
    return monto;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  @Override
  public void agregateA(Cuenta cuenta) {

  }

  public boolean seRealizoTransaccion(LocalDate unaFecha) {
    return fecha.equals(unaFecha);
  }

  /*
  public boolean fueDepositado(LocalDate fecha) {
    return esDeLaFecha(fecha);
  }

  public boolean esDeLaFecha(LocalDate fecha) {
    return this.fecha.equals(fecha);
  }

  public boolean isDeposito() {
    return esDeposito;
  }

  public boolean isExtraccion() {
    return !esDeposito;
  }
*/
  /*
  public void agregateA(Cuenta cuenta) {
    cuenta.setSaldo(calcularValor(cuenta));
    cuenta.agregarMovimiento(fecha, monto);
  }
   */

  //¿Porque le pregunto que es?"Missing polymorphism"
  public double calcularValor(Cuenta cuenta) {
    return cuenta.getSaldo() + monto;
  }
}
