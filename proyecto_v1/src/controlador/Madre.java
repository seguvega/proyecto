package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import modelo.coneccion;

@Named("madre")
@RequestScoped

public class Madre implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int anio ;	
	private String asistencia; 
	private String nacionalidad;
	private List<String>nacionalidad_lista;
	private int edad ; 
	private String etnia;
	private List<String> etnialista;
	private int instruccion; 
	private String lugar;
	private List<String> lug_ocurr;
	private int trimestre;
	private int total;
	
	
/* -----------------get y sets-------------------------*/
	
	
	public String getAsistencia() {
		return asistencia;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getTrimestre() {
		return trimestre;
	}
	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getEtnia() {
		return etnia;
	}
	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}
	public List<String> getEtnialista() {
		return etnialista;
	}
	public void setEtnialista(List<String> etnialista) {
		this.etnialista = etnialista;
	}
	public int getInstruccion() {
		return instruccion;
	}
	public void setInstruccion(int instruccion) {
		this.instruccion = instruccion;
	}
	public List<String> getLug_ocurr() {
		return lug_ocurr;
	}
	public void setLug_ocurr(List<String> lug_ocurr) {
		this.lug_ocurr = lug_ocurr;
	}
	
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;}
	
	
	
	
	/*METODOS*/
	
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public List<String> getNacionalidad_lista() {
		return nacionalidad_lista;
	}
	public void setNacionalidad_lista(List<String> nacionalidad_lista) {
		this.nacionalidad_lista = nacionalidad_lista;
	}
	public Madre() {
		try {
			lista_lugar();
			etnia();
			nacionalidad();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void lista_lugar()throws SQLException{
		
		coneccion c=new coneccion();
		lug_ocurr= new ArrayList<>(); 
		ResultSet rs = null; 
		try {
			c.iniciar_con();
			String sql = "select descripcion_lugar from d_lugar_ocurrencia";
			rs= c.Consulta(sql);
	
			while (rs.next()) {
				lug_ocurr.add(rs.getString(1));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		c.fin_cone();
	}
	
	public void etnia ()throws SQLException{
		coneccion c=new coneccion();
		etnialista=new ArrayList<>();
		ResultSet rs = null; 
		try {
			c.iniciar_con();
			String sql = "select nombre_etnia from d_etnia";
			rs= c.Consulta(sql);
	
			while (rs.next()) {
				etnialista.add(rs.getString(1));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void nacionalidad (){
		coneccion c=new coneccion();
		nacionalidad_lista=new ArrayList<>();
		ResultSet rs = null; 
		try {
			c.iniciar_con();
			String sql = "select nacionalidad from d_nacionalidad";
			rs= c.Consulta(sql);
	
			while (rs.next()) {
				nacionalidad_lista.add(rs.getString(1));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int sum() {
		coneccion c=new coneccion();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sqlTotal = "SELECT count(*) from tg_defuncionesfetales where etnia = (SELECT ID_ETNIA from d_etnia "
					+ "where NOMBRE_ETNIA='"+getEtnia()+"' and anio_fall = "+getAnio()+")"; 
			System.out.println(sqlTotal);
			rs=c.Consulta(sqlTotal);
			while(rs.next()) {
				total=rs.getInt(1);
				System.out.println(total);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return total;
		
	}
	
	public int sumNac() {
		coneccion c=new coneccion();
		ResultSet rs = null;
		int totalnac=0;
		try {
			c.iniciar_con();
			

			String sqlTotal = "SELECT count(*) FROM tg_defuncionesfetales where nac_mad = (SELECT ID_NACIONALIDAD "
					+ "FROM NAC_MAD where DES_NA='"+getNacionalidad()+"') and anio_fall="+getAnio();
			System.out.println(sqlTotal);
			rs=c.Consulta(sqlTotal);
			while(rs.next()) {
				totalnac=rs.getInt(1);
				System.out.println(total);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return totalnac;
	}
	
	public int sumlugar() {
		coneccion c=new coneccion();
		ResultSet rs = null;
		int totallug=0;
		try {
			c.iniciar_con();
			

			String sqlTotal ="SELECT count(*) FROM tg_defuncionesfetales "
					+ "where  LUGAR_OCURR=(SELECT ID_OCURRENCIA FROM TB_LUGAR_OCUR "
					+ "where DESCRIPCION_LUGAR = '"+getLugar()+"' )and anio_fall="+getAnio();
			System.out.println(sqlTotal);
			rs=c.Consulta(sqlTotal);
			while(rs.next()) {
				totallug=rs.getInt(1);
				System.out.println(total);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return totallug;
	}
	
	public int sumtr() {
		int tr=getTrimestre();
		int r1=0;
		int r2=0;
		if (tr==1) {
			r1=1;
			r2=3;
		}else if (tr==2) {
			r1=4;
			r2=6;
			
		}else if (tr==3) {
			r1=7;
			r2=9;
		}else if (tr==4) {
			r1=10;
			r2=12;
		}
		coneccion c=new coneccion();
		ResultSet rs = null;
		int totatr=0;
		try {
			c.iniciar_con();
			String sqlTotal ="select count(*) from tg_defuncionesfetales where "
					+ "to_number(anio_fall) = "+getAnio()+" and mes_fall between "+r1+" and "+r2+" group by mes_fall";
			System.out.println(sqlTotal);
			rs=c.Consulta(sqlTotal);
			while(rs.next()) {
				totatr=rs.getInt(1);
				System.out.println(total);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return totatr;

	}

}
