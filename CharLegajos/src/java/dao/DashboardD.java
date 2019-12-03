package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DashboardM;

public class DashboardD extends Conexion {

    public List<DashboardM> lstCantEmpGen() throws Exception {
        List<DashboardM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT DISTINCT SEXEMP GENERO, COUNT(*) CANTIDAD FROM EMPLEADO\n"
                    + "WHERE (SEXEMP LIKE 'M' OR SEXEMP LIKE 'F') AND ESTEMP LIKE 'A'\n"
                    + "GROUP BY SEXEMP";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            DashboardM dashboard;
            while (rs.next()) {
                dashboard = new DashboardM();
                dashboard.setGENERO(rs.getString("GENERO"));
                dashboard.setCANTIDADEMPGEN(rs.getInt("CANTIDAD"));
                lista.add(dashboard);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<DashboardM> lstCantEmpProf() throws SQLException {
        List<DashboardM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT COUNT(*) CANTIDAD, PROFESION.NOMPRO PROFESION FROM EMPLEADO\n"
                    + "INNER JOIN PROFESION ON PROFESION.CODPRO = EMPLEADO.CODPRO\n"
                    + "WHERE PROFESION.ESTPRO LIKE 'A' AND EMPLEADO.ESTEMP LIKE 'A'\n"
                    + "GROUP BY PROFESION.NOMPRO";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            DashboardM dashboard;
            while (rs.next()) {
                dashboard = new DashboardM();
                dashboard.setPROFESION(rs.getString("PROFESION"));
                dashboard.setCANTIDADEMPPRO(rs.getInt("CANTIDAD"));
                lista.add(dashboard);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}
