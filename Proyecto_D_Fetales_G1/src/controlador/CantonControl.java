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
@ManagedBean(name = "cant")
@RequestScoped

public class CantonControl implements Serializable {
	private String canton;
	private List<Canton> listCant;
	ParroquiaControl pa = new ParroquiaControl();

	public void lista_canton(String prov) {
		Canton cant = null;
		coneccion c = new coneccion();
		ResultSet rs = null;
		listCant= new ArrayList<>();

		try {
			c.iniciar_con();
			String sql = "select nombre_canton from tb_canton where cod_provincia = (select cod_provincia from tb_provincia "
					+ "where nombre_provincia='" + prov + "')";
			rs = c.Consulta(sql);
			while (rs.next()) {
				cant= new Canton(rs.getString(1));
				listCant.add(cant);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		pa.lista_parroquia(getCanton());
		System.out.println("Canton: "+ getCanton());
		c.fin_cone();
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public List<Canton> getListCant() {
		return listCant;
	}

	public void setListCant(List<Canton> listCant) {
		this.listCant = listCant;
	}
	
	
}
