/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.charly.eliminarhoy.config;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ca921
 */
public class Conexion {

    public Connection conn;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    public CallableStatement cs = null;

    public void conectar() {
        String url = "jdbc:mysql://localhost:3306/inventario_eliminar";
        String user = "root";
        String pass = "root";

        try {
            conn = DriverManager.getConnection(url, user, pass);

        } catch (SQLException ex) {
            System.out.println("Error al conectar! " + ex.getMessage());
        }

    }

    public void desconectar() {
        try {
            if (ps != null) {
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            if(cs != null){
                cs.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException ex) {
            System.out.println("Error al desconectar! " + ex.getLocalizedMessage());
        }

    }

}
