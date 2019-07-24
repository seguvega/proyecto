package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import modelo.Canton;
import modelo.Parroquia;
import modelo.Provincia;
import modelo.coneccion;

@ManagedBean(name = "parr")
@RequestScoped
public class ParroquiaControl  implements Serializable{

	private String parroq ;
	private List<Parroquia> listparr;
	
	public void lista_parroquia(String canton) {
		Parroquia parr = null;
		coneccion c = new coneccion();
		ResultSet rs = null;
		listparr = new ArrayList<>();

		try {
			c.iniciar_con();
			String sql = "select nombre_parroquia from tb_parroquia where cod_canton = (select cod_canton from tb_canton "
					+ "where nombre_canton='" + canton + "')";
			rs = c.Consulta(sql);
			while (rs.next()) {
				parr = new Parroquia(rs.getString(1));
				listparr.add(parr);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		c.fin_cone();

	}

	public String getParroq() {
		return parroq;
	}

	public void setParroq(String parroq) {
		this.parroq = parroq;
	}

	public List<Parroquia> getListparr() {
		return listparr;
	}

	public void setListparr(List<Parroquia> listparr) {
		this.listparr = listparr;
	}
	
	
}
