package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import controllers.Alerta;
import modelo.DAOImp.ContinenteDAOImp;






@WebListener
public class InicioAppListenner implements ServletContextListener {

	
    static private final ContinenteDAOImp continentedao = ContinenteDAOImp.getInstance();
    
    public InicioAppListenner() {
        // TODO Auto-generated constructor stub
    }

	
    public void contextDestroyed(ServletContextEvent sce)  { 
        
    }

    public void contextInitialized(ServletContextEvent sce)  { 
         
    	// Este contexto es para toda la Aplicacion y es accesible desde cualquier JSP o Servlet    	
    	ServletContext contextoAplicacion = sce.getServletContext();
    	
    	try {
    	
    		contextoAplicacion.setAttribute("continentes", continentedao.getAll() );
    		
    	}catch (Exception e) {
    		//LOG.fatal(e);
    		contextoAplicacion.setAttribute("alerta", new Alerta("danger", e.getMessage()) );
		}
    }
	
}
