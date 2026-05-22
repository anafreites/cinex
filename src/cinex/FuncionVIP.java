/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinex;

import java.time.LocalDate;

/**
 *
 * @author LAB_L121
 */
public class FuncionVIP extends Funcion {
    private double recargoServicio;
    
    public FuncionVIP(int codigoFuncion, String tituloPelicula, int duracion, double costoBase, double precioBoleto, int asientosDisponibles, int asientosReservados, LocalDate fecha, String hora, int numeroSala, int estado, double recargoServicio){
        super(codigoFuncion, tituloPelicula, duracion, costoBase, precioBoleto, asientosDisponibles, asientosReservados, fecha, hora, numeroSala, estado);
        this.recargoServicio = recargoServicio;
    }

    public double getRecargoServicio() {
        return recargoServicio;
    }
    
    @Override
    public void leerDatos(){
        this.codigoFuncion = validar.validarEntero("el codigo de la funcion: ", "el codigo de la funcion", 10000, 99999);
        this.tituloPelicula = validar.validarSoloLetras("titulo de la pelicula", "tener solo letras");
        this.duracion = validar.validarEntero("duracion de la pelicula en minutos: ", "la duracion", 60 , 240);
        this.costoBase = validar.validarDecimal("el costo base de la funcion: ", "costo base de la pelicula", 5, 10 );
        this.recargoServicio = validar.validarDecimal("el porcentaje de recargo por servicio VIP (como un numero entero): ", "el porcentaje del recargo ", 0, 100);
        this.precioBoleto = validar.validarPrecioBoleto(costoBase)*(getRecargoServicio()/100);
        this.asientosReservados = validar.validarEntero("la cantidad de asientos reservados: ", "cantidad de asientos reservados invalida", 0, 100);
        this.asientosDisponibles = 100 - this.asientosReservados;
        this.fecha = validar.validarFecha("Indique la fecha de la funcion(dd/mm/yyyy): ", "fecha invalida. Intentelo nuevamente" );
        this.hora = validar.validarHora("Indique la hora de la funcion: ", "Error la hora ingresada es invalida");
        this.numeroSala = validar.validarEntero("el numero de sala: ", "el numero de sala", 1, 20);
        this.estado = validar.validarEntero("el estado de la funcion (0.Cancelada 1.Programada 2.En ejecucion): ", "numero de estado invalido. Intente nuevamente", 0, 2);
    }
    
    @Override
    public void mostrarInformacion(){
        System.out.println("\n===INFORMACION DE LA FUNCION: ===");
        System.out.println("Codigo de la funcion: " + getCodigoFuncion());
        System.out.println("Titulo de la pelicula: "+ getTituloPelicula());
        System.out.println("Duracion de la pelicula: " + getDuracion());
        System.out.println("Costo base de la funcion: " + getCostoBase());
        System.out.println("Recargo por servicios VIP: " + getRecargoServicio());
        System.out.println("Precio del boleto: " + getPrecioBoleto()*getRecargoServicio());
        System.out.println("Asientos disponibles: " + getAsientosDisponibles());
        System.out.println("Asientos reservados: " + getAsientosReservados());
        System.out.println("Fecha de la funcion: " + getFecha());
        System.out.println("Hora de la funcion: " + getHora());
        System.out.println("Numero de sala: " + getNumeroSala());
        System.out.println("Estado (0: Cancelada, 1: Programada, 2: En ejecucion: " + getEstado());
    }
    
    @Override
    public double calcularIngreso(){
         int boletosVendidos = getAsientosReservados();
         double precioUnitario = getPrecioBoleto();

         return (boletosVendidos*precioUnitario)*(1+recargoServicio/100);
     }
    
    @Override
     public double calcularIngreso(double descuento){
         int boletosVendidos = getAsientosReservados();
         double precioUnitario = getPrecioBoleto();

         return (boletosVendidos*precioUnitario)*(1+recargoServicio/100) * descuento;
     } 
}

