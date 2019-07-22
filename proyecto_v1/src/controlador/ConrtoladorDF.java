package controlador;

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
public class ConrtoladorDF {

	private List<String> lista;
	private int tiempo_trim;
	
	
	public ConrtoladorDF() {
		try {
			lista_tabla();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lista_tabla() throws SQLException {
		coneccion c = new coneccion();
		lista= new ArrayList<>();
		ResultSet rs = null;
		
		try {
			c.iniciar_con();
			String sql = "select trimestre from d_tiempo";
			rs = c.Consulta(sql);
			while (rs.next()) {
				lista.add(rs.getString(1));
				System.out.println(rs.getString(1));
			}

		} catch (Exception e) {

		}
		c.fin_cone();
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	public int getTiempo_trim() {
		return tiempo_trim;
	}

	public int setTiempo_trim(int tiempo_trim) {
		return this.tiempo_trim = tiempo_trim;
	}

	
	
	

}
