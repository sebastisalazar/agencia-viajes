<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Si se intenta entrar siendo un usuario normal, es decir NO ADMIN, redigirá -->
<c:if test="${not empty loginUsuario}">
         <c:if test = "${loginUsuario.rol.id == 1}">
            <c:redirect url="inicio"></c:redirect>
      	</c:if>
	
</c:if>

<!-- Si se intenta acceder sin haberse logeado redigira a login -->
<c:if test="${empty loginUsuario}">
      <c:redirect url="login.jsp"></c:redirect>
</c:if>

<!-- Si el array select esta vacio llama de nuevo al crear-ciudad para recuperarlo -->
<c:if test="${empty selectPaises}">
	<c:redirect url="crear-ciudad" />
</c:if>

<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Registro Ciudad" />

	<jsp:param name="title" value="Crear ciudad" />

</jsp:include>



<div class="d-flex flex-row justify-content-center border-0  ">


	<jsp:include page="includes/alerta.jsp"></jsp:include>

	<form action="crear-ciudad"
		class="px-5 py-3 border pb-5 formulario-crear" method="POST">


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



		<div class="row row-cols-2">
			<div class="col">
				<div class="form-group">
					<label for="pais">Pais: </label> <select class="form-control"
						id="pais" name="paisciudad">
						<c:forEach items="${selectPaises}" var="p">

							<c:if test="${not empty paisSeleccionadoC}">
								<option value="${p.id},${p.nombrecorto}"
									${(p.id eq paisSeleccionadoC)? 'selected':'' }>${p.nombre}</option>

							</c:if>

							<c:if test="${empty paisSeleccionadoC}">

								<c:choose>

									<c:when test="${'1' eq p.id}">
							            <option value="${p.id},${p.nombrecorto}" selected>${p.nombre}</option>
	
							         </c:when>
							
									<c:otherwise>
							            <option value="${p.id},${p.nombrecorto}" >${p.nombre}</option>
							         </c:otherwise>
							         
								</c:choose>

							</c:if>
						</c:forEach>

					</select>
				</div>
			</div>

			<div class="col">
				<label for="pais">Bandera: </label>
				<div class="country d-block text-center"
					style="margin: 0px; padding: 8px 6px">
					<i></i> <b></b>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="continente">Continente: </label> <select
				class="form-control" id="continente" name="continenteciudad">
				<option value="0" selected>Elige un continente ...</option>


				<c:if test="${not empty continenteSeleccionadoC}">

					<option value="1"
						${('1' eq continenteSeleccionadoC)?'selected':'' }>Africa</option>
					<option value="2"
						${('2' eq continenteSeleccionadoC)?'selected':'' }>América</option>
					<option value="3"
						${('3' eq continenteSeleccionadoC)?'selected':'' }>Antártida</option>
					<option value="4"
						${('4' eq continenteSeleccionadoC)?'selected':'' }>Asia</option>
					<option value="5"
						${('5' eq continenteSeleccionadoC)?'selected':'' }>Europa</option>
					<option value="6"
						${('6' eq continenteSeleccionadoC)?'selected':'' }>Oceania</option>
				</c:if>

				<c:if test="${empty continenteSeleccionadoC}">

					<option value="1">Africa</option>
					<option value="2">América</option>
					<option value="3">Antártida</option>
					<option value="4">Asia</option>
					<option value="5" selected>Europa</option>
					<option value="6">Oceania</option>
				</c:if>

			</select>

		</div>

		<div class="text-center">
			<button type="submit" class="btn btn-primary mb-2 mt-5 mr-5 p-3">Registrar</button>
			<a href="listado-ciudades"
				class="btn btn-danger bg-danger mb-2 mt-5 p-3">Cancelar</a>
		</div>

	</form>

</div>


<jsp:include page="includes/pie.jsp" />