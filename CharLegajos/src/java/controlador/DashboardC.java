package controlador;

import dao.DashboardD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.DashboardM;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "dashboardC")
@SessionScoped
public class DashboardC implements Serializable {

    private PieChartModel pieModelGen;
    private PieChartModel pieModelPro;
    private List<DashboardM> lstCantEmpGen;
    private List<DashboardM> lstCantEmpPro;
    
    @PostConstruct
    public void init() {
        try {
            lstCantEmpGen();
            lstCantEmpProf();
        } catch (Exception ex) {
        }
    }
    
    public void lstCantEmpGen() throws Exception {
        DashboardD dao;
        try {
            dao = new DashboardD();
            lstCantEmpGen = dao.lstCantEmpGen();
            graficarCantEmpGen(lstCantEmpGen);
        } catch (Exception e) {
        }
    }

    public void graficarCantEmpGen(List<DashboardM> lista) {
        pieModelGen = new PieChartModel();
        for (DashboardM alu : lstCantEmpGen) {
            pieModelGen.set(alu.getGENERO(), alu.getCANTIDADEMPGEN());
        }
        pieModelGen.setTitle("Porcentaje de Empleados por Género");
        pieModelGen.setLegendPosition("ne");
        pieModelGen.setShowDataLabels(true);
        pieModelGen.setDiameter(150);
    }
    
    public void lstCantEmpProf() throws Exception {
        DashboardD dao;
        try {
            dao = new DashboardD();
            lstCantEmpPro = dao.lstCantEmpProf();
            graficarCantEmpProf(lstCantEmpPro);
        } catch (Exception e) {
        }
    }
    
    public void graficarCantEmpProf(List<DashboardM> lista) {
        pieModelPro = new PieChartModel();
        for (DashboardM alu : lstCantEmpPro) {
            pieModelPro.set(alu.getPROFESION(), alu.getCANTIDADEMPPRO());
        }
        pieModelPro.setTitle("Porcentaje de Empleados por Profesión");
        pieModelPro.setLegendPosition("ne");
        pieModelPro.setShowDataLabels(true);
        pieModelPro.setDiameter(150);
    }

    public PieChartModel getPieModelGen() {
        return pieModelGen;
    }

    public void setPieModelGen(PieChartModel pieModelGen) {
        this.pieModelGen = pieModelGen;
    }

    public PieChartModel getPieModelPro() {
        return pieModelPro;
    }

    public void setPieModelPro(PieChartModel pieModelPro) {
        this.pieModelPro = pieModelPro;
    }

    public List<DashboardM> getLstCantEmpGen() {
        return lstCantEmpGen;
    }

    public void setLstCantEmpGen(List<DashboardM> lstCantEmpGen) {
        this.lstCantEmpGen = lstCantEmpGen;
    }

    public List<DashboardM> getLstCantEmpPro() {
        return lstCantEmpPro;
    }

    public void setLstCantEmpPro(List<DashboardM> lstCantEmpPro) {
        this.lstCantEmpPro = lstCantEmpPro;
    }
    
}
