package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import modelo.VISTA_TH;
import modelo.coneccion;

@Named("vista1")
@RequestScoped
public class Vista1 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<VISTA_TH> vm;
	private static int top=0;
	

	public Vista1() {
		// TODO Auto-generated constructor stub
	}
	
	public void generar() {
		vm=new ArrayList<>();
		coneccion c = new coneccion();
		ResultSet rs = null;
		String sql="select * from VTH_T_U_ETNIA where rownum<="+getTop();
		try {
			c.iniciar_con();
			rs=c.Consulta(sql);
			while (rs.next()) {
				vm.add(new VISTA_TH(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			
		} catch (Exception e) {
			
		}

	}
	
	public ArrayList<VISTA_TH> getVm() {
		return vm;
	}
	public void setVm(ArrayList<VISTA_TH> vm) {
		this.vm = vm;
	}

	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
}
