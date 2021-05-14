package dds.monedero.model;

import java.time.LocalDate;

public class Extraccion implements Movimiento{
  private LocalDate fecha;
  private double monto;

  public Extraccion(LocalDate unaFecha, double unMonto) {
    fecha = unaFecha;
    monto = unMonto;
  }

  public double getMonto() {
    return monto;
  }

  public LocalDate getFecha() { return fecha; }

  @Override
  public void agregateA(Cuenta cuenta) {

  }

  public boolean seRealizoTransaccion(LocalDate unaFecha){
      return fecha.equals(unaFecha);
  }
/*
  public void agregateA(Cuenta cuenta) {
    cuenta.setSaldo(calcularValor(cuenta));
    cuenta.agregarMovimiento(fecha, monto);
  }
 */
//¿Porque le pregunto que es?"Missing polymorphism"
  public double calcularValor(Cuenta cuenta) {
      return cuenta.getSaldo() - monto;
    }
}
