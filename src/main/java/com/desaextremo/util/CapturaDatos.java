package com.desaextremo.util;

import com.desaextremo.modelo.Producto;
import javax.swing.JOptionPane;

/**
 * Esta clase permite la captura y conversión de datos haciendo uso de la clase
 * {@code JOptionPane} y sus metodos para presentacion de cuadros de dialogo y
 * mensajes.
 *
 * <p>
 * Convierte el dato ingresado al tipo de dato primitivo segun necesidad del
 * usuario y realiza un control de excepción de tipo
 * {@code NumberFormatException} que hace que se ingrese en un ciclo infinito
 * hasta que el valor registrado en el cuadro de dialogo corresponda con el tipo
 * de dato requerido.
 *
 * Es una adaptaci{on de la versión original en mi repositorio:
 * https://github.com/desaextremo/CapturaDatosGrafica Clonar:
 * https://github.com/desaextremo/CapturaDatosGrafica.git
 *
 * @author Crhistian Ovalle Gamba
 */
public class CapturaDatos {

    /**
     * Convierte el dato ingresado a un valor {@code int}, controlando excepcion
     * de tipo {@code NumberFormatException} e ingresando en un ciclo hasta que
     * el valor registrado en el cuadro de dialogo corresponda con un numero
     * entero (int).
     *
     * @param mensaje un texto que aprecera como mensaje al usuario
     * @param titulo titulo del cuadro de dialogo
     * @param icono imagen que aparecera en el cuadro de dialogo
     * <blockquote>
     *
     *
     * <table border=1 summary="Common JOptionPane method names and their descriptions">
     * <tr>
     * <th>Tipo de mensaje</th>
     * <th>Valor</th>
     * </tr>
     * <tr>
     * <td>ERROR_MESSAGE</td>
     * <td>0</td>
     * </tr>
     * <tr>
     * <td>INFORMATION_MESSAGE</td>
     * <td>1</td>
     * </tr>
     * <tr>
     * <td>WARNING_MESSAGE</td>
     * <td>2</td>
     * </tr>
     * <tr>
     * <td>QUESTION_MESSAGE</td>
     * <td>3</td>
     * </tr>
     * </table>
     *
     * </blockquote>
     * @return dato convertido a {@code int}
     */
    public static int leeInt(String mensaje, String titulo, int icono) {
        int retorno = -1;
        boolean estado = false;
        do {
            try {
                retorno = Integer.parseInt(JOptionPane.showInputDialog(null, mensaje, titulo, validaIcono(icono)));

                estado = true;
            } catch (NumberFormatException e) {
                estado = false;
            }
        } while (!estado);
        return retorno;
    }
    
    /**
     * Devuelve el dato ingresado, controlando excepcion de tipo
     * {@code NullPointerException} e ingresando en un ciclo hasta que se
     * ingrese informacion en el cuadro de dialogo.
     *
     * @param mensaje un texto que aprecera como mensaje al usuario
     * @param titulo titulo del cuadro de dialogo
     * @param icono icono que aparecera en el cuadro de dialogo
     * <blockquote>
     *
     *
     * <table border=1 summary="Common JOptionPane method names and their descriptions">
     * <tr>
     * <th>Tipo de mensaje</th>
     * <th>Valor</th>
     * </tr>
     * <tr>
     * <td>ERROR_MESSAGE</td>
     * <td>0</td>
     * </tr>
     * <tr>
     * <td>INFORMATION_MESSAGE</td>
     * <td>1</td>
     * </tr>
     * <tr>
     * <td>WARNING_MESSAGE</td>
     * <td>2</td>
     * </tr>
     * <tr>
     * <td>QUESTION_MESSAGE</td>
     * <td>3</td>
     * </tr>
     * </table>
     *
     * </blockquote>
     * @return dato ingresado
     */
    public static String leeString(String mensaje, String titulo, int icono) {
        String retorno = "";
        boolean estado = false;
        do {
            try {
                retorno = JOptionPane.showInputDialog(null, mensaje, titulo, validaIcono(icono));
                if (retorno.length() > 0) {
                    estado = true;
                }
            } catch (NullPointerException e) {
                estado = false;
            }
        } while (!estado);
        return retorno;
    }

