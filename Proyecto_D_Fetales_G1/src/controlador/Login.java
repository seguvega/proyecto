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
	private int estado;
	private String tipoUs;
	private HttpSession sesion;
	private String n;


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
		
	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String ver() {
		coneccion c = new coneccion();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sql = "SELECT * FROM usuarios where password_u = '" + getPassword() + "' and correo = '"
					+ getCorreo() + "'";
			rs = c.Consulta(sql);
			while (rs.next()) {
				estado = rs.getInt(5);
				tipoUs = rs.getString(8);
				sesion = ControladorSession.getSession();
				sesion.setAttribute("Correo", getCorreo());
				sesion.setAttribute("Nick", rs.getString(4));
				sesion.setAttribute("Nombre", rs.getString(6));
				if (estado == 1) {
					if (tipoUs.equals("ADM")) {
						return "menuAdm.xhtml";
					} else {
						 n=(String)sesion.getAttribute("Nick");
						return "otro.xhtml";///CAMBIAR A UNO BONITO --> KARLAAAAAAAAAAAAAAAA
					}
				} else if (estado == 2) {
					//String bloqueado = "Usuario Bloqueado";
					return "BloquearUs.xhtml";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login.xhtml";
	}
}
