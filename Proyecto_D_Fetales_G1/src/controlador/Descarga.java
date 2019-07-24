package controlador;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xhtmlrenderer.pdf.ITextRenderer;

@Named("descarga")
@RequestScoped

public class Descarga implements Serializable{
	public Descarga() {
		
	}
	
	public void download() {
	FacesContext faces = FacesContext.getCurrentInstance();
	ExternalContext external = faces.getExternalContext();
	HttpSession session= (HttpSession) external.getSession(true);
	String url="http://localhost:8080/D_Fetales_G1/faces/menuAdm.xhtml;jsessionid="+session.getId()+"?pdf=true";
	try {
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(new URL(url).toString());
		renderer.layout();
		HttpServletResponse response =(HttpServletResponse) external.getResponse();
		response.reset();
		response.setContentType("menuAdm.xhtml");
		response.setHeader("Content-Disposition", "inline; filename=\"menuAdm.xhtml\"");
		OutputStream outputStream = response.getOutputStream();
		renderer.createPDF(outputStream);
} catch (Exception e) {
		// TODO: handle exception
	e.printStackTrace();
	}
	faces.responseComplete();
	
	}
}
