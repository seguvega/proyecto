package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


import modelo.VISTA_TH_2;
import modelo.coneccion;

@Named("vista2")
@RequestScoped
public class Vista2 implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<VISTA_TH_2> vm;
	private static int top=0;
	

	public Vista2() {
		// TODO Auto-generated constructor stub
	}
	
	public void generar() {
		vm=new ArrayList<>();
		coneccion c = new coneccion();
		ResultSet rs = null;
		String sql="select * from V_TH_AN_PROV_LUGAR where ROWNUM<="+getTop();
		try {
			c.iniciar_con();
			rs=c.Consulta(sql);
			while (rs.next()) {
				vm.add(new VISTA_TH_2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			
		} catch (Exception e) {
			
		}
		c.fin_cone();

	}
	
	public ArrayList<VISTA_TH_2> getVm() {
		return vm;
	}
	public void setVm(ArrayList<VISTA_TH_2> vm) {
		this.vm = vm;
	}

	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
}

