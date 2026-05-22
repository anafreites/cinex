/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinex;

import java.time.LocalDate;

/**
 *
 * Elaborado por Anabella Freites y Jose Quintero
 */
public class Funcion {
    protected int codigoFuncion;
    protected String tituloPelicula;
    protected int duracion;
    protected double costoBase;
    protected double precioBoleto;
    protected int asientosDisponibles;
    protected int asientosReservados;
    protected LocalDate fecha;
    protected String hora;
    protected int numeroSala;
    protected int estado;

    Validacion validar = new Validacion();
    
    public Funcion() {
        this.codigoFuncion = 10000;
        this.tituloPelicula = "";
        this.duracion = 0;
        this.costoBase = 0;
        this.precioBoleto = 0;
        this.asientosDisponibles = 100;
        this.asientosReservados = 0;
        this.hora = "";
        this.numeroSala = 0;
        this.estado = 1;
    }
    
    public Funcion(int codigoFuncion, String tituloPelicula, int duracion, double costoBase, double precioBoleto, int asientosDisponibles, int asientosReservados, LocalDate fecha, String hora, int numeroSala, int estado) {
        this.codigoFuncion = codigoFuncion;
        this.tituloPelicula = tituloPelicula;
        this.duracion = duracion;
        this.costoBase = costoBase;
        this.precioBoleto = precioBoleto;
        this.asientosDisponibles = asientosDisponibles;
        this.asientosReservados = asientosReservados;
        this.fecha = fecha;
        this.hora = hora;
        this.numeroSala = numeroSala;
        this.estado = estado;
    }

