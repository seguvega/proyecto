package controlador;

import java.io.Serializable;
import java.sql.ResultSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import modelo.coneccion;

@Named("asis")
@RequestScoped
public class Asistencia implements Serializable {
	private LineChartModel lineModel1;
	private BarChartModel barModel;
	private int anio;
	private int asistido1, asistido2, asistido3, asistido4, asistido5, asistido6, asistido7;

	@PostConstruct
	public void init() {
		asistencia();
		createBarModels();
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public int getAsistido1() {
		return asistido1;
	}

	public void setAsistido1(int asistido1) {
		this.asistido1 = asistido1;
	}

	public int getAsistido2() {
		return asistido2;
	}

	public void setAsistido2(int asistido2) {
		this.asistido2 = asistido2;
	}

	public int getAsistido3() {
		return asistido3;
	}

	public void setAsistido3(int asistido3) {
		this.asistido3 = asistido3;
	}

	public int getAsistido4() {
		return asistido4;
	}

	public void setAsistido4(int asistido4) {
		this.asistido4 = asistido4;
	}

	public int getAsistido5() {
		return asistido5;
	}

	public void setAsistido5(int asistido5) {
		this.asistido5 = asistido5;
	}

	public int getAsistido6() {
		return asistido6;
	}

	public void setAsistido6(int asistido6) {
		this.asistido6 = asistido6;
	}

	public int getAsistido7() {
		return asistido7;
	}

	public void setAsistido7(int asistido7) {
		this.asistido7 = asistido7;
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        
        ChartSeries asis1 = new ChartSeries();
        asis1.setLabel("Médico/a");
        asis1.set("Médico/a", getAsistido1() );
        
        ChartSeries asis2 = new ChartSeries();
        asis2.setLabel("Obstetriz/Obstetra");
        asis2.set("Obstetriz/Obstetra", getAsistido2());
        
        ChartSeries asis3 = new ChartSeries();
        asis3.setLabel("Enfermero/a");
        asis3.set("Enfermero/a", getAsistido3());
        
        ChartSeries asis4 = new ChartSeries();
        asis4.setLabel("Auxiliar de enfermería");
        asis4.set("Auxiliar de enfermería", getAsistido4());
        
        ChartSeries asis5 = new ChartSeries();
        asis5.setLabel("Partero calificado");
        asis5.set("Partero calificado", getAsistido5());
        
        ChartSeries asis6 = new ChartSeries();
        asis6.setLabel("Partero no calificado");
        asis6.set("Partero no calificado", getAsistido6());
        
        ChartSeries asis7 = new ChartSeries();
        asis7.setLabel("Otro");
        asis7.set("Otro",getAsistido7());
        
       
        model.addSeries(asis1);
        model.addSeries(asis2);
        model.addSeries(asis3);
        model.addSeries(asis4);
        model.addSeries(asis5);
        model.addSeries(asis6);
        model.addSeries(asis7);
        return model;
    }

	private void createBarModels() {
		barModel = initBarModel();

		barModel.setTitle("Asistido Por: " + getAnio());
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Asistencia");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Madres");
		yAxis.setMin(0);
		yAxis.setMax(1500);
	}

	private void asistencia() {

		coneccion c = new coneccion();
		ResultSet rs = null;
		int a1, a2, a3, a4, a5, a6, a7;

		try {
			c.iniciar_con();
			String sql1 = "Select count(*) from tg_defuncionesfetales " + "where asis_por = 1 and anio_fall = "
					+ getAnio();
			rs = c.Consulta(sql1);
			while (rs.next()) {
				a1 = rs.getInt(1);
				setAsistido1(a1);

			}
			// nivel 2
			String sql2 = "Select count(*) from tg_defuncionesfetales " + "where asis_por = 2 and anio_fall = "
					+ getAnio();
			rs = c.Consulta(sql2);

			while (rs.next()) {
				a2 = rs.getInt(1);
				setAsistido2(a2);
			}

			// nivel 3

			String sql3 = "Select count(*) from tg_defuncionesfetales " + "where asis_por = 3 and anio_fall = "
					+ getAnio();
			rs = c.Consulta(sql3);

			while (rs.next()) {
				a3 = rs.getInt(1);
				setAsistido3(a3);
			}

			// nivel 4

			String sql4 = "Select count(*) from tg_defuncionesfetales " + "where asis_por = 4 and anio_fall = "
					+ getAnio();
			rs = c.Consulta(sql4);

			while (rs.next()) {
				a4 = rs.getInt(1);
				setAsistido4(a4);
			}

			// nivel 5
			String sql5 = "Select count(*) from tg_defuncionesfetales " + "where asis_por = 5 and anio_fall = "
					+ getAnio();
			rs = c.Consulta(sql5);

			while (rs.next()) {
				a5 = rs.getInt(1);
				setAsistido5(a5);
			}

			// nivel 6
			String sql6 = "Select count(*) from tg_defuncionesfetales " + "where asis_por = 6 and anio_fall = "
					+ getAnio();
			rs = c.Consulta(sql6);

			while (rs.next()) {
				a6 = rs.getInt(1);
				setAsistido6(a6);
			}

			// nivel 7

			String sql7 = "Select count(*) from tg_defuncionesfetales " + "where asis_por = 7 and anio_fall = "
					+ getAnio();
			rs = c.Consulta(sql7);

			while (rs.next()) {
				a7 = rs.getInt(1);
				setAsistido7(a7);
			}


		} catch (Exception e) {
			// TODO: handle exception
		}

		c.fin_cone();
	}
}
