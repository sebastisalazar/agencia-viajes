<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 

<base href="${pageContext.request.contextPath}/" />

<c:if test="${not empty loginUsuario}">
	<c:redirect url="/login"/>
</c:if>

<jsp:include page="/includes/cabecera.jsp">

	<jsp:param name="pagina" value="Iniciar sesion" />

	<jsp:param name="title" value="Iniciar sesion" />

</jsp:include>




<div class="d-flex flex-row justify-content-center border-0  ">
	
	
	<form class="px-5 py-3 border pb-5" action="login" method="post">
		<h1 class="text-primary text-center mb-3 mt-5">Iniciar sesión</h1>
		
		<!-- Pinta los mensajes si hay alguno -->
		<c:if test="${not empty requeridos}">

			<jsp:include page="/includes/requeridos.jsp"></jsp:include>

			<!-- Una vez pintado borra los mensajes -->
			<%
				session.removeAttribute("requeridos");
			%>
		</c:if>
		
		
		<div class="form-group pt-2">
			<label for="email">Email</label> <input
				type="email" class="form-control px-5" id="email" name="email" value="${loginEmailErroneo}">
		</div>
		<div class="form-group ">
			<label for="password">Password</label> <input
				type="password" class="form-control px-5" id="password" name="password" value="">
		</div>
		<div class="form-group form-check">
			<input type="checkbox" class="form-check-input" id="recordar" name="recordar">
			<label class="form-check-label" for="recordar" >Recordar sesión</label>
			
			<div class="d-flex justify-content-around my-4">
				<small class="font-weight-bold"><a href="crear-usuario">Registrate</a></small>
				<small class="font-weight-bold"><a href="#">Recuperar contraseña</a></small>
			</div>
			 
		</div>
		
		<div class="d-flex justify-content-center">
			   <button type="submit" class="btn btn-primary px-5">Entrar</button>
		</div>
	</form>

</div>



<jsp:include page="/includes/pie.jsp" />

