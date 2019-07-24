package modelo;

public class Parroquia {
	private String nombreParroquia;

	public Parroquia() {
		// TODO Auto-generated constructor stub
	}

	public Parroquia(String nomParro) {
		this.nombreParroquia = nomParro;
	}

	public String getNombreParroquia() {
		return nombreParroquia;
	}

	public void setNombreParroquia(String nombreParroquia) {
		this.nombreParroquia = nombreParroquia;
	}

	
public String toString () {
	return nombreParroquia;
}
}
