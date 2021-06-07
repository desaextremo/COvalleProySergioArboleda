package com.desaextremo.modelo;

import java.text.DecimalFormat;

/**
 * Esta clase representa a los productos del almacen disponibles en la tienda de Pepe
 * @author Crhistian Ovalle Gamba
 */
public class Producto {
    /**
     * Código del producto
     */
    private int codigo;
    /**
     * Nombre del producto
     */
    private String nombre;
    /**
     * precio del producto
     */
    private long precio;
    /**
     * Cantidad del producto en el inventario
     */
    private int inventario;
    
    /**
     * Constructor por defecto
     */
    public Producto() {
       
    }
    
    /**
     * Constructur sobrecargado, invoca al constructor por defecto para generar el código del producto e
     * inicializar los atributos del objeto
     * @param codigo valor para el codigo
     * @param nombre valor para el nombre
     * @param precio valor para el precio
     * @param inventario  valor para la cantidad de producto en el inventario
     */
    public Producto(int codigo, String nombre, long precio, int inventario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }
    /**
     * Permite consultar el codigo del producto
     * @return codigo del producto
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Permite asignr un valor al codigo del producto
     * @param codigo valor para asignar al código del producto
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Permite consultar el nombre del producto
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Permite asignar un valor al nombre del producto
     * @param nombre valor para asiganar al nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Permite consultar el precio del producto
     * @return precio del producto
     */
    public long getPrecio() {
        return precio;
    }

    /**
     * Permite asignar un valor al precio del producto
     * @param precio valor para asignar al precio del producto
     */
    public void setPrecio(long precio) {
        this.precio = precio;
    }

    /**
     * Permite consultar la cantidad de producto en inventario
     * @return cantidad de producto en inventario
     */
    public int getInventario() {
        return inventario;
    }

    /**
     * Permite asignar un valor a la canidad de producto en inventario
     * @param inventario 
     */
    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    @Override
    /**
     * Imprimer el contenido del producto
     */
    public String toString() {
       DecimalFormat df = new DecimalFormat("#.0"); 
        
       return this.getCodigo() + " " + this.getNombre() + " " + df.format(this.getPrecio()) + " " + this.getInventario();
    }
    
    
}