    public int getCodigoFuncion() {
        return codigoFuncion;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public int getDuracion() {
        return duracion;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public double getPrecioBoleto() {
        return precioBoleto;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public int getAsientosReservados() {
        return asientosReservados;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public int getEstado() {
        return estado;
    }
    
    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public void setAsientosReservados(int asientosReservados) {
        this.asientosReservados = asientosReservados;
    }
    
    public void leerDatos(){
        this.codigoFuncion = validar.validarEntero("el codigo de la funcion: ", "el codigo de la funcion es invalido,", 10000, 99999);
        this.tituloPelicula = validar.validarSoloLetras("titulo de la pelicula", "tener solo letras");
        this.duracion = validar.validarEntero("duracion de la pelicula en minutos: ", "la duracion de la pelicula es invalida,", 60 , 240);
        this.costoBase = validar.validarDecimal("el costo base de la funcion: ", "costo base de la pelicula es incorrecto,", 5, 10 );
        this.precioBoleto = validar.validarPrecioBoleto(costoBase);
        this.asientosReservados = validar.validarEntero("la cantidad de asientos reservados: ", "cantidad de asientos reservados invalida,", 0, 100);
        this.asientosDisponibles = 100 - this.asientosReservados;
        this.fecha = validar.validarFecha("Indique la fecha de la funcion(dd/mm/yyyy): ", "fecha invalida. Intentelo nuevamente" );
        this.hora = validar.validarHora("Indique la hora de la funcion: ", "Error la hora ingresada es invalida");
        this.numeroSala = validar.validarEntero("el numero de sala: ", "el numero de sala", 1, 20);
        this.estado = validar.validarEntero("el estado de la funcion (0.Cancelada 1.Programada 2.En ejecucion): ", "numero de estado invalido. Intente nuevamente", 0, 2);
    }
    
    public double calcularIngreso(){
        int boletosVendidos = getAsientosReservados();
        double precioUnitario = getPrecioBoleto();
        
        return boletosVendidos*precioUnitario;
    }
    
    public double calcularIngreso(double descuento){
        int boletosVendidos = getAsientosReservados();
        double precioUnitario = getPrecioBoleto();

        return (boletosVendidos * precioUnitario) * descuento;
    }
    
    public void notificarDisponibilidad(){
        int cantidadAsientosDisponibles = getAsientosDisponibles();
        
        if (cantidadAsientosDisponibles <= 5){
            System.out.println("Solo quedan "+ cantidadAsientosDisponibles + ". Se recomienda activar una promocion para vender los asientos restantes.");
        }
    }
    
    public void actualizarEstado(){
        LocalDate fechaFuncion = validar.validarFecha("Indique la fecha de la funcion(dd/mm/yyyy): ", "fecha invalida. Intentelo nuevamente" );

        if(fechaFuncion.isAfter(this.fecha)){
            System.out.println("La fecha de la funcion ha sido actualizada. Fecha nueva: " + fechaFuncion);
            this.fecha = fechaFuncion;
        }

        int estadoFuncion = validar.validarEntero("el estado de la funcion actualmente(0.Cancelada 1.Programada 2.En ejecucion): ", "numero de estado invalido. Intente nuevamente", 0, 2);

        switch(estadoFuncion){
            case 0 -> {
            System.out.println("La funcion ha sido cancelada");
            this.estado = 0;
            }

            case 1 -> {
                System.out.println("La funcion esta programada");
                this.estado = 1;
            }

            case 2 -> {
                System.out.println("La funcion se encuentra en ejecucion");
                this.estado = 2;
            }
        }
    }
    
    public void mostrarInformacion(){
        int codigo = getCodigoFuncion();
        String titulo = getTituloPelicula();
        int duracionPelicula = getDuracion();
        double costo = getCostoBase();
        double precio = getPrecioBoleto();
        int aDisponibles = getAsientosDisponibles();
        int aReservados = getAsientosReservados();
        LocalDate fechaFuncion = getFecha();
        String horaFuncion = getHora();
        int sala = getNumeroSala();
        int estadoFuncion = getEstado();
        
        System.out.println("\n===INFORMACION DE LA FUNCION: ===");
        System.out.println("Codigo de la funcion: " + getCodigoFuncion());
        System.out.println("Titulo de la pelicula: "+ titulo);
        System.out.println("Duracion de la pelicula: " + duracionPelicula);
        System.out.println("Costo base de la funcion: " + costo);
        System.out.println("Precio del boleto: " + precio);
        System.out.println("Asientos disponibles: " + aDisponibles);
        System.out.println("Asientos reservados: " + aReservados);
        System.out.println("Fecha de la funcion: " + fechaFuncion);
        System.out.println("Hora de la funcion: " + horaFuncion);
        System.out.println("Numero de sala: " + sala);
        System.out.println("Estado (0: Cancelada, 1: Programada, 2: En ejecucion: " + estadoFuncion);
    }
    
    public Funcion seleccionarFuncion(Funcion f1, Funcion f2, Funcion f3, int num){
        switch (num) {
            case 1 -> {
                if(f1.getCodigoFuncion() == 10000 && f1.getTituloPelicula().isEmpty()){
                    System.out.println("\t\nERROR: La funcion normal no ha sido agregada aun. Use la opcion 1 primero.");
                    return null;
                }
                else{
                    System.out.println("\t\nFuncion Normal seleccionada exitosamente.");
                    return f1;
                }                
            }
            case 2 -> {
                if(f2.getCodigoFuncion() == 10000 && f2.getTituloPelicula().isEmpty()){
                    System.out.println("\t\nERROR: La funcion 3D no ha sido agregada aun. Use la opcion 1 primero.");
                    return null;
                }
                else{
                    System.out.println("\t\nFuncion 3D seleccionada exitosamente.");
                    return f2;
                } 
            }
            case 3 -> {
                if(f3.getCodigoFuncion() == 10000 && f3.getTituloPelicula().isEmpty()){
                    System.out.println("\t\nERROR: La funcion VIP no ha sido agregada aun. Use la opcion 1 primero.");
                    return null;
                }
                else{
                    System.out.println("\t\nFuncion VIP seleccionada exitosamente.");
                    return f3;
                } 
            }
            default -> {
                System.err.println("\t\nERROR: Selecciono un numero invalido. Indique 1 para Funcion Normal, 2 para Funcion 3D o 3 para Funcion VIP.");
                return null;
            }
        }
    }
}