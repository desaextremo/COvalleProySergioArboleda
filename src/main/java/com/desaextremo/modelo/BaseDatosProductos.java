package com.desaextremo.modelo;

import com.desaextremo.util.CapturaDatos;
import com.desaextremo.modelo.Producto;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * Esta clase representa al listado de productos. El listado de productos es un HashMap que almacena objetos de tipo Producto, y usa
 * el código del producto como llave o identificador único.
 * Cuenta con métodos para AGREGAR, ACTUALIZAR, BORRAR, LISTAR productos al listado, y métodos adicionales para validaciones de negocio.
 * @author Crhistian Ovalle Gamba
 */
public class BaseDatosProductos {
    private HashMap<Integer, Producto> listaProductos;
    
    /**
     * Constructor que se encarga de instanciar el HashMap o listado de Productos
     */
    public BaseDatosProductos() {
       listaProductos =  new HashMap<>();
    } 
    
    /**
     * Se encarga de agregar un producto al listado. Valida si ya existe un producto en el listado cuyo código coincida con el codigo del producto 
     * recibido como parametro.
     * Si el producto ya existe en el listado genera mensaje de error, en caso contrario, agrega el prducto al listado e imprime mensaje 
     * indicando que ha sido agregado y presentando información de resumen sobre el producto y resumen sobre el listado:
     * 
     * Producto precio mayor
     * Producto precio menor
     * Promedio de precios
     * Valor del inventario
     * 
     * @param producto Producto a ingresar al listado
     */
    public void agregar (Producto producto){
        //valida que el codigo del producto no exista en el HashMap
        if (existeCodigoProducto(producto.getCodigo()))
             CapturaDatos.muestraMensaje("El codigo de producto " + producto.getCodigo() + " ya existe, por favor verifique", "AGREGAR PRODUCTO", JOptionPane.ERROR_MESSAGE);
        else{    
            listaProductos.put(producto.getCodigo(),producto);
            CapturaDatos.muestraMensaje(producto.toString(), "Producto Agregado", JOptionPane.INFORMATION_MESSAGE);
            CapturaDatos.muestraMensaje(productoMayorValor()+"\n" + productoMenorValor() + "\n" + promedioPrecios() + "\n" + valorInventario(), "Producto Agregado", JOptionPane.INFORMATION_MESSAGE); 
        }
        
    }
    
    /**
     * Se encarga de actualizar un producto del listado. Valida si el producto existe, y de ser asi actualiza sus datos, para luego imprimir mensaje 
     * indicando que ha sido actualizado y presentando información de resumen sobre el producto y resumen sobre el listado:
     * 
     * Producto precio mayor
     * Productoprecio menor
     * Promedio de precios
     * Valor del inventario
     * 
     * @param producto Producto para actualizar en el listado
     */
    public void actualizar(Producto producto){
        //valida que el codigo del producto no exista en el HashMap
        if (!existeCodigoProducto(producto.getCodigo()))
            CapturaDatos.muestraMensaje("El codigo de producto " + producto.getCodigo() + " no existe, por favor verifique", "ACTUALIZAR PRODUCTO", JOptionPane.ERROR_MESSAGE);
        else{   
            listaProductos.put(producto.getCodigo(),producto);
            CapturaDatos.muestraMensaje(producto.toString(), "Producto Actualizado", JOptionPane.INFORMATION_MESSAGE);
            CapturaDatos.muestraMensaje(productoMayorValor()+"\n" + productoMenorValor() + "\n" + promedioPrecios() + "\n" + valorInventario(), "Producto Actualizado", JOptionPane.INFORMATION_MESSAGE); 
        }
    }
    
