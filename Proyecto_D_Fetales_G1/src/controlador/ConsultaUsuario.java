package controlador;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import modelo.coneccion;
@Named ("consulta")
@RequestScoped
public class ConsultaUsuario {
	
	private String nombre; 
	private String cedula;
	private String nick; 
	private String correo; 
	private String tipo;
	private String estado;
	private List <String> nombres;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public List<String> getNombres() {
		return nombres;
	}
	public void setNombres(List<String> nombres) {
		this.nombres = nombres;
	}
	
	public ConsultaUsuario() {
		try {
			consultar();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void consultar() {
		coneccion c = new coneccion();
		nombres=new ArrayList<>();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sqlnombre="select nombre from usuarios";
			rs=c.Consulta(sqlnombre);
			while (rs.next()) {
				nombres.add(rs.getString(1));
				System.out.println(nombres);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		c.fin_cone();
	}
	
	
	

}
