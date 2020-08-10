package controllers;

import java.io.*;
import java.util.*;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Path;


import modelo.DAOImp.PaisDAOImp;
import modelo.pojo.Ciudad;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	   private boolean isMultipart;
	   
	   //TODO cambiar esta ruta, NO ES LA CORRECTA
	   private String filePath= new File("").getPath()+"/uploads/";
	  
	   
	   private int maxFileSize = 1024 * 1024;
	   private int maxMemSize = 50 * 1024;
	   private File file ;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // checks if the request actually contains upload file
		
		HttpSession session= request.getSession();
		Alerta alerta= new Alerta();
		
		Ciudad ci= (Ciudad) session.getAttribute("ciudadEditar");
		
		// Check that we have a file upload request
//	      isMultipart = ServletFileUpload.isMultipartContent(request);
	   
	      java.io.PrintWriter out = response.getWriter( );
		
		
//		if( !isMultipart ) {
//	         alerta= new Alerta("danger","error al subir el archivo.");
//	         session.setAttribute("alerta", alerta);
//	         response.sendRedirect("actualizar-ciudad?id="+ci.getId());
//	    }
//	  
//	      DiskFileItemFactory factory = new DiskFileItemFactory();
//	   
//	      // maximum size that will be stored in memory
//	      factory.setSizeThreshold(maxMemSize);
//	   
//	   
//
//	      // Create a new file upload handler
//	      ServletFileUpload upload = new ServletFileUpload(factory);
//	   
//	      // maximum file size to be uploaded.
//	      upload.setSizeMax( maxFileSize );
//
//	      try { 
//	         // Parse the request to get file items.
//	         List fileItems = upload.parseRequest(request);
//		
//	         // Process the uploaded file items
//	         Iterator i = fileItems.iterator();
//	   
//		         while ( i.hasNext () ) {
//		            FileItem fi = (FileItem)i.next();
//		            if ( !fi.isFormField () ) {
//		               // Get the uploaded file parameters
//		               String fieldName = fi.getFieldName();
//		               String fileName = fi.getName();
//		               String contentType = fi.getContentType();
//		               boolean isInMemory = fi.isInMemory();
//		               long sizeInBytes = fi.getSize();
//		            
//		               // Write the file
//		               if( fileName.lastIndexOf("\\") >= 0 ) {
//		                  file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
//		               } else {
//		                  file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
//		               }
//		               fi.write( file ) ;
//		               
//		               ci.getPais().setBandera(fileName);
//		               
//		               PaisDAOImp dao= PaisDAOImp.getInstance();
//		               dao.setBandera(ci);
//		               
//		               alerta= new Alerta("success", "Se ha subido correctamente: "+fileName);
//		               session.setAttribute("alerta", alerta);
//		               
//		            }
//		            
//	
//		         }
//	         } catch(Exception ex) {
//	        	 alerta= new Alerta("danger","error"+ex.getMessage());
//		         session.setAttribute("alerta", alerta);
//	         }finally {
//	        	 
//		         response.sendRedirect("actualizar-ciudad?id="+ci.getId());
//			}
	      }

}
