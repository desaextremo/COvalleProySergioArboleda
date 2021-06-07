package com.desaextremo.launcher;

import com.desaextremo.modelo.BaseDatosProductos;
import com.desaextremo.modelo.MultasVelocidad;
import com.desaextremo.modelo.Producto;
import com.desaextremo.util.CapturaDatos;
import javax.swing.JOptionPane;

/**
 * Clase principal de la aplicación, presenta menú de opciones y permite interactuar con el reto 1 y 2
 * 1 Multas por exceso de velocidad
 * 2 Inventario para una tienda de víveres
 * 
 * Propuestos para la convocatoria instructor-tutor de la Universidad Sergio Arboleda
 * 
 * @author Crhistian Ovalle Gamba
 * 
 */
public class Principal {
    public static void main(String[] args) {
        int datoInt;
        long datoLong;
        String datoString;
        int opcion = 0;
        
        BaseDatosProductos baseDatosProductos;

        String menuOpciones = "1 Multas por exceso de velocidad \n"
                + "2 Inventario para una tienda de víveres \n"
                + "3 Terminar\n";

        do {
            //Arma menu de opciones al usuario
            opcion = CapturaDatos.presentaMenu(menuOpciones, "Examen Formadores y Tutores", 1, 3);

            switch (opcion) {
                //1 Multas por exceso de velocidad
                case 1:
                    MultasVelocidad.validadMulta();
                    break;
                //2 Inventario para una tienda de víveres
                case 2:
                    //Instancia la clase BaseDatosProductos e invoca al menu de opciones
                    baseDatosProductos = new BaseDatosProductos ();
                    baseDatosProductos.opcionesMenu();
                    break;
                //3 Terminar programa
                case 3:
                    CapturaDatos.muestraMensaje("Crhistian Ovalle Gamba\ndesarrolloextremo@gmail.com\n3107533161", "Examen Formadores y Tutores",JOptionPane.INFORMATION_MESSAGE);
            }
        } while (opcion != 3);
        //termina y baja la maquina virtual
        System.exit(0);
    }
}