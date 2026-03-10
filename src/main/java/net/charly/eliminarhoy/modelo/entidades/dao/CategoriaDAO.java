/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.charly.eliminarhoy.modelo.entidades.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.charly.eliminarhoy.config.Conexion;
import net.charly.eliminarhoy.modelo.entidades.Categoria;

/**
 *
 * @author ca921
 */
public class CategoriaDAO extends Conexion {

    private static final String SQL_INSERT = "{CALL sp_insertarCategoria(?,?)}";
    private static final String SQL_UPDATE = "{CALL sp_actualizarCategoria(?,?,?)}";
    private static final String SQL_DELETE = "{CALL sp_eliminarCategoria(?)}";
    private static final String SQL_SELECT = "{CALL sp_obtenerCategoria(?)}";
    private static final String SQL_SELECT_ALL = "{CALL sp_obtenerCategorias()}";

    public String insertar(Categoria c) {
        conectar();
        String resultado = "";
        try {
            cs = conn.prepareCall(SQL_INSERT);
            cs.setString(1, c.getNombreCategoria());
            cs.setString(2, c.getDescripcion());

            boolean tieneResultado = cs.execute();

            if (tieneResultado) {
                rs = cs.getResultSet();

                if (rs.next()) {
                    resultado = rs.getString("tResultado");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al insertar categoria! :( " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }

        return resultado;
    }

    public String actualizar(Categoria c) {
        conectar();
        String resultado = "";
        try {
            cs = conn.prepareCall(SQL_UPDATE);
            cs.setString(1, c.getNombreCategoria());
            cs.setString(2, c.getDescripcion());
            cs.setInt(3, c.getIdCategoria());

            boolean tieneResultado = cs.execute();

            if (tieneResultado) {
                rs = cs.getResultSet();

                if (rs.next()) {
                    resultado = rs.getString("tResultado");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al actualizar la categoria! :( " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }

        return resultado;
    }

    public String eliminar(Categoria c) {
        conectar();
        String resultado = "";
        try {
            cs = conn.prepareCall(SQL_DELETE);
            cs.setInt(1, c.getIdCategoria());

            boolean tieneResultado = cs.execute();

            if (tieneResultado) {
                rs = cs.getResultSet();

                if (rs.next()) {
                    resultado = rs.getString("tResultado");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al eliminar la categoria! :( " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }

        return resultado;
    }

    public Categoria obtenerCategoria(int id) {
        Categoria c = null;
        try {
            conectar();
            cs = conn.prepareCall(SQL_SELECT);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            if (rs.next()) {
                c = new Categoria();
                c.setIdCategoria(rs.getInt("idCategoria"));
                c.setNombreCategoria(rs.getString("nombreCategoria"));
                c.setDescripcion(rs.getString("descripcionCategoria"));
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtener la categoria especificada! " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }
        return c;
    }

    public List<Categoria> obtenerCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        try {
            conectar();
            cs = conn.prepareCall(SQL_SELECT_ALL);
            rs = cs.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("idCategoria"));
                c.setNombreCategoria(rs.getString("nombreCategoria"));
                c.setDescripcion(rs.getString("descripcionCategoria"));

                categorias.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtener las categorias disponibles! " + ex.getLocalizedMessage());
        } finally {
            desconectar();
        }
        return categorias;
    }

}
