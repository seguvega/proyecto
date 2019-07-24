package modelo;

public class VISTA_TH {

	private String tiempo;
	private String ubic;
	private String Etnia;
	private int total;
	public VISTA_TH(String tiempo, String ubic, String etnia, int total) {
		super();
		this.tiempo = tiempo;
		this.ubic = ubic;
		Etnia = etnia;
		this.total = total;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public String getUbic() {
		return ubic;
	}
	public void setUbic(String ubic) {
		this.ubic = ubic;
	}
	public String getEtnia() {
		return Etnia;
	}
	public void setEtnia(String etnia) {
		Etnia = etnia;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
