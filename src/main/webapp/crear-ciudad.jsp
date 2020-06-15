<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Crear" />

	<jsp:param name="title" value="Crear ciudad" />

</jsp:include>



<div class="d-flex flex-row justify-content-center border-0  ">

	
	<jsp:include page="includes/alerta.jsp"></jsp:include>
	
	<form action="crear-ciudad" class="px-5 py-3 border pb-5" method="POST">

	<h1 class="text-primary text-center mb-5 mt-5">Registro ciudad</h1>

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
				placeholder="Introduce el nombre ..." value="${nombreIntroducidoC}">

		</div>

		<div class="form-group">
			<label for="pais">Pais: </label> <select class="form-control"
				id="pais" name="paisciudad">
				<option value="0" selected>Elige un pais ...</option>


				<c:forEach items="${selectPaises}" var="p">
					<c:if test="${not empty paisSeleccionadoC}">
						<option value="${p.id}"
							${(p.id eq paisSeleccionadoC)? 'selected':'' }>${p.nombre}</option>

					</c:if>

					<c:if test="${empty paisSeleccionadoC}">
						<option value="${p.id}">${p.nombre}</option>

					</c:if>


				</c:forEach>

			</select>
		</div>


		<div class="form-group">
			<label for="continente">Continente: </label>
			 <select class="form-control" id="continente" name="continenteciudad">
				<option value="0" selected>Elige un continente ...</option>


				<c:if test="${not empty continenteSeleccionadoC}">

					<option value="1" ${('1' eq continenteSeleccionadoC)?'selected':'' }>Africa</option>
					<option value="2" ${('2' eq continenteSeleccionadoC)?'selected':'' }>América</option>
					<option value="3" ${('3' eq continenteSeleccionadoC)?'selected':'' }>Antártida</option>
					<option value="4" ${('4' eq continenteSeleccionadoC)?'selected':'' }>Asia</option>
					<option value="5" ${('5' eq continenteSeleccionadoC)?'selected':'' }>Europa</option>
					<option value="6" ${('6' eq continenteSeleccionadoC)?'selected':'' }>Oceania</option>
				</c:if> 
				
				<c:if test="${empty continenteSeleccionadoC}">

					<option value="1">Africa</option>
					<option value="2">América</option>
					<option value="3">Antártida</option>
					<option value="4">Asia</option>
					<option value="5">Europa</option>
					<option value="6">Oceania</option>
				</c:if> 
			</select>
		</div>

		<div class="text-center" >
			<button type="submit" class="btn btn-primary mb-2 mt-5 mr-5 p-3">Registrar</button>
			<a href="listado-ciudades" class="btn btn-danger bg-danger mb-2 mt-5 p-3">Cancelar</a>
		</div>
		
	</form>


	<jsp:include page="includes/pie.jsp" />