<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 

<jsp:include page="includes/cabecera.jsp" >
 
<jsp:param name="pagina" value="Registro" />
 
<jsp:param name="title" value="Registrar nuevo destino" /> 
 
</jsp:include>



	<div class="container">
		
	
		<h1 class="text-primary text-center">Registro nuevo destino</h1>
		
		<!-- Pinta los mensajes si hay alguno -->
		<c:if test="${not empty requeridos}">
		
			<!-- lee los mensajes del array y los va escribiendo cada uno en etiqueta p -->
			<c:forEach items="${requeridos}" var="r">
				<p class="text-center text-danger">${r}</p>
			</c:forEach>
			
			<!-- Una vez pintado borra los mensajes -->
			<%session.removeAttribute("requeridos");%>
		</c:if>
		
		
	
	<form class="form-inline justify-content-center my-5" action="registro-ciudad" method="POST">
	  
	  <div class="form-group mx-sm-3 mb-2">
	 
	    <!-- VALUEE -> Pinta lo que se introdujo en caso de error-->
	    <input type="text" class="form-control" id="paisnuevo" name="paisnuevo" placeholder="Introduce un nuevo pais" value="${nombreIntroducido}">
	 
	  </div>
	  
	  <button type="submit" class="btn btn-primary mb-2">Confirmar registro</button>
	</form>
	
	<!-- Borra lo que se introdujo para que no se escriba si se refresca o se vuelva a esta pagina-->
	<c:if test="${not empty nombreIntroducido}">
		<%session.removeAttribute("nombreIntroducido"); %>
	</c:if>
	
	</div>
	



<jsp:include page="includes/pie.jsp"/>  