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
@Named ("barras")
@RequestScoped
public class Niv_inst implements Serializable {
	
	 private LineChartModel lineModel1;
	    
	    private BarChartModel barModel;
	    private int nivel1;
	    private int nivel2;
	    private int nivel3;
	    private int nivel4;
	    private int nivel5;
	    private int nivel6;
	    private int nivel7;
	    private int nivel8;
	    private int nivel9;
	    private int nivel10;
	    
	 
	    @PostConstruct
	    public void init() {
	    	controles();
	        createBarModels();
	    }
	 
	    public void itemSelect(ItemSelectEvent event) {
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
	                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
	 
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	    public LineChartModel getLineModel1() {
	        return lineModel1;
	    }
	 
	    public BarChartModel getBarModel() {
	        return barModel;
	    }
	    
	 
	    public int getNivel1() {
			return nivel1;
		}

		public void setNivel1(int nivel1) {
			this.nivel1 = nivel1;
		}

		public int getNivel2() {
			return nivel2;
		}

		public void setNivel2(int nivel2) {
			this.nivel2 = nivel2;
		}

		public int getNivel3() {
			return nivel3;
		}

		public void setNivel3(int nivel3) {
			this.nivel3 = nivel3;
		}

		public int getNivel4() {
			return nivel4;
		}

		public void setNivel4(int nivel4) {
			this.nivel4 = nivel4;
		}
		

		public int getNivel5() {
			return nivel5;
		}

		public void setNivel5(int nivel5) {
			this.nivel5 = nivel5;
		}

		public int getNivel6() {
			return nivel6;
		}

		public void setNivel6(int nivel6) {
			this.nivel6 = nivel6;
		}

		public int getNivel7() {
			return nivel7;
		}

		public void setNivel7(int nivel7) {
			this.nivel7 = nivel7;
		}

		public int getNivel8() {
			return nivel8;
		}

		public void setNivel8(int nivel8) {
			this.nivel8 = nivel8;
		}

		public int getNivel9() {
			return nivel9;
		}

		public void setNivel9(int nivel9) {
			this.nivel9 = nivel9;
		}

		public int getNivel10() {
			return nivel10;
		}

		public void setNivel10(int nivel10) {
			this.nivel10 = nivel10;
		}

		private BarChartModel initBarModel() {
	        BarChartModel model = new BarChartModel();
	        
	        ChartSeries nivel1 = new ChartSeries();
	        nivel1.setLabel("Ninguno");
	        nivel1.set("Ninguno", getNivel1());
	        
	        ChartSeries nivel2 = new ChartSeries();
	        nivel2.setLabel("Centro de alfabetizacion");
	        nivel2.set("Centro Alfabetización", getNivel2());
	        
	        ChartSeries nivel3 = new ChartSeries();
	        nivel3.setLabel("Primaria");
	        nivel3.set("Primaria", getNivel3());
	        
	        ChartSeries nivel4 = new ChartSeries();
	        nivel4.setLabel("Secundaria");
	        nivel4.set("Secundaria", getNivel4());
	        
	        ChartSeries nivel5 = new ChartSeries();
	        nivel5.setLabel("Educación básica");
	        nivel5.set("Ed.Básica", getNivel5());
	        
	        ChartSeries nivel6 = new ChartSeries();
	        nivel6.setLabel("Eduación media/bachillerato");
	        nivel6.set("media/bachillerato", getNivel6());
	        
	        ChartSeries nivel7 = new ChartSeries();
	        nivel7.setLabel("Ciclo Post-Bachillerato");
	        nivel7.set("Post-Bachillerato", getNivel7());
	        
	        ChartSeries nivel8 = new ChartSeries();
	        nivel8.setLabel("Superior");
	        nivel8.set("Superior", getNivel8());
	        
	        ChartSeries nivel9 = new ChartSeries();
	        nivel9.setLabel("Postgrado");
	        nivel9.set("Postgrado", getNivel9());
	        
	        ChartSeries nivel10 = new ChartSeries();
	        nivel10.setLabel("Se ignora");
	        nivel10.set("Ignorado", getNivel10());
	        
	        model.addSeries(nivel1);
	        model.addSeries(nivel2);
	        model.addSeries(nivel3);
	        model.addSeries(nivel4);
	        model.addSeries(nivel5);
	        model.addSeries(nivel6);
	        model.addSeries(nivel7);
	        model.addSeries(nivel8);
	        model.addSeries(nivel9);
	        model.addSeries(nivel10);
	 
	        return model;
	    }
	 
	    private void createBarModels() {
	        createBarModel();
	    }
	 
	    private void createBarModel() {
	        barModel = initBarModel();
	 
	        barModel.setTitle("Nivel de instrucción 2015-2016");
	        barModel.setLegendPosition("ne");
	 
	        Axis xAxis = barModel.getAxis(AxisType.X);
	        xAxis.setLabel("Nivel de instrucción");
	 
	        Axis yAxis = barModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Madres");
	        yAxis.setMin(0);
	        yAxis.setMax(900);
	    }
	    
	    private void controles() {
	    	coneccion c= new coneccion();
	    	ResultSet rs = null;
	    	int n1, n2, n3, n4, n5,n6,n7,n8,n9,n10;
	    	
	    	try {
				c.iniciar_con();
				String sql1= "Select count(*) from tg_defuncionesfetales "
						+ "where niv_inst = 1";
				rs= c.Consulta(sql1);
				while(rs.next()) {
					n1=rs.getInt(1);
					setNivel1(n1);
				}
				//nivel 2
				String sql2="Select count(*) from tg_defuncionesfetales "
						+ "where niv_inst = 2";
				rs=c.Consulta(sql2);
				
				while(rs.next()) {
					n2=rs.getInt(1);
					setNivel2(n2);
				}
				
				//nivel 3
				
				String sql3="Select count(*) from tg_defuncionesfetales "
						+ "where niv_inst = 3";
				rs=c.Consulta(sql3);
				
				while(rs.next()) {
					n3=rs.getInt(1);
					setNivel3(n3);
				}
				
				//nivel 4
				
				String sql4="Select count(*) from tg_defuncionesfetales "
						+ "where niv_inst = 4";
				rs=c.Consulta(sql4);
				
				while(rs.next()) {
					n4=rs.getInt(1);
					setNivel4(n4);
				}
				
				//nivel 5 
				String sql5="Select count(*) from tg_defuncionesfetales "
						+ "where niv_inst = 5";
				rs=c.Consulta(sql5);
				
				while(rs.next()) {
					n5=rs.getInt(1);
					setNivel5(n5);
				}
				
				//nivel 6
				String sql6="Select count(*) from tg_defuncionesfetales "
						+ "where niv_inst = 6";
				rs=c.Consulta(sql6);
				
				while(rs.next()) {
					n6=rs.getInt(1);
					setNivel6(n6);
				}
				
				//nivel 7 
				
				String sql7="Select count(*) from tg_defuncionesfetales "
						+ "where niv_inst = 7";
				rs=c.Consulta(sql7);
				
				while(rs.next()) {
					n7=rs.getInt(1);
					setNivel7(n7);
				}
				
				//nivel 8 
				String sql8="Select count(*) from tg_defuncionesfetales "
						+ "where niv_inst =8";
				rs=c.Consulta(sql8);
				
				while(rs.next()) {
					n8=rs.getInt(1);
					setNivel8(n8);
				}
				
				//nivel 9 
				String sql9="Select count(*) from tg_defuncionesfetales "
						+ "where niv_inst =9";
				rs=c.Consulta(sql9);
				
				while(rs.next()) {
					n9=rs.getInt(1);
					setNivel9(n9);
				}
				
				//nivel10 
				
				String sql10="Select count(*) from tg_defuncionesfetales "
						+ "where niv_inst = 10";
				rs=c.Consulta(sql10);
				
				while(rs.next()) {
					n10=rs.getInt(1);
					setNivel10(n10);
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	
	    	c.fin_cone();
	    }

}
