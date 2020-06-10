<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 

<jsp:include page="includes/cabecera.jsp" >
 
<jsp:param name="pagina" value="Registro" />
 
<jsp:param name="title" value="Registrar ciudad" /> 
 
</jsp:include>



	<div class="container">
		
	
		<h1 class="text-primary text-center">Registro ciudad</h1>
		
		<!-- Pinta los mensajes si hay alguno -->
		<c:if test="${not empty requeridos}">
		
			<!-- lee los mensajes del array y los va escribiendo cada uno en etiqueta p -->
			<c:forEach items="${requeridos}" var="r">
				<p class="text-center text-danger">${r}</p>
			</c:forEach>
			
			<!-- Una vez pintado borra los mensajes -->
			<%session.removeAttribute("requeridos");%>
		</c:if>
		
		
	
	<form>
	  <div class="form-group">
	    <label for="nombreciudad">Nombre ciudad:</label>
	    <input type="text" class="form-control" id="nombreciudad" placeholder="Valencia">
	    
	  </div>
	  
	  <div class="form-group">
	    <label for="pais">Pais: </label>
	    <select class="form-control" id="pais" name="pais">
	    
	    <c:forEach items="${selectPaises}" var="p">
	    	<option value="${p.id}">${p.nombre}</option>
	    </c:forEach>
	    
	    </select>
	  </div>
	  <div class="form-group">
	    <label for="continente">Continente: </label>
	    <select class="form-control" id="continente" name="continente">
	      <c:forEach items="${selectContinentes}" var="c">
	    	<option value="${c.id}">${c.nombre}</option>
	    </c:forEach>
	    </select>
	  </div>
	 
	  <button type="submit" class="btn btn-primary mb-2">Registrar</button>
	</form>
	
	
	



<jsp:include page="includes/pie.jsp"/>  