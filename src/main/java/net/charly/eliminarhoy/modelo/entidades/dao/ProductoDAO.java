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

    private static final String SQL_INSERT = "INSERT INTO producto (nombreProducto, descripcionProducto, precioProducto, existencia, create_at, idCategoria) VALUES (?,?,?,?,NOW(),?)";
    private static final String SQL_UPDATE = "UPDATE producto SET nombreProducto = ?, descripcionProducto = ?, precioProducto = ?, existencia = ?, create_at = NOW(), idCategoria = ? "
            + "WHERE idProducto = ?";
    private static final String SQL_DELETE = "DELETE FROM producto WHERE idProducto = ?";
    private static final String SQL_SELECT = "SELECT * FROM producto WHERE idProducto = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM producto";

    public boolean insertar(Producto p) {
        conectar();
        try {
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, p.getNombreProducto());
            ps.setString(2, p.getDescripcionProducto());
            ps.setDouble(3, p.getPrecioProducto());
            ps.setInt(4, p.getExistencia());
            ps.setInt(5, p.getIdCategoria());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Error al insertar Producto! :( " + ex.getLocalizedMessage());

        } finally {
            desconectar();
        }
        return false;
    }

    public boolean actualizar(Producto p) {
        conectar();
        try {
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getNombreProducto());
            ps.setString(2, p.getDescripcionProducto());
            ps.setDouble(3, p.getPrecioProducto());
            ps.setInt(4, p.getExistencia());
            ps.setInt(5, p.getIdCategoria());
            ps.setInt(6, p.getIdProducto());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Error al actualizar la producto! :( " + ex.getLocalizedMessage());

        } finally {
            desconectar();
        }
        return false;
    }

    public boolean eliminar(Producto p) {
        conectar();
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, p.getIdProducto());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Error al eliminar la producto! :( " + ex.getLocalizedMessage());

        } finally {
            desconectar();
        }
        return false;
    }

    public Producto obtenerProducto(int id) {
        Producto p = null;
        try {
            conectar();
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs = ps.executeQuery();
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
            System.out.println("Error al obtener la categoria! " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }
        return p;
    }

    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        try {
            conectar();
            ps = conn.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
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
            System.out.println("Error al obtener la categoria! " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }
        return productos;
    }

}
