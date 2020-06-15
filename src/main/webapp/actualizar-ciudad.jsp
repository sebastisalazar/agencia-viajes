<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Si se intenta entrar sin un id por parametro redigirá -->
<c:if test="${empty param.id}">
	<c:redirect url="listado-ciudades"></c:redirect>
</c:if>


<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Actualizar" />

	<jsp:param name="title" value="Actualizar ciudad" />

</jsp:include>



<div class="d-flex flex-column justify-content-center border-0  ">

	<jsp:include page="includes/alerta.jsp"></jsp:include>

	<form action="actualizar-ciudad?id=${ciudadEditar.id}"
		class="px-5 py-3 border pb-5 row" method="POST">

		
		<div class="col-12">
			<h1 class="text-primary text-center mb-3 mt-3">Actualizar ciudad</h1>
		</div>
		
		<div class="col-12 mt-3">
			<!-- Pinta los mensajes si hay alguno -->
			<c:if test="${not empty requeridos}">

				<jsp:include page="includes/requeridos.jsp"></jsp:include>

				<!-- Una vez pintado borra los mensajes -->
				<%
					session.removeAttribute("requeridos");
				%>
			</c:if>
			
		</div>

		<div class="col">
			
			<div class="form-group col">
				<label for="nombreciudad">Nombre ciudad:</label> <input type="text"
					class="form-control" id="nombreciudad" name="nombreciudad"
					placeholder="${ciudadEditar.nombre}"
					value="${(not empty nombreIntroducido)? nombreIntroducido: ciudadEditar.nombre}">

			</div>
			
			
			

			<div class="form-group col">
				<label for="pais">Pais: </label> <select class="form-control"
					id="pais" name="paisciudad">

					<c:forEach items="${selectPaises}" var="p">


						<c:if test="${empty paisSeleccionado }">
							<option value="${p.id}"
								${(p.id eq ciudadEditar.pais.id)? 'selected':'' }>${p.nombre}</option>
						</c:if>

						<c:if test="${not empty paisSeleccionado }">
							<option value="${p.id}"
								${(p.id eq paisSeleccionado)? 'selected':'' }>${p.nombre}</option>
						</c:if>


					</c:forEach>

				</select>
			</div>


			<div class="form-group col">
				<label for="continente">Continente: </label> <select
					class="form-control" id="continente" name="continenteciudad">

					<c:if test="${empty continenteSeleccionado }">
						<option value="1"
							${('1' eq ciudadEditar.continente.id)? 'selected':'' }>Africa</option>
						<option value="2"
							${('2' eq ciudadEditar.continente.id)? 'selected':'' }>América</option>
						<option value="3"
							${('3' eq ciudadEditar.continente.id)? 'selected':'' }>Antártida</option>
						<option value="4"
							${('4' eq ciudadEditar.continente.id)? 'selected':'' }>Asia</option>
						<option value="5"
							${('5' eq ciudadEditar.continente.id)? 'selected':'' }>Europa</option>
						<option value="6"
							${('6' eq ciudadEditar.continente.id)? 'selected':'' }>Oceania</option>

					</c:if>

					<c:if test="${not empty continenteSeleccionado }">
						<option value="1"
							${('1' eq continenteSeleccionado)? 'selected':'' }>Africa</option>
						<option value="2"
							${('2' eq continenteSeleccionado)? 'selected':'' }>América</option>
						<option value="3"
							${('3' eq continenteSeleccionado)? 'selected':'' }>Antártida</option>
						<option value="4"
							${('4' eq continenteSeleccionado)? 'selected':'' }>Asia</option>
						<option value="5"
							${('5' eq continenteSeleccionado)? 'selected':'' }>Europa</option>
						<option value="6"
							${('6' eq continenteSeleccionado)? 'selected':'' }>Oceania</option>

					</c:if>




				</select>
			</div><!-- div selec continente -->

			</div><!-- div de todo rodos los botones del form -->
			
			<div class="col text-center ">
				<img alt="Bandera" src="${ciudadEditar.pais.bandera}" class="rounded">
				
				<input type="file" name="bandera" class="d-inline mt-3" >
				
			</div>
			
			<div class="col-12 d-flex justify-content-center">
				<button type="submit" class="btn btn-primary mb-2 mt-5 mr-5 p-3">Actualizar</button>
				
				<a href="listado-ciudades" class="btn btn-danger bg-danger mb-2 mt-5 p-3">Cancelar</a>
			</div>
			
			
			
			
	</form>
</div>


<jsp:include page="includes/pie.jsp" />