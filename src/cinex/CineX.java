/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cinex;
import java.time.LocalDate;
import java.util.Scanner;



/**
 *
 * Elaborado por Anabella Freites y Jose Quintero
 */


public class CineX {
    
    public static void main(String[] args) {
        Validacion validar = new Validacion();
        int opt = -1;
        int numFuncion = -1;
        LocalDate fecha = LocalDate.now();
        Scanner datos = new Scanner(System.in);
        Funcion funcion1 = new Funcion();
        Funcion funcion2 = new Funcion3D(10000, "", 0, 0, 0, 100, 0, fecha, "", 0, 1, 0);
        Funcion funcion3 = new FuncionVIP(10000, "", 0, 0, 0, 100, 0, fecha, "", 0, 1, 0);
        Funcion funcionActual = new Funcion();
        
        while(opt != 0){
            System.out.println("\n===== BIENVENIDO A CINEX. MENU DE OPCIONES =====");
            System.out.println("1. Agregar una nueva funcion.");
            System.out.println("2. Realizar una reserva.");
            System.out.println("3. Calcular ingresos.");
            System.out.println("4. Actualizar y consultar estados.");
            System.out.println("5. Mostrar informacion de una funcion.");
            System.out.println("0. Salir.");
            opt = validar.validarEntero("un numero de opcion: ", "el numero de opcion no es valido", 0, 5);
            System.out.println();
            
            switch(opt){
                case 1 -> {
                    while(numFuncion != 0){
                        System.out.println("\n=== AGREGAR NUEVA FUNCION ===");
                        System.out.print("\tIndique tipo de funcion quiere agregar (1: Funcion Normal, 2: Funcion 3D, 3: Funcion VIP, 0: Salir): ");
                        numFuncion = datos.nextInt();
                        boolean esNueva = false;

                        switch (numFuncion) {
                            case 1 -> {
                                if(funcion1.getCodigoFuncion() == 10000 && funcion1.getTituloPelicula().isEmpty()){
                                    esNueva = true;
                                }
                                if(esNueva){
                                    System.out.println("\nFuncion Normal creada exitosamente.");
                                    funcion1.leerDatos();
                                    funcion1.mostrarInformacion();
                                } else {
                                    System.out.println("\t\nERROR: La funcion normal ya existe. Use otra funcion o modifique la existente con la opcion 4.");
                                }
                            }
                            case 2 -> {
                                if(funcion2.getCodigoFuncion() == 10000 && funcion2.getTituloPelicula().isEmpty()){
                                    esNueva = true;
                                }
                                if(esNueva){
                                    System.out.println("\nFuncion 3D creada exitosamente.");
                                    funcion2.leerDatos();
                                    funcion2.mostrarInformacion();
                                } else {
                                    System.out.println("\t\nERROR: La funcion 3D ya existe. Use otra funcion o modifique la existente con la opcion 4.");
                                }
                            }
                            case 3 -> {
                                if(funcion3.getCodigoFuncion() == 10000 && funcion3.getTituloPelicula().isEmpty()){
                                    esNueva = true;
                                }
                                if(esNueva){
                                    System.out.println("\nFuncion VIP creada exitosamente.");
                                    funcion3.leerDatos();
                                    funcion3.mostrarInformacion();
                                } else {
                                    System.out.println("\t\nERROR: La funcion VIP ya existe. Use otra funcion o modifique la existente con la opcion 4.");
                                }
                            }
                            case 0 -> {
                                System.out.println("Regresando...");
                            }
                            default -> System.out.println("\t\nERROR: Ingreso un tipo de funcion invalido. Indique 1 para Funcion Normal, 2 para Funcion 3D o 3 para Funcion VIP.");
                        }
                    }   
                }               
                case 2 -> {
                    System.out.println("\n=== REALIZAR RESERVA ===");
                    System.out.print("\tIndique tipo de funcion que quiere reservar (1: Funcion Normal, 2: Funcion 3D, 3: Funcion VIP): ");
                    numFuncion = datos.nextInt();
                    datos.nextLine();
                    
                    
                    funcionActual = funcionActual.seleccionarFuncion(funcion1, funcion2, funcion3, numFuncion);
                    if(funcionActual.getEstado() == 1){
                        System.out.println("");
                    }
                    else {
                        funcionActual.mostrarInformacion();
                        //validar el estado de la funcion
                        if(funcionActual.getEstado() == 0){
                            System.out.println("\t\nERROR: No se puede reservar porque la funcion esta CANCELADA.");
                        }
                        else if(funcionActual.getEstado() == 2){
                            System.out.println("\t\nERROR: No se puede reservar porque la funcion ya está EN EJECUCION.");
                        }
                        //validar que la fecha no haya pasado ya
                        else if(funcionActual.getFecha().isBefore(LocalDate.now())){
                            System.out.println("\t\nERROR: No se puede reservar porqie la fecha de la funcion ya ha pasado.");
                            System.out.println("Fecha de la funcion: " + funcionActual.getFecha());
                            System.out.println("Fecha actual: " + LocalDate.now());
                        }
                        //validar que haya asientos disponibles
                        else if(funcionActual.getAsientosDisponibles() <= 0){
                            System.out.println("\t\nERROR: No hay asientos disponibles para esta funcion.");
                        }
                        else {
                            System.out.print("\tCuantos asientos desea reservar? ");
                            int asientosReservar = datos.nextInt();
                            
                            if(asientosReservar <= 0){
                                System.out.println("\t\nERROR: Debe reservar al menos 1 asiento.");
                            }
                            else if(asientosReservar <= funcionActual.getAsientosDisponibles()){
                                if(funcionActual.getAsientosDisponibles() - asientosReservar <= 5){
                                    System.out.println("\tDescuento del 50% aplicado por promocion.");
                                    System.out.println("\n=== RESERVA REALIZADA EXITOSAMENTE ===");
                                    funcionActual.setAsientosReservados(funcionActual.getAsientosReservados() + asientosReservar);
                                    funcionActual.setAsientosDisponibles(funcionActual.getAsientosDisponibles() - asientosReservar);
                                    System.out.println("Asientos reservados: " + asientosReservar);
                                }                                
                            }
                            else {
                                System.out.println("\t\nERROR: No hay suficientes asientos disponibles.");
                                System.out.println("Asientos disponibles: " + funcionActual.getAsientosDisponibles());
                                System.out.println("Asientos solicitados: " + asientosReservar);
                            }
                        }                    
                    }
                }              
                case 3 -> {
                    System.out.println("\n=== CALCULAR INGRESOS ===");
                    System.out.print("\tIndique el tipo de funcion cuyos ingresos quiere calcular (1: Funcion Normal, 2: Funcion 3D, 3: Funcion VIP): ");
                    numFuncion = datos.nextInt();
                    
                    funcionActual = funcionActual.seleccionarFuncion(funcion1, funcion2, funcion3, numFuncion);
                    if(funcionActual != null){
                        double ingresoNormal = funcionActual.calcularIngreso();
                        System.out.println("\t\nEl ingreso total de la funcion es: $" + ingresoNormal);
                       //descuento para pocos asientos
                        if(funcionActual.getAsientosDisponibles() <= 5){
                            double ingresoPromocion = funcionActual.calcularIngreso(0.5);
                            //profe aqui esta lo de la promocion
                            System.out.println("\t\n=== PROMOCION APLICADA ===");
                            System.out.println("Ya que solo quedan " + funcionActual.getAsientosDisponibles() + " asientos disponibles, los boletos contaran con un descuento del 50%.");
                            System.out.println("\nEl ingreso con el descuento de la funcion es: $" + ingresoPromocion);
                        }
                    }
                }                
                case 4 -> {
                    System.out.println("\n=== ACTUALIZAR Y CONSULTAR ESTADOS ===");
                    System.out.print("\tIndique el tipo de funcion cuyos ingresos quiere calcular (1: Funcion Normal, 2: Funcion 3D, 3: Funcion VIP) para consultar y/o actualizar su estado: ");
                    numFuncion = datos.nextInt();
                    
                    funcionActual = funcionActual.seleccionarFuncion(funcion1, funcion2, funcion3, numFuncion);
                    if(funcionActual != null){
                        funcionActual.actualizarEstado();
                        System.out.println("\t\nEstado actualizado exitosamente.");
                    }
                }   
                case 5 -> {
                    System.out.println("\n=== MOSTRAR INFORMACION DE UNA FUNCION ===");
                    System.out.print("\tIndique el tipo de funcion cuyos ingresos quiere calcular (1: Funcion Normal, 2: Funcion 3D, 3: Funcion VIP) para mostrar su informacion: ");
                    numFuncion = datos.nextInt();
                    funcionActual = funcionActual.seleccionarFuncion(funcion1, funcion2, funcion3, numFuncion);
                    if(funcionActual != null){
                        funcionActual.mostrarInformacion();
                    }
                }                
                case 0 -> {
                    System.out.println("\t\nGracias por usar CineX. Hasta pronto");
                }
                
                default -> {
                    System.out.println("\t\nERROR: Ingreso una opcion invalida, intente nuevamente.");
                }
            }
        }
        datos.close();
    }
}
