package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.Flushable;
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

@Named("desc")
@RequestScoped

public class descarcga1 implements Serializable{
	
	public void download() {
		try {
			
		
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.responseReset();
			externalContext.setResponseContentType("menuAdm/pdf");
			externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"menuAdm.dpf\"");
			FileInputStream inputstream = new FileInputStream(new File("/Users/HP/Desktop/Des_eclipse"));
			OutputStream outputStream=externalContext.getResponseOutputStream();
		
			byte [] buffer = new byte [4000];
			int length; 
			while ((length = inputstream.read(buffer))>0) {
				outputStream.write(buffer,0,length);
			}
			((Flushable) inputstream).flush();
			outputStream.close();
			context.responseComplete();
			
			
		} catch (Exception e) {
			
		}
	}

}
