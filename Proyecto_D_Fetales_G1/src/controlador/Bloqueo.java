package controlador;

import java.io.Serializable;
import java.sql.ResultSet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import modelo.Usuarios;
import modelo.coneccion;

@Named("blok")
@RequestScoped

public class Bloqueo implements Serializable {

	private int opcion;
	private int bloquear;
	private int eliminar;
	private String mensaje;
	private Lista_Usuarios us = new Lista_Usuarios();

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	public Lista_Usuarios getUs() {
		return us;
	}

	public void setUs(Lista_Usuarios us) {
		this.us = us;
	}

	public int getBloquear() {
		return bloquear;
	}

	public void setBloquear(int bloquear) {
		this.bloquear = bloquear;
	}

	public int getEliminar() {
		return eliminar;
	}

	public void setEliminar(int eliminar) {
		this.eliminar = eliminar;
	}

	public void accion(Usuarios us) {
		coneccion c = new coneccion();
		ResultSet rs = null;
		System.out.println("Correo: " + us.getCorreo());
		System.out.println("estado: " + us.getEstado());
		if (us.getEstado().equals("ACTIVO")) {

			try {
				c.iniciar_con();
				String sqlbloc = "update usuarios set estado = 2 where correo='" + us.getCorreo() + "'";
				rs = c.Consulta(sqlbloc);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			c.fin_cone();
		} else {
			try {
				c.iniciar_con();
				String sqlbloc = "update usuarios set estado = 1 where correo='" + us.getCorreo() + "'";
				rs = c.Consulta(sqlbloc);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			c.fin_cone();
		}

	}

	public void eliminar(Usuarios us) {
		coneccion c = new coneccion();
		ResultSet rs = null;
		try {
			c.iniciar_con();
			String sqlbloc = "delete from usuarios where correo='" + us.getCorreo() + "'";
			rs = c.Consulta(sqlbloc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		c.fin_cone();

	}
}