    /**
     * Se encarga de borrar un producto del listado. Valida si el producto existe, y de ser asi lo borra usando su codigo de producto, 
     * para luego imprimir mensaje indicando que ha sido borrado y presentando información de resumen sobre el producto y resumen 
     * sobre el listado:
     * 
     * Producto precio mayor
     * Producto precio menor
     * Promedio de precios
     * Valor del inventario
     * 
     * @param producto Producto a eliminar
     */
    public void borrar(Producto producto){
        //valida que el codigo del producto no exista en el HashMap
        if (!existeCodigoProducto(producto.getCodigo()))
            CapturaDatos.muestraMensaje("El codigo de producto " + producto.getCodigo() + " no existe, por favor verifique", "BORRAR PRODUCTO", JOptionPane.ERROR_MESSAGE);
        else{
            listaProductos.remove(producto.getCodigo());
            CapturaDatos.muestraMensaje(producto.toString(), "Producto Eliminado", JOptionPane.INFORMATION_MESSAGE); 
            if (!listaVacia()){
                CapturaDatos.muestraMensaje(productoMayorValor()+"\n" + productoMenorValor() + "\n" + promedioPrecios() + "\n" + valorInventario(), "Producto Eliminado", JOptionPane.INFORMATION_MESSAGE); 
            }
        }
    }
    
    /**
     * Se encarga de recorrer el listado de productos, presentar el listado de productos con su respectivo:
     * 
     * codigo, nombre, precio y cantidad de inventario y luego presenta un cuadro de dialogo con:
     * 
     * Producto precio mayor
     * Producto precio menor
     * Promedio de precios
     * Valor del inventario
     */
    public void listar(){
        if (!listaVacia()){
            StringBuilder resultado = new StringBuilder();
            for (Map.Entry<Integer, Producto> e : listaProductos.entrySet()){

                resultado.append(e.getValue()).append("\n");
            }
            CapturaDatos.muestraMensaje(resultado.toString(), "Datos Tienda de Pepe",JOptionPane.INFORMATION_MESSAGE);
            CapturaDatos.muestraMensaje(productoMayorValor()+"\n" + productoMenorValor() + "\n" + promedioPrecios() + "\n" + valorInventario(), "Resumen Productos", JOptionPane.INFORMATION_MESSAGE); 
        }    
    }
    
    /**
     * Se encarga de presentar un cuadro de dialogo con las opciones validas, se incluye una opción listar que muestra el listado de productos, y
     * facilita comprobación de programa
     * 
     * Opciones validas AGREGAR, ACTUALIZAR, BORRAR, LISTAR, cualquier otro valor rompe el ciclo y el menú de opciones termina
     */
    public void opcionesMenu(){
        String entrada; // String que contiene la línea de entrada
        String[] subcadena; // Array que contendrá las subcadenas de la entrada
        int codigo=0;
        String nombre="";
        long precio=0;
        int inventario=0;
        
        String opcion = "FIN";

        String menuOpciones = "Ingrese la operacion a realizar: (AGREGAR, ACTUALIZAR, BORRAR, LISTAR) \n"
                + "o cualquier otro valor para terminar  \n";
        Producto producto;
        
        do {
            //Arma menu de opciones al usuario
            opcion = CapturaDatos.presentaMenu(menuOpciones, "Inventario para una tienda de víveres");

            switch (opcion) {
                //AGREGAR
                case "AGREGAR":
                    producto = CapturaDatos.capturaProducto();
                    if (producto == null) break;
                    this.agregar(producto);
                    break;
                //ACTUALIZAR
                case "ACTUALIZAR":
                    if (this.listaVacia())
                        CapturaDatos.muestraMensaje("No es posible aactualizar información, la lista se encuentra vacia","Actualizar Producto", JOptionPane.ERROR_MESSAGE);
                    else {
                        producto = CapturaDatos.capturaProducto();
                        if (producto == null) break;
                        this.actualizar(producto);
                    
                    }
                    break;
                //BORRAR
                case "BORRAR":
                    if (this.listaVacia())
                        CapturaDatos.muestraMensaje("No es posible borrar información, la lista se encuentra vacia","Borrar Producto", JOptionPane.ERROR_MESSAGE);
                    else {
                        producto = CapturaDatos.capturaProducto();
                        if (producto == null) break;
                        this.borrar(producto);
                    }
                    break;
                 //BORRAR
                case "LISTAR":
                    if (this.listaVacia())
                        CapturaDatos.muestraMensaje("No es posible borrar información, la lista se encuentra vacia","Listar Productos", JOptionPane.ERROR_MESSAGE);
                    else
                        this.listar();
                    break;
                default:
                    CapturaDatos.muestraMensaje("Ha seleccionado terminar", "Inventario para una tienda de víveres",JOptionPane.INFORMATION_MESSAGE);
                    opcion = "TERMINAR";
            }
        } while (!opcion.equals("TERMINAR"));
    }
    
    
    /**
     * Valida si el codigo de producto recibido como parametro existe en el HashMap
     * @param codigoProducto
     * @return 
     */
    private boolean existeCodigoProducto(int codigoProducto){
        boolean existe = true; 
        if (listaProductos.containsKey(codigoProducto))
           existe=true;
        else
            existe=false;
        
        return existe;
    }
    
