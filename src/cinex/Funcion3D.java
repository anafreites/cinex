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
public class Funcion3D extends Funcion {
    private double costoGafas;
    
    //constructor
    public Funcion3D(int codigoFuncion, String tituloPelicula, int duracion, double costoBase, double precioBoleto, int asientosDisponibles, int asientosReservados, LocalDate fecha, String hora, int numeroSala, int estado, double costoGafas){
        super(codigoFuncion, tituloPelicula, duracion, costoBase, precioBoleto, asientosDisponibles, asientosReservados, fecha, hora, numeroSala, estado);
        this.costoGafas = 0;
    }
    
    
    //metodos
    @Override
    public void leerDatos(){
        this.codigoFuncion = validar.validarEntero("el codigo de la funcion: ", "el codigo de la funcion", 10000, 99999);
        this.tituloPelicula = validar.validarSoloLetras("titulo de la pelicula", "tener solo letras");
        this.duracion = validar.validarEntero("duracion de la pelicula en minutos: ", "la duracion", 60 , 240);
        this.costoBase = validar.validarDecimal("el costo base de la funcion: ", "costo base de la pelicula", 5, 10 );
        this.precioBoleto = validar.validarPrecioBoleto(costoBase);
        this.asientosReservados = validar.validarEntero("la cantidad de asientos reservados: ", "cantidad de asientos reservados invalida", 0, 100);
        this.asientosDisponibles = 100 - this.asientosReservados;
        this.fecha = validar.validarFecha("Indique la fecha de la funcion(dd/mm/yyyy): ", "fecha invalida. Intentelo nuevamente" );
        this.hora = validar.validarHora("Indique la hora de la funcion: ", "Error la hora ingresada es invalida");
        this.numeroSala = validar.validarEntero("el numero de sala: ", "el numero de sala", 1, 20);
        this.costoGafas = validar.validarDecimal("el precio de las gafas 3D: ", "el precio indicado", 1, 2.5);
    }
    
    
}   

