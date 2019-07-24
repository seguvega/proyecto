package controlador;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import controlador.ControladorSession;

@Named("cerrar")
@RequestScoped
public class CerrarSesion {

	public void close() {
	 HttpSession cerrar = ControladorSession.getSession();
	 System.out.println(cerrar.getAttribute("Nick"));
		cerrar.invalidate();		
		
	}
	
}
