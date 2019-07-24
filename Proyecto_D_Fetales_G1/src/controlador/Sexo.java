package controlador;

import java.io.Serializable;
import java.sql.ResultSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.PieChartModel;

import modelo.coneccion;

@Named("pastelSexo")
@RequestScoped
public class Sexo implements Serializable {
	
	    private PieChartModel pieModel2;

	 
	    @PostConstruct
	    public void init() {
	        createPieModels();
	    }
	 
	    public void itemSelect(ItemSelectEvent event) {
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
	                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	 
	    public PieChartModel getPieModel2() {
	        return pieModel2;
	    }
	 
	  
	    private void createPieModels() {
	        createPieModel2();
	    }
	 
	 
	    private void createPieModel2() {
	        pieModel2 = new PieChartModel();
	        pieModel2.set("Hombre",mujer() );
	        pieModel2.set("Mujer", hombre());
	        pieModel2.set("Indeterminado", indeterminado());
	        pieModel2.setTitle("Porcentaje de sexo de fetos fallecidos");
	        pieModel2.setLegendPosition("e");
	        pieModel2.setFill(false);
	        pieModel2.setShowDataLabels(true);
	        pieModel2.setDiameter(150);
	        pieModel2.setShadow(false);
	        
	    }
	    
	    public double mujer() {
	    	coneccion c= new coneccion();
	    	ResultSet rs = null; 
	    	double mujer = 0; 
	    	try {
				c.iniciar_con(); 
				String sql = "Select count (*) from tg_defuncionesfetales where sexo =2 ";
				rs= c.Consulta(sql); 
				while(rs.next()) {
					mujer=rs.getInt(1);
					System.out.println(mujer);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	return mujer;
	    }
	    
	    public double hombre() {
	    	coneccion c= new coneccion();
	    	ResultSet rs = null; 
	    	double hombre = 0; 
	    	try {
				c.iniciar_con(); 
				String sql = "Select count (*) from tg_defuncionesfetales where sexo =1";
				rs= c.Consulta(sql); 
				while(rs.next()) {
					hombre=rs.getInt(1);
					System.out.println(hombre);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	return hombre;
	    }
	    
	    public double indeterminado() {
	    	coneccion c= new coneccion();
	    	ResultSet rs = null; 
	    	double indeterminado = 0; 
	    	try {
				c.iniciar_con(); 
				String sql = "Select count (*) from tg_defuncionesfetales where sexo =3";
				rs= c.Consulta(sql); 
				while(rs.next()) {
					indeterminado=rs.getInt(1);
					System.out.println(indeterminado);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	return indeterminado;
	    }
	    

}
