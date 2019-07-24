package modelo;

public class Provincia {
private String nombProv;

public Provincia() {
	
}

public Provincia(String NomnbreProv) {
this.nombProv=NomnbreProv;
}

public String getNombProv() {
	return nombProv;
}

public void setNombProv(String nombProv) {
	this.nombProv = nombProv;
}
public String toString() {
	return nombProv;
}
}
