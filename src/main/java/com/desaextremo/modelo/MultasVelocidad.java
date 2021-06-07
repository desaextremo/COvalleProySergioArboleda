package com.desaextremo.modelo;

import com.desaextremo.util.CapturaDatos;
import javax.swing.JOptionPane;

/**
 * Multas por exceso de velocidad: Clase con método estatico para clacular la velocidad maxima permitida
 * @author Crhistian Ovalle Gamba
 */
public class MultasVelocidad {
    public static void validadMulta(){
        String entrada; // String que contiene la línea de entrada
        String[] subcadena; // Array que contendrá las subcadenas de la entrada
        float distancia; // Distancia recorrido en metros
        float velocidadLimite; // Velocidad en Km/h
        float tiempo; // Tiempo en segundos que ha tardado el vehículo realmente
        float velocidadCalculada;
        boolean estado;
        
        String menuOpciones = "Ingrese la información para calcular Velocidad Maxima y Evaluar posible multa:\n(distancia entre camaras,\nvelocidad maxima permitida en el tramo,\ntiempo de recorrido en seg) \n"
                + "\nTermina cuando el valor para distancia entre camaras,velocidad maxima permitida en el tramo y tiempo de recorrido en seg son:\n0 0 0\n"
                + "\n o cuando se escriba terminar";
        
        while (true) {
            entrada = CapturaDatos.leeString(menuOpciones, "Multas por exceso de velocidad", JOptionPane.INFORMATION_MESSAGE);
            
            if (entrada.trim().equalsIgnoreCase("terminar")){
                CapturaDatos.muestraMensaje("Ha terminado la aplicación", "Multas por exceso de velocidad", JOptionPane.ERROR_MESSAGE);
                break;
            }
            // Separamos la cadena de entrada en todas las subcadenas que hay 
            // entre espacios en blanco y se guardan en el array de Strings
            subcadena = entrada.split(" ");
            estado = CapturaDatos.capturarDatosMultaVelocidad(entrada,subcadena);
                    
            if (estado){
            
                distancia = Integer.parseInt(subcadena[0]);
                // El segundo la velocidad en Km/h
                velocidadLimite = Integer.parseInt(subcadena[1]);
                // El tercero el tiempo empleado en segundos
                tiempo = Integer.parseInt(subcadena[2]);

                // Condición de salida del bucle. Si todos son cero
                if (distancia == 0 && velocidadLimite == 0 && tiempo == 0) {
                    CapturaDatos.muestraMensaje("Fin del programa, valores en 0", "Multas por exceso de velocidad", JOptionPane.ERROR_MESSAGE);
                    break; // Salgo del bucle while y terminamos
                }
                // Si se cumple lanzo ERROR y empiezo desde el principio
                // leyendo una nueva línea
                if (distancia <= 0 || velocidadLimite <= 0 || tiempo <= 0) {
                    CapturaDatos.muestraMensaje("ERROR, valores negativos:\n" + distancia + "\n" + velocidadLimite + "\n" + tiempo, "Multas por exceso de velocidad", JOptionPane.ERROR_MESSAGE);
                    continue; // Salto al principio de bucle while
                }

                // Paso velocidad metros/segundo a Kilometros/hora
                velocidadCalculada = (distancia / tiempo) * 3.6F;

                // Si velocidad calculada es menor y igual a la velocidad límite
                if (velocidadCalculada <= velocidadLimite) {
                    CapturaDatos.muestraMensaje("OK", "Multas por exceso de velocidad", JOptionPane.INFORMATION_MESSAGE);
                } else if (velocidadCalculada >= velocidadLimite * 1.20) {
                    // La velocidad es igual o mayor a la velocidad límite mas un 20%
                    CapturaDatos.muestraMensaje("CURSO SENSIBILIZACION", "Multas por exceso de velocidad", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Velocidad esta entre la velocidad límite y la velocidad 
                    // límite más un 20%
                    CapturaDatos.muestraMensaje("MULTA", "Multas por exceso de velocidad", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                break;
            }
        }
    }
}