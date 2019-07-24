package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import modelo.coneccion;

@Named("madre")
@RequestScoped

public class Madre implements Serializable {

	private static final long serialVersionUID = 1L;
	private int anio;
	private String asistencia;
	private int edad;
	private String etnia;
	private List<String> etnialista;
	private int instruccion;
	private String lugar;
	private List<String> lug_ocurr;
	private int trimestre;
	private int total;
	private String sexo;
	private List<String> list_sexo;

	/* -----------------get y sets------------------------- */

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
		this.lugar = lugar;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<String> getList_sexo() {
		return list_sexo;
	}

	public void setList_sexo(List<String> list_sexo) {
		this.list_sexo = list_sexo;
	}


	/* METODOS */

	public Madre() {
		try {
			lista_lugar();
			etnia();
			Sexo();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lista_lugar() throws SQLException {

		coneccion c = new coneccion();
		lug_ocurr = new ArrayList<>();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sql = "select descripcion_lugar from d_lugar_ocurrencia";
			rs = c.Consulta(sql);

			while (rs.next()) {
				lug_ocurr.add(rs.getString(1));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		c.fin_cone();
	}

	public void etnia() throws SQLException {
	
		coneccion c = new coneccion();
		etnialista = new ArrayList<>();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sql = "select nombre_etnia from d_etnia";
			rs = c.Consulta(sql);

			while (rs.next()) {
				etnialista.add(rs.getString(1));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		c.fin_cone();
	}

	public void Sexo() {
		coneccion c = new coneccion();
		list_sexo = new ArrayList<>();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sql = "select sexo from tb_sexo";
			rs = c.Consulta(sql);

			while (rs.next()) {
				list_sexo.add(rs.getString(1));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		c.fin_cone();
	}

	public int sum() {
		Ubicacion ub = new Ubicacion();
		coneccion c = new coneccion();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sqlTotal = "SELECT count(*) from vista2 where NOMBRE_ETNIA='" + getEtnia() + "' and anio = "
					+ getAnio() + " and trimestre = " + getTrimestre() + "and nombre_provincia = '" + ub.getPro() + "'"
					+ "and nombre_canton= '" + ub.getCanton() + "'";
			rs = c.Consulta(sqlTotal);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		c.fin_cone();
		return total;
	}

	public int sexoF() {
		Ubicacion ub = new Ubicacion();
		coneccion c = new coneccion();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sqlTotal = "SELECT count(*) from vista2 where sexo='" + getSexo() + "' and anio = " + getAnio()
					+ " and trimestre = " + getTrimestre() + "and nombre_provincia = '" + ub.getPro() + "'"
					+ " and nombre_canton= '" + ub.getCanton() + "'";
			rs = c.Consulta(sqlTotal);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		c.fin_cone();
		return total;
	}

	public int sumlugar() {
		Ubicacion ub = new Ubicacion();
		coneccion c = new coneccion();
		ResultSet rs = null;
		int totallug = 0;
		try {
			c.iniciar_con();

			String sqlTotal = "select count(*) from v_consulta where descripcion_lugar = '" + getLugar()
					+ "' and anio = " + getAnio() + " and trimestre =" + getTrimestre() + " and nombre_provincia = '"
					+ ub.getPro() + "' and nombre_canton = '" + ub.getCanton() + "'";
			rs = c.Consulta(sqlTotal);
			while (rs.next()) {
				totallug = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		c.fin_cone();
		return totallug;
	}

}
