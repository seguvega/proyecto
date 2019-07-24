package modelo;

public class Canton {
	private String nombreCanton;

	public Canton() {

	}

	public Canton(String nomCanton) {
		this.nombreCanton = nomCanton;
	}

	public String getNombreCanton() {
		return nombreCanton;
	}

	public void setNombreCanton(String nombreCanton) {
		this.nombreCanton = nombreCanton;
	}

	public String toString () {
		return nombreCanton;
	}
}
