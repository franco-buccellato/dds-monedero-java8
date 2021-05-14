package dds.monedero.model;

import java.time.LocalDate;

public interface Movimiento {

  //En ningún lenguaje de programación usen jamás doubles para modelar dinero en el mundo real
  //siempre usen numeros de precision arbitraria, como BigDecimal en Java y similares
  //private LocalDate fecha;
  //private double monto;
  //private boolean esDeposito;

  //No debería poner como boolean "esDeposito"
  //Debería crear la interface "Movimeinto"
  //Crear las clases "Deposito" y "Extraccion" que implementan "Movimiento"
  /*
  public Movimiento(LocalDate fecha, double monto, boolean esDeposito) {
    this.fecha = fecha;
    this.monto = monto;
    this.esDeposito = esDeposito;

  }
*/
  public double getMonto();

  public LocalDate getFecha();

  public void agregateA(Cuenta cuenta);

  public double calcularValor(Cuenta cuenta);

  public boolean seRealizoTransaccion(LocalDate fecha);

/*
  public boolean fueDepositado(LocalDate fecha) {
    return isDeposito() && esDeLaFecha(fecha);
  }

  public boolean fueExtraido(LocalDate fecha) {
    return isExtraccion() && esDeLaFecha(fecha);
  }
*/

/*
  public boolean isDeposito() {
    return esDeposito;
  }

  public boolean isExtraccion() {
    return !esDeposito;
  }
*/

//¿Porque le pregunto que es?"Missing polymorphism"
/*
  public double calcularValor(Cuenta cuenta) {
    if (esDeposito) {
      return cuenta.getSaldo() + getMonto();
    } else {
      return cuenta.getSaldo() - getMonto();
    }
  }
*/
}
