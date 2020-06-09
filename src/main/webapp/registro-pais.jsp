<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 

<jsp:include page="includes/cabecera.jsp" >
 
<jsp:param name="pagina" value="Registro" />
 
<jsp:param name="title" value="Registrar nuevo destino" /> 
 
</jsp:include>



	<div class="container">
		
	
		<h1 class="text-primary text-center">Registro nuevo destino</h1>
		<c:if test="${not empty requeridos}">
		
			<c:forEach items="${requeridos}" var="r">
				<p class="text-center text-danger">${r}</p>
			</c:forEach>
			
			<%session.setAttribute("requeridos",null);%>
		</c:if>
	
	<form class="form-inline justify-content-center my-5" action="registro-pais" method="POST">
	  
	  <div class="form-group mx-sm-3 mb-2">
	  
	  	
		
	    <label for="paisnuevo" class="sr-only">Nombre del pais destino</label>
	    <input type="text" class="form-control" id="paisnuevo" name="paisnuevo" placeholder="Australia">
	  </div>
	  <button type="submit" class="btn btn-primary mb-2">Confirmar registro</button>
	</form>
	
	</div>
	



<jsp:include page="includes/pie.jsp"/>  