package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.hibernate.validator.constraints.Length;

import modelo.coneccion;

@Named("registro")
@RequestScoped

public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String identificacion; 
	private String nombre; 
	private String correo; 
	private String contrasenia; 
	private String nick;
	private String mencedula;
	private String mensajeContra;
	private String boton;
	private String nickres="";
	private boolean valinick=false;
	private String correores="";
	private boolean valicor=false;
	
	/*GET AND SET*/
	
	
	public String getIdentificacion() {
		return identificacion;
	}
	public String getBoton() {
		return boton;
	}
	public void setBoton(String boton) {
		this.boton = boton;
	}
	public String getMensajeContra() {
		return mensajeContra;
	}
	public void setMensajeContra(String mensajeContra) {
		this.mensajeContra = mensajeContra;
	}
	public String getMencedula() {
		return mencedula;
	}
	public void setMencedula(String mencedula) {
		this.mencedula = mencedula;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	/*METODOS*/

	public boolean validadorDeCedula() {
		String cedula = this.getIdentificacion();
		boolean cedulaCorrecta = false;

		try {

			if (cedula.length() == 10) // ConstantesApp.LongitudCedula
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					// Coeficientes de validación cédula
					// El decimo digito se lo considera dú„ito verificador
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			System.out.println("Una excepcion ocurrio en el proceso de validadcion");
			cedulaCorrecta = false;
		}

		if (!cedulaCorrecta) {
			System.out.println("La Cédula ingresada es Incorrecta");
		}
		return cedulaCorrecta;
	}
	//ACABA VALIDADOR DE CEDULA
	
	public void validaContra(){
		int tam=2;
		tam= this.getContrasenia().length();
		System.out.println("tamaño_: "+tam);
		if (tam <6) {
			setMensajeContra("no menos de 6 caracteres");
		}else if (tam > 15) {
			setMensajeContra("no mas de 15 caracteres");
		}else {
			setMensajeContra("");
		}
	}
	
	public String boton() {
		validaContra();
		validarnik();
		validarcorreo();
		if (this.validadorDeCedula()) {
			setMencedula("");
		} else {
			setMencedula("Cedula Incorrecta");
		}
		
		System.out.println("mensaje contra:"+ getMensajeContra());
		System.out.println("cedula: "+getMencedula());
		if (getMensajeContra()== "" && getMencedula()=="" && valinick && valicor) {
			registrar();
			return "index.xhtml";
		} 
		else {
			
			
			return "registro.xhtml";
		}
	}
	
	//email
	
	public String register() {
		
		return "index.xhtml";
	}
	
	public void validarnik() {
		
		String sql= "select nick from usuarios where nick = '"+getNick()+"'";
		coneccion c = new coneccion();
		ResultSet rs = null;
		int i=0;
		try {
			c.iniciar_con();
			rs = c.Consulta(sql);

			while (rs.next()) {
				i=1;
			}

		} catch (Exception e) {
			i=1;
		}
		
		if (i==1) {

			nickres="NICK REPETIDO";
		}else {
			valinick=true;
		}
		c.fin_cone();
	}
	 
public void validarcorreo() {
		
		String sql= "SELECT correo FROM usuarios where correo ='"+getCorreo()+"'";
		coneccion c = new coneccion();
		ResultSet rs = null;
		int i=0;
		try {
			c.iniciar_con();
			rs = c.Consulta(sql);

			while (rs.next()) {
				i=1;
			}

		} catch (Exception e) {
			i=1;
		}
		
		if (i==1) {
			
			correores="ESTE CORREO YA SE ENCUENTRA REGISTRADO";
			
		}else {
			valicor=true;
		}
		c.fin_cone();
	}

public void registrar() {
	coneccion c = new coneccion();
	ResultSet rs=null;
	String sql="insert into usuarios(CORREO,PASSWORD_U,NICK,ESTADO,NOMBRE,CEDULA,TIPOUSUARIO) VALUES ('"+getCorreo()+"','"+getContrasenia()+"','"+getNick()+"',1"
			+ ",'"+getNombre()+"','"+getIdentificacion()+"','US')";
	//System.out.println(sql);
	
	try {
		c.iniciar_con();
		c.Ejecutar(sql);
		c.Ejecutar("COMMIT");
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	c.fin_cone();
	
}

	public String getNickres() {
		return nickres;
	}
	public void setNickres(String nickres) {
		this.nickres = nickres;
	}
	
	public String getCorreores() {
		return correores;
	}
	public void setCorreores(String correores) {
		this.correores = correores;
	}
	}


