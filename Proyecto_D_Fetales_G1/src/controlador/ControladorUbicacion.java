package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelo.coneccion;

@ManagedBean(name = "df")
@RequestScoped
public class ControladorUbicacion implements Serializable{

	private static final long serialVersionUID = 1L;
	private String provin;
	private List<String> listProv;
	private String canton;
	private List<String> listCant;
	private String parroquia; 
	private List<String> listParr;
	
	
	public ControladorUbicacion() {
		lista_prov();
		
	}
	
	public void lista_prov() {
		coneccion c = new coneccion();
		listProv= new ArrayList<>();
		listCant= new ArrayList<>();
		listParr= new ArrayList<>();
		ResultSet rs = null;
		
		try {
			c.iniciar_con();
			String sqlpr="select nombre_provincia from tb_provincia";
			rs= c.Consulta(sqlpr);
			while (rs.next()) {
				listProv.add(rs.getString(1));
			}
			
			String sqlca="select nombre_canton from tb_canton where cod_provincia = (select cod_provincia from tb_provincia "
					+ "where nombre_provincia='"+getProvin()+"')"; 
			rs= c.Consulta(sqlca);
			String sqlpa="select nombre_parroquia from tb_parroquia where cod_canton = (select cod_canton from tb_canton "
					+ "where nombre_canton='"+getCanton()+"')" ;
			
			rs= c.Consulta(sqlpa);
			
			while(rs.next()) {
				listCant.add(rs.getString(1));
				listParr.add(rs.getString(2));
				System.out.println(listCant);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		c.fin_cone();
	}

	public String getProvin() {
		return provin;
	}

	public void setProvin(String provin) {
		this.provin = provin;
	}

	public List<String> getListProv() {
		return listProv;
	}

	public void setListProv(List<String> listProv) {
		this.listProv = listProv;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public List<String> getListCant() {
		return listCant;
	}

	public void setListCant(List<String> listCant) {
		this.listCant = listCant;
	}

	public String getParroquia() {
		return parroquia;
	}

	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	public List<String> getListParr() {
		return listParr;
	}

	public void setListParr(List<String> listParr) {
		this.listParr = listParr;
	}

}
