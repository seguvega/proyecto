package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import modelo.coneccion;

@Named("pr")
@RequestScoped


public class Ubicacion implements Serializable{

	private static final long serialVersionUID = 1L;
	public static String pro;
	private static List<String> listProv;
	private static List<String> lispro;
	public static String canton;
	private static List<String> liscant;
	private static String parroquia;
	private static List<String> lisparr;
	
	public Ubicacion() {
		lista_prov();
	}
	
	public void lista_prov() {
		coneccion c = new coneccion();
		listProv= new ArrayList<>();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sqlpr="select nombre_provincia from tb_provincia";
			rs= c.Consulta(sqlpr);
			while (rs.next()) {
				listProv.add(rs.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(pro);
		c.fin_cone();
	}



	public void lista_Can(AjaxBehaviorEvent event) {
		coneccion c = new coneccion();
		liscant= new ArrayList<>();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sqlpr="select nombre_canton from tb_canton where cod_provincia=(SELECT cod_provincia "
					+ "FROM tb_provincia where tb_provincia.nombre_provincia='"+getPro()+"')";
			rs= c.Consulta(sqlpr);
			while (rs.next()) {
				liscant.add(rs.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.fin_cone();
	}
	
	public void lista_parr(AjaxBehaviorEvent event){
		System.out.println("ingresa al metodo");
		coneccion c = new coneccion();
		lisparr= new ArrayList<>();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sqlpr="SELECT nombre_parroquia FROM tb_parroquia where cod_canton="
					+ "(SELECT cod_canton FROM tb_canton where nombre_canton='"+getCanton()+"')";
			rs= c.Consulta(sqlpr);
			while (rs.next()) {
				System.out.println("ingresa al while");
				lisparr.add(rs.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.fin_cone();
		
	}
	

	public String getPro() {
		return pro;
	}



	public void setPro(String pro) {
		this.pro = pro;
	}



	public List<String> getListProv() {
		return listProv;
	}



	public void setListProv(List<String> listProv) {
		this.listProv = listProv;
	}



	public List<String> getLispro() {
		return lispro;
	}



	public void setLispro(List<String> lispro) {
		this.lispro = lispro;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	public String getCanton() {
		return canton;
	}





	public void setCanton(String canton) {
		this.canton = canton;
	}





	public List<String> getLiscant() {
		return liscant;
	}





	public void setLiscant(List<String> liscant) {
		this.liscant = liscant;
	}

	public String getParroquia() {
		return parroquia;
	}

	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	public List<String> getLisparr() {
		return lisparr;
	}

	public void setLisparr(List<String> lisparr) {
		this.lisparr = lisparr;
	}
	
	
	
	
}
