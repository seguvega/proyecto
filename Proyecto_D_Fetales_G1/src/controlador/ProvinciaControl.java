package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.jboss.as.controller.client.helpers.domain.AddDeploymentPlanBuilder;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import modelo.Provincia;
import modelo.coneccion;

@ManagedBean(name = "prov")
@RequestScoped
public class ProvinciaControl implements Serializable {

	private static final long serialVersionUID = 1L;
	private String provin;
	private List<Provincia> listProv;
	CantonControl ca = new CantonControl();
	
	public ProvinciaControl() {
		lista_prov();
		

	}

	public void lista_prov() {
		Provincia pr = null;
		coneccion c = new coneccion();
		ResultSet rs = null;
		listProv= new ArrayList<>(); 

		try {
			c.iniciar_con();
			String sql = "select nombre_provincia from tb_provincia";
			rs = c.Consulta(sql);
			while (rs.next()) {
				pr = new Provincia(rs.getString(1));
				listProv.add(pr);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		c.fin_cone();
		ca.lista_canton(getProvin());
		System.out.println("getprovin: "+getProvin() );

	}

	public String getProvin() {
		return provin;
	}

	public void setProvin(String provin) {
		this.provin = provin;
	}

	public List<Provincia> getListProv() {
		return listProv;
	}

	public void setListProv(List<Provincia> listProv) {
		this.listProv = listProv;
	}
	
	

}
