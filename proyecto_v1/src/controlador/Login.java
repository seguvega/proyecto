package controlador;

import java.io.Serializable;
import java.sql.ResultSet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;

import modelo.coneccion;
import controlador.ControladorSession;
@Named("login")
@RequestScoped

public class Login implements Serializable {

	private String correo;
	private String password;
	private String nombre;
	private String nick;
	private String cedula;
	private String pagina;
	
	public Login() {
	ver();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	
	/*--------------------*/
	
	public String ver() {
		coneccion c=new coneccion();
		ResultSet rs = null; 
		int con=0;
		String hola = null;
		
		try {
			c.iniciar_con();
			String sql="SELECT * FROM usuarios where password_u = '"+getPassword()+"' and correo = '"+getCorreo()+"'";
			rs= c.Consulta(sql);
			
			while (rs.next()) {
				setCedula(rs.getString(7));
				setNombre(rs.getString(6));
				setNick(rs.getString(4));
				con=rs.getInt(5);
				System.out.println(con);
				HttpSession sesion = ControladorSession.getSession();
				sesion.setAttribute("Correo", correo);
				return "otro.xhtml";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		password="";
		correo="";
		
		return "login.xhtml";
	}
}
