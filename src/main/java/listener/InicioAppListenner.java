package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import controllers.Alerta;
import modelo.DAOImp.ContinenteDAOImp;

@WebListener
public class InicioAppListenner implements ServletContextListener {

	private final static Logger LOG = Logger.getLogger(InicioAppListenner.class);
    static private final ContinenteDAOImp continentedao = ContinenteDAOImp.getInstance();
    
	
    public void contextDestroyed(ServletContextEvent sce)  { 
    	LOG.info("Apagando Servidor");
    }

    public void contextInitialized(ServletContextEvent sce)  { 
         
    	// cuando ejecutamos la App en el Servidor, al arrancar la 1º vez
    	LOG.info("Estamos arrancado la App, y soy un evento");
    	
    	// Este contexto es para toda la Aplicacion y es accesible desde cualquier JSP o Servlet    	
    	ServletContext contextoAplicacion = sce.getServletContext();
    	
    	try {
    	
    		contextoAplicacion.setAttribute("continentes", continentedao.getAll() );
    		
    	}catch (Exception e) {
    		LOG.fatal(e);
    		contextoAplicacion.setAttribute("alerta", new Alerta("danger", e.getMessage()) );
		}
    }
	
}
