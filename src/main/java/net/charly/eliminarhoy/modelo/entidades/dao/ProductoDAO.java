/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.charly.eliminarhoy.modelo.entidades.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.charly.eliminarhoy.config.Conexion;
import net.charly.eliminarhoy.modelo.entidades.Producto;

/**
 *
 * @author ca921
 */
public class ProductoDAO extends Conexion {

    private static final String SQL_INSERT = "{CALL sp_insertarProducto(?,?,?,?,?)}";
    private static final String SQL_UPDATE = "{CALL sp_actualizarProducto(?,?,?,?,?,?)}";
    private static final String SQL_DELETE = "{CALL sp_eliminarProducto(?)}";
    private static final String SQL_SELECT = "{CALL sp_obtenerProducto(?)}";
    private static final String SQL_SELECT_ALL = "{CALL sp_obtenerProductos()}";

    public String insertar(Producto p) {
        conectar();
        String resultado = "";
        try {
            cs = conn.prepareCall(SQL_INSERT);
            cs.setString(1, p.getNombreProducto());
            cs.setString(2, p.getDescripcionProducto());
            cs.setDouble(3, p.getPrecioProducto());
            cs.setInt(4, p.getExistencia());
            cs.setInt(5, p.getIdCategoria());

            boolean tieneResultado = cs.execute();

            if (tieneResultado) {
                rs = cs.getResultSet();

                if (rs.next()) {
                    resultado = rs.getString("tResultado");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al insertar Producto! :( " + ex.getLocalizedMessage());

        } finally {
            desconectar();
        }
        return resultado;
    }

    public String actualizar(Producto p) {
        conectar();
        String resultado = "";
        try {
            cs = conn.prepareCall(SQL_UPDATE);
            cs.setString(1, p.getNombreProducto());
            cs.setString(2, p.getDescripcionProducto());
            cs.setDouble(3, p.getPrecioProducto());
            cs.setInt(4, p.getExistencia());
            cs.setInt(5, p.getIdCategoria());
            cs.setInt(6, p.getIdProducto());

            boolean tieneResultado = cs.execute();

            if (tieneResultado) {
                rs = cs.getResultSet();

                if (rs.next()) {
                    resultado = rs.getString("tResultado");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al actualizar el producto! :( " + ex.getLocalizedMessage());

        } finally {
            desconectar();
        }
        return resultado;
    }

    public String eliminar(Producto p) {
        conectar();
        String resultado = "";
        try {
            cs = conn.prepareCall(SQL_DELETE);
            cs.setInt(1, p.getIdProducto());

            boolean tieneResultado = cs.execute();

            if (tieneResultado) {
                rs = cs.getResultSet();

                if (rs.next()) {
                    resultado = rs.getString("tResultado");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al eliminar el producto! :( " + ex.getLocalizedMessage());

        } finally {
            desconectar();
        }
        return resultado;
    }

    public Producto obtenerProducto(int id) {
        Producto p = null;
        try {
            conectar();
            cs = conn.prepareCall(SQL_SELECT);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            if (rs.next()) {
                p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombreProducto"));
                p.setDescripcionProducto(rs.getString("descripcionProducto"));
                p.setPrecioProducto(rs.getDouble("precioProducto"));
                p.setExistencia(rs.getInt("existencia"));
                p.setCreatedAt(rs.getDate("create_at"));
                p.setIdCategoria(rs.getInt("idCategoria"));
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtener el producto especificado! " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }
        return p;
    }

    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        try {
            conectar();
            cs = conn.prepareCall(SQL_SELECT_ALL);
            rs = cs.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombreProducto"));
                p.setDescripcionProducto(rs.getString("descripcionProducto"));
                p.setPrecioProducto(rs.getDouble("precioProducto"));
                p.setExistencia(rs.getInt("existencia"));
                p.setCreatedAt(rs.getDate("create_at"));
                p.setIdCategoria(rs.getInt("idCategoria"));

                productos.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtener los productos disponibles! " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }
        return productos;
    }

}
