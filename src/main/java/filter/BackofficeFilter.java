package filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import modelo.pojo.Rol;
import modelo.pojo.Usuario;

/**
 * Servlet Filter implementation class BackofficeFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/views/backoffice/*" })
public class BackofficeFilter implements Filter {
	
	private final static Logger LOG = Logger.getLogger(BackofficeFilter.class);

    /**
     * Default constructor. 
     */
    public BackofficeFilter() {
    	LOG.trace("se destruye filtro");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.trace("se destruye filtro");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req= (HttpServletRequest) request; 
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session= req.getSession();
		
		//necesitamos la url de como comienza nuestra App, apra construir una ryta ABSOLUTA y que no sea relativa
		String urlInicioApp = req.getContextPath();
		
		Usuario u= (Usuario) session.getAttribute("loginUsuario");
		LOG.trace("filtrando URI:" + req.getRequestURI() );
		
		if (u==null) {
			LOG.warn("No ha pasado por el LOGIN, usuario NULL, SIN AUTENTIFICAR ");
			res.sendRedirect( urlInicioApp + "/views/login.jsp"); //ruta absoluta
			
		}else if ( u.getRol().getId() != Rol.ADMINISTRADOR) {
			
			LOG.warn("Intento usuario sin privilegios de ADMINISTRADOR, SIN AUTORIZACION");
			res.sendRedirect( urlInicioApp + "/views/login.jsp");
			
		}else {
			// si usuario administrador
			// dejamos pasar y continua la request
			chain.doFilter(request, response);	
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.trace("se inicializa filtro");
	}

}
