/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinex;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;
/**
 *
 * Elaborado por Anabella Freites y Jose Quintero
 */
public class Validacion {
    Scanner datos = new Scanner(System.in);

    public String validarCadena(String mensaje, String patron, String mensajeError) {
        String entrada;
        while (true) {
            System.out.println(mensaje);
            entrada = datos.nextLine();
            if (entrada.matches(patron))
                return entrada;
            else
                System.err.println("Error: " + mensajeError);
        }
    }

    public int validarEntero(String mensaje, String mensajeE, int liminf, int limsup ){
        boolean infinito = true; String entrada;
        int entero=0;
        do {
        try {
            System.out.print("Indica "+mensaje);
            entrada = datos.nextLine().trim();
            entero = Integer.parseInt(entrada);
            if (entero < liminf || entero > limsup)
            System.err.println("Error "+mensajeE+" debe estar entre "+liminf+" y "+limsup);
            else
            infinito = false;
            }
            catch (NumberFormatException objeto){
            System.err.println("Error "+ mensajeE + " no es un numero entero ");
            }
        }
        while (infinito);

        return entero;
    }

    public LocalDate validarFecha(String mensaje, String mensajeError) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu")
        .withResolverStyle(ResolverStyle.STRICT);
        LocalDate fechaValida = null;
        while (fechaValida == null) {
            try {
                System.out.print(mensaje);
                String entrada = datos.nextLine().trim();
                fechaValida = LocalDate.parse(entrada, dtf);
            }
            catch (DateTimeParseException e) {
                System.err.println("Error: " + mensajeError);
            }
        }
        return fechaValida;
    }

    public String validarCorreo(){
        String correo;

        while(true){
            System.out.println("Indique su correo electronico: ");
            correo = datos.nextLine().trim();
            if(correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
            return correo;
            else
            System.err.println("Error. Correo no valido");
        }
    }

    public double validarDecimal(String mensaje, String mensajeE, double liminf, double limsup) {
        String entrada;
        double decimal = 0;
        while(true) {
            try {
                System.out.print("Indique " + mensaje);
                entrada = datos.nextLine().trim();
                decimal = Double.parseDouble(entrada);

                if(decimal < liminf || decimal > limsup) {
                    System.out.println("Error " + mensajeE + " debe estar entre " + liminf + " y " + limsup);
                }
                else {
                    return decimal;
                }
            }
            catch (NumberFormatException e) {
                System.err.println("Error " + mensajeE + " debe ser un numero decimal (ej: 15.5)");
            }
        }
    }

    public String validarTelefono(){
        String entrada;

        while(true){
            System.out.println("Indique su numero de telefono: ");
            entrada = datos.nextLine().trim();

            if(entrada.matches("^[0-9]{4}[- ]?[0-9]{7}$"))
                return entrada;
            else System.err.println("\nERROR. El numero de telefono no es valido");
           }
    }

    public String validarSoloLetras(String mensaje , String mensajeE){
        String entrada;

        while(true){
            System.out.println("Indique " + mensaje + ": ");
            entrada = datos.nextLine().trim();

            if(entrada.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"))
                return entrada;
            else System.err.println("\nERROR. " + mensaje + " debe " + mensajeE);
        }
    }

    public String validarCedula(){
        String cedula;

        while(true){
            System.out.println("Indique su numero de cedula: ");
            cedula = datos.nextLine().trim();

            if(cedula.matches("^[VEve]?-?[0-9]{7,8}$"))
                return cedula;
            else System.err.println("\nERROR. Formuato de cedula invalida");
            }
    }

    public String validarHora(String mensaje, String mensajeError) {
        String hora;
        while(true) {
            System.out.print(mensaje);
            hora = datos.nextLine().trim();

            if(hora.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")){
                return hora;
            }
            else{
                System.err.println("Error: " + mensajeError);
                System.out.println("Formato valido: HH:MM (ejemplos: 14:30, 9:43)");
            }
        }
    }

    public String validarStringNoVacio(String mensaje, String mensajeError){
        String entrada;

        while(true){
            System.out.println(mensaje);
            entrada = datos.nextLine().trim();

            if(!entrada.isEmpty()){
                return entrada;
            }
            else{
                System.out.println(mensajeError);
            }
        }
    }

    public char validarCaracter(String mensaje, String mensajeError){
    String caracter;

        while(true){
            System.out.println(mensaje);
            caracter = datos.nextLine().trim();
            if (caracter.length()==1){
                return caracter.charAt(0);
            }
            else{
                System.out.println("ERROR: " + mensajeError);
            }
        }
    }
    
    public double validarPrecioBoleto(double costoBase){
        String entrada;
        double precio = 0;
        double minimo = (costoBase * 0.20) + costoBase;
        while(true) {
            try {
                System.out.print("Indique el precio del boleto: ");
                entrada = datos.nextLine().trim();
                precio = Double.parseDouble(entrada);

                if(precio > minimo) {
                    return precio;
                }
                else {
                    System.err.println("Error. El precio de boleto no es valido");
                }
            }
            catch (NumberFormatException e) {
                System.err.println("Error el precio del boleto debe ser un numero entero o decimal ");
            }
        }
    }
}