    /**
     * Se encarga de encontrar el producto con el mayor precio, recorriendo el listado
     * de productos y comparando su precio contra el mayor valor almacenado hasta el momento en la variable
     * mayorPrecio.
     * @return Cadena de caracteres informando respescto al Producto con el precio mayor
     */
    private String productoMayorValor(){
        long mayorPrecio=0;
        Producto producto=null;
        DecimalFormat df = new DecimalFormat("#.0");
        
        StringBuilder resultado = new StringBuilder();
        for (Map.Entry<Integer, Producto> e : listaProductos.entrySet()){
            
            
            if (e.getValue().getPrecio() > mayorPrecio){
                mayorPrecio = e.getValue().getPrecio();
                producto = e.getValue();
            }
            
        }
        return "Producto precio mayor: " + producto.getNombre() + " " + df.format(producto.getPrecio());
    }
    
    /**
     * Se encarga de encontrar el producto con el menor precio, recorriendo el listado
     * de productos y comparando su precio contra el menor valor almacenado hasta el momento en la variable
     * menorPrecio
     * @return Cadena de caracteres informando respescto al Producto con el precio menor
     */
    private String productoMenorValor(){
        long menorPrecio=100000;
        Producto producto=null;
        DecimalFormat df = new DecimalFormat("#.0");
        
        StringBuilder resultado = new StringBuilder();
        for (Map.Entry<Integer, Producto> e : listaProductos.entrySet()){
            
            
            if (e.getValue().getPrecio() < menorPrecio){
                menorPrecio = e.getValue().getPrecio();
                producto = e.getValue();
            }
            
        }
        return "Producto precio menor: " + producto.getNombre() + " " + df.format(producto.getPrecio());
    }
    
    
    /**
     * Se ancarga de calcular el promedio de precios, realizando una sumatoria de precios
     * y dividiendo entre la cantidad de productos del listado
     * @return promedio de precios
     */
    private String promedioPrecios(){
        long sumaPrecios=0;
        DecimalFormat df = new DecimalFormat("#.0"); 
        
        StringBuilder resultado = new StringBuilder();
        for (Map.Entry<Integer, Producto> e : listaProductos.entrySet()){
            sumaPrecios+=e.getValue().getPrecio();
        }
        
        sumaPrecios = sumaPrecios / listaProductos.size();
        return "Promedio de precios: " + df.format(sumaPrecios);
    }
    
    /**
     * Se encarga de calcular el valor del inventario realizando una sumatoria total de:
     * 
     * precio de cada producto * cantidad de prodcuto en inventario
     * @return 
     */
    private String valorInventario(){
        double valorInventario=0.0;
        DecimalFormat df = new DecimalFormat("#.0"); 
        
        StringBuilder resultado = new StringBuilder();
        for (Map.Entry<Integer, Producto> e : listaProductos.entrySet()){
            valorInventario+= (e.getValue().getPrecio() * e.getValue().getInventario());
        }
        
        return "Valor del inventario: " + valorInventario;
    }
    
    /**
     * Se encarga de validar si la lista de productos se encuentra vacia
     * @return true si es vacia, o false en caso contrario
     */
    private boolean listaVacia(){
        boolean esVacia=false;
        if (listaProductos.size() == 0)
            esVacia=true;
        else
            esVacia=false;
        
        return esVacia;
    }
}