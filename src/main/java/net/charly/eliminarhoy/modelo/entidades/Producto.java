/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.charly.eliminarhoy.modelo.entidades;

import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author ca921
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Producto implements Serializable{
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double precioProducto;
    private int existencia;
    private Date createdAt;
    private int idCategoria;

    
    
}
