<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Actualizar" />

	<jsp:param name="title" value="Actualizar ciudad" />

</jsp:include>



<div class="container">

	<jsp:include page="includes/alerta.jsp"></jsp:include>

	<h1 class="text-primary text-center">Actualizar ciudad</h1>

	<form action="actualizar-ciudad?id=${ciudadEditar.id}" method="POST">


		<!-- Pinta los mensajes si hay alguno -->
		<c:if test="${not empty requeridos}">

			<jsp:include page="includes/requeridos.jsp"></jsp:include>

			<!-- Una vez pintado borra los mensajes -->
			<%
				session.removeAttribute("requeridos");
			%>
		</c:if>


		<div class="form-group">
			<label for="nombreciudad">Nombre ciudad:</label> <input type="text"
				class="form-control" id="nombreciudad" name="nombreciudad"
				placeholder="Valencia" value="${ciudadEditar.nombre}">

		</div>

		<div class="form-group">
			<label for="pais">Pais: </label> <select class="form-control"
				id="pais" name="paisciudad">
				<option value="0" selected>Elige un pais ...</option>

				<c:forEach items="${selectPaises}" var="p">
					<option value="${p.id}"
						${(p.id eq ciudadEditar.pais.id)? 'selected':'' }>${p.nombre}</option>
				</c:forEach>

			</select>
		</div>


		<div class="form-group">
			<label for="continente">Continente: </label> <select
				class="form-control" id="continente" name="continenteciudad">
				<option value="0" selected>Elige un continente ...</option>
				<c:forEach items="${selectContinentes}" var="c">
					<option value="${c.id}"
						${(c.id eq ciudadEditar.continente.id)? 'selected':'' }>${c.nombre}</option>
				</c:forEach>
			</select>
		</div>

		<button type="submit" class="btn btn-primary mb-2">Actualizar</button>
	</form>


	<jsp:include page="includes/pie.jsp" />