    /**
     * Presenta cuadro de dialogo con un menu de opciones
     *
     * @param textoMenu Texto del menu de opcion
     * @param titulo Titulo del cuadro de dialogo
     * @param limiteInferior Limite inferior del grupo de opciones de menu
     * @param limiteSuperior Limite superior del grupo de opciones de menu
     * @return numero de la opcion selecciona
     */
    public static int presentaMenu(String textoMenu, String titulo, int limiteInferior, int limiteSuperior) {
        int opcion = 0;
        do {
            opcion = leeInt(textoMenu, titulo, 1);
            if (opcion == -1 || opcion == 0) {
                continue;
            }
            if (validaRangoOpciones(opcion, limiteInferior, limiteSuperior)) {
                continue;
            }
            opcion = -1;
        } while (opcion == -1);
        return opcion;
    }
    
    /**
     * Presenta cuadro de dialogo con un menu de opciones
     * Versi{on sobrecargada para presentar menu con opciones String
     *
     * @param textoMenu Texto del menu de opcion
     * @param titulo Titulo del cuadro de dialogo
     * @param limiteInferior Limite inferior del grupo de opciones de menu
     * @param limiteSuperior Limite superior del grupo de opciones de menu
     * @return numero de la opcion selecciona
     */
    public static String presentaMenu(String textoMenu, String titulo) {
        String opcion = "ERROR";
        opcion = leeString(textoMenu, titulo, 1);
        return opcion;
    }

