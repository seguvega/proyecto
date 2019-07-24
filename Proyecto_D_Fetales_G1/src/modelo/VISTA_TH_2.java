package modelo;

public class VISTA_TH_2 {

	
	private String tiempo;
	private String ubicacion;
	private String lugar;
	private int total;
	public VISTA_TH_2(String tiempo, String ubicacion, String lugar, int total) {
		super();
		this.tiempo = tiempo;
		this.ubicacion = ubicacion;
		this.lugar = lugar;
		this.total = total;
	}
	
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
