package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

  private double saldo;
  private double limiteExtraccion = 1000;
  //private List<Movimiento> movimientos = new ArrayList<>();
  private List<Movimiento> depositos = new ArrayList<>();
  private List<Movimiento> extracciones = new ArrayList<>();

  //¿Porque crear 2 inicializadores?
//Si quiero que el monto inicial sea 0 se lo paso por parámetro
/*public Cuenta() {
    saldo = 0;
  }
*/
  public Cuenta() {
    saldo = 0;
  }

  /*
  public void setMovimientos(List<Movimiento> movimientos) {
    this.movimientos = movimientos;
  }
  */
  //Nombre mas declarativo al method: poner -> depositar
  //Nombre mas declarativo a la variable: cuanto -> montoDeposito
  public void depositar(double montoDeposito) {
    LocalDate fechaHoy = LocalDate.now();
    if (montoDeposito <= 0) {
      throw new MontoNegativoException(montoDeposito + ": el monto a ingresar debe ser un valor positivo");
    }

    if(depositos.stream().filter(depositos -> depositos.getFecha().equals(fechaHoy)).count() >= 3){
      throw new MaximaCantidadDepositosException("Ya excedio los " + 3 + " depositos diarios");
    }

    /*
    if (getMovimientos().stream().filter(movimiento -> movimiento.isDeposito()).count() >= 3) {
      throw new MaximaCantidadDepositosException("Ya excedio los " + 3 + " depositos diarios");
    }
    */
    agregarDeposito(new Deposito(LocalDate.now(), montoDeposito));
  }

  //Nombre mas declarativo al method: sacar -> extraer
  //Nombre mas declarativo a la variable: cuanto -> montoExtraccion
  public void extraer(double montoExtraccion) {
    if (montoExtraccion <= 0) {
      throw new MontoNegativoException(montoExtraccion + ": el monto a ingresar debe ser un valor positivo");
    }
    if (saldo - montoExtraccion < 0) {
      throw new SaldoMenorException("No puede sacar mas de " + saldo + " $");
    }
    double montoExtraidoHoy = getMontoExtraidoA(LocalDate.now());
    double limite = limiteExtraccion - montoExtraidoHoy;
    if (montoExtraccion > limite) {
      throw new MaximoExtraccionDiarioException("No puede extraer mas de $ " + limiteExtraccion
          + " diarios, límite: " + limite);
    }
    agregarExtraccion(new Extraccion(LocalDate.now(), montoExtraccion));
  }
/*
  public void agregarMovimiento(LocalDate fecha, double cuanto) {
    Movimiento movimiento = new Movimiento(fecha, cuanto);
    movimientos.add(movimiento);
  }
*/
  public void agregarDeposito(Movimiento deposito){
    depositos.add(deposito);
  }
  public void agregarExtraccion(Movimiento extraccion){
    extracciones.add(extraccion);
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return extracciones.stream()
        .filter(extracciones -> extracciones.getFecha().equals(fecha))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }

  public List<Movimiento> getDepositos() {
    return depositos;
  }

  public List<Movimiento> getExtracciones() {
    return extracciones;
  }

  public Double getSaldo() {
    return saldo;
  }

  public void setSaldo(Double nuevoSaldo) {
    saldo = nuevoSaldo;
  }

}
