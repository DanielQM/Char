package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection cn;

    public void Conexion() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@35.229.77.157:1521:XE", "Legajo", "Vallegrande2019");
            System.out.println("Conectado");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void Cerrar() throws SQLException {
        if (cn != null) {
            if (cn.isClosed() == false) {
                cn.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Conexion conexion = new Conexion();
            conexion.Conexion();
        } catch (SQLException e) {
        }
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

}