    /**
     * Presenta un cuadro de mensaje al usuario, haciendo uso del metodo
     * {@code JOptionPane.showMessageDialog}
     *
     * @param mensaje Mensaje para presentar al usuario
     * @param titulo Tituo del cuadro de mensaje
     * @param icono Valor para asignar al icono en el cuadro de dialogo
     */
    public static void muestraMensaje(String mensaje, String titulo, int icono) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, validaIcono(icono));
    }
    
    /**
     * Valida la cadena de caracteres ingresada para evaluar el caso Multas por exceso de velocidad. 
     * @param entrada Cadena de caracteres que contiene: distancia, Velocidad Limite
     * @param subcadena arreglo de cadenas de caraceres que se pasa por referencia. Continene informaci{on de cada parametro
     *                  despues de dividir la cadena de caracteres usando el caracter espacio como separador
     * @return bolleano true si no hay error, o false si algún error ocurre
     */
    public static boolean capturarDatosMultaVelocidad(String entrada,String[] subcadena){
        boolean estado = true;
        
        entrada = entrada.trim();//elimina espacios en blanco
            
        //si no encuentra los tres valores para realizar el calculo, termina el ciclo
        if ((subcadena.length > 3) ||  (subcadena.length < 3)){ 
            CapturaDatos.muestraMensaje("Fin del programa, debe indicar:\n-distancia entre camaras,\n-velocidad maxima permitida en el tramo,\n-tiempo de recorrido en seg:\n" + entrada , "Multas por exceso de velocidad", JOptionPane.ERROR_MESSAGE);
            estado=false;
        }

        //validaciones de conversion a enteros para saber si puede realizar el calculo
        //Utiliza expresión regular para evaluar si en la cadena de caracteres viene algun caracter diferente a numérico
        // El primer elemento del array contiene la distancia

        else if (!subcadena[0].matches("[+-]?\\d*(\\.\\d+)?")){
            CapturaDatos.muestraMensaje("distancia contiene valores no numericos:\n" + entrada, "Multas por exceso de velocidad", JOptionPane.ERROR_MESSAGE);
            estado=false;
        }

        else if (!subcadena[1].matches("[+-]?\\d*(\\.\\d+)?")){
            CapturaDatos.muestraMensaje("Velocidad Limite contiene valores no numericos:\n" + entrada, "Multas por exceso de velocidad", JOptionPane.ERROR_MESSAGE);
            estado=false;
        }

        else if (!subcadena[2].matches("[+-]?\\d*(\\.\\d+)?")){
            CapturaDatos.muestraMensaje("tiempo contiene valores no numericos:\n" + entrada, "Multas por exceso de velocidad", JOptionPane.ERROR_MESSAGE);
            estado=false;
        }
        
        return estado;
    }
    
    /**
     * Permite la captura de datos necesarrios para crear un producto. Realiza validaciones de cantidad de atributos necesarios y tipos de datos
     * @return una referencia a un objeto producto o null si ocurre algún error
     */
    public static Producto capturaProducto(){
        
        Producto producto;
        String entrada; // String que contiene la línea de entrada
        String[] subcadena; // Array que contendrá las subcadenas de la entrada
        //Datos para crear el producto
        int codigo=0;
        String nombre="";
        long precio=0;
        int inventario=0;
        
        entrada = CapturaDatos.leeString("Ingrese información del producto\nCódigo,nombre, precio, cantidad en inventario", "Inventario para una tienda de víveres", JOptionPane.INFORMATION_MESSAGE);
        entrada = entrada.trim();//elimina espacios en blanco

        // Separamos la cadena de entrada en todas las subcadenas que hay 
        // entre espacios en blanco y se guardan en el array de Strings
        subcadena = entrada.split(" ");

         //si no encuentra los cuatro valores para realizar el calculo, termina el ciclo
        if ((subcadena.length > 4) ||  (subcadena.length < 4)){ 
            CapturaDatos.muestraMensaje("Debe indicar:\n-Código\n-nombre\n-precio\n-cantidad en inventario\n\n" + entrada , "Inventario para una tienda de víveres", JOptionPane.ERROR_MESSAGE);
            producto=null;
        

        //validaciones de conversion a enteros para saber si puede realizar el calculo
        //Utiliza expresión regular para evaluar si en la cadena de caracteres viene algun caracter diferente a numérico
        // El primer elemento del array contiene la distancia

        }else if (!subcadena[0].matches("[+-]?\\d*(\\.\\d+)?")){
            CapturaDatos.muestraMensaje("Código contiene valores no numericos:\n" + entrada, "Inventario para una tienda de víveres", JOptionPane.ERROR_MESSAGE);
            producto=null;
        }else if (!subcadena[2].matches("[+-]?\\d*(\\.\\d+)?")){
            CapturaDatos.muestraMensaje("precio contiene valores no numericos:\n" + entrada, "Inventario para una tienda de víveres", JOptionPane.ERROR_MESSAGE);
            producto=null;
        }else if (!subcadena[3].matches("[+-]?\\d*(\\.\\d+)?")){
            CapturaDatos.muestraMensaje("cantidad en inventario valores no numericos:\n" + entrada, "Inventario para una tienda de víveres", JOptionPane.ERROR_MESSAGE);
            producto=null;
        }else{
            codigo=Integer.parseInt(subcadena[0]);
            nombre= subcadena[1];
            precio = Long.parseLong(subcadena[2]);
            inventario=Integer.parseInt(subcadena[3]);

            producto = new Producto(codigo,nombre,precio,inventario);
        }
        
        return producto;
    }

    /**
     * Valida que el valor ingresado por el usuario en menu de ocpiones se
     * encuentre entre el rango de valores minimo y maximo permitido.
     *
     * @param opcionSeleccionada valor registrado por el usuario
     * @param limiteInferior valor minimo permitido por el grupo de opciones de
     * un menu
     * @param limiteSuperior valor maximo permitido por el grupo de opciones de
     * un menu
     * @return booleano indicando true si el valor del parametro
     * {@code opcionSeleccionada} este dentro del rango o false en caso
     * contrario
     */
    private static boolean validaRangoOpciones(int opcionSeleccionada, int limiteInferior, int limiteSuperior) {
        boolean estadoValidacion = false;
        if (opcionSeleccionada < limiteInferior || opcionSeleccionada > limiteSuperior) {
            muestraMensaje("Debe ingresar un valor entre " + limiteInferior + " y " + limiteSuperior, "Por favor verifique...",JOptionPane.ERROR_MESSAGE);
            estadoValidacion = false;
        } else {
            estadoValidacion = true;
        }
        return estadoValidacion;
    }
    
    /**
     * Valida que el valor recibido como parametro para asignar el icono a cuadro de mensaje este dentro del rango valido
     * @param valorIcono
     * @return el valor del icono a asignar el el cuadro de dialogo
     */
    private static int validaIcono(int valorIcono){
        int icono=0;
        
        if ((valorIcono > 3)&&(valorIcono < 0))
            icono = 1;
        else
            icono = valorIcono;    
        
        return icono;
    }
}
