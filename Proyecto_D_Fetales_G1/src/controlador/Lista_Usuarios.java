package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import modelo.Usuarios;
import modelo.coneccion;

@Named("usuarios")
@ViewScoped
public class Lista_Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	private static ArrayList<Usuarios> user = null;

	public Lista_Usuarios() {

			user = new ArrayList<>();
	
		generar_lista();

	}

	public void generar_lista() {
		coneccion c = new coneccion();
		ResultSet rs = null;
		String sql="select id_usuario,nombre,correo,estado,nick,TIPOUSUARIO from usuarios";
		try {
			c.iniciar_con();
			rs=c.Consulta(sql);
			String estado;
			String tipo="";
			while (rs.next()) {
				if (rs.getInt(4)==1) {
					 estado="ACTIVO";
				}else {
					estado="BLOQUEADO";
				}
				if (rs.getString(6).equals("US")) {
					tipo="USUARIO";
				}else {
					
					tipo="ADMINISTRADOR";
				}
				
				user.add(new Usuarios(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), estado, rs.getInt(4), tipo));
			}
		} catch (Exception e) {
			c.fin_cone();;
		}
		c.fin_cone();;
	}
	
	public ArrayList<Usuarios> getUser() {
		return user;
	}
	public void setUser(ArrayList<Usuarios> user) {
		this.user = user;
	}

}
