<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Si se intenta entrar sin un id por parametro redigirá -->
<c:if test="${empty param.id}">
	<c:redirect url="listado-ciudades"></c:redirect>
</c:if>

<!-- Si pasamos mucho tiempo interacturar en esta pagina, la session borrara el atributo ciudad editar, por lo tanto vuelve a recuperar los datos cuando eso pase -->
<c:if test="${empty ciudadEditar}">
	<c:redirect url="actualizar-ciudad?id=${param.id}"></c:redirect>
</c:if>


<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Actualizar" />

	<jsp:param name="title" value="Actualizar ciudad" />

</jsp:include>





	<jsp:include page="includes/alerta.jsp"></jsp:include>
<div class="container">
	<div class="row row-cols-2 border p-4">

			<div class="col-12">
				<h1 class="text-center mb-5 text-primary">Actualizar ciudad</h1>

			</div>

			<div class="col">

				<form action="actualizar-ciudad?id=${ciudadEditar.id}"
					class="" method="POST"
					>

						
					
						<!-- Pinta los mensajes si hay alguno -->
						<c:if test="${not empty requeridos}">

							<jsp:include page="includes/requeridos.jsp"></jsp:include>

							<!-- Una vez pintado borra los mensajes -->
							<%
								session.removeAttribute("requeridos");
							%>
						</c:if>

					

						<div class="form-group col">
							<label for="nombreciudad">Nombre ciudad:</label> 
							
							
							<input type="text"
								class="form-control" id="nombreciudad" name="nombreciudad"
								placeholder="${ciudadEditar.nombre}"
								value="${(not empty nombreIntroducido)? nombreIntroducido: ciudadEditar.nombre}">

						</div>




						<div class="form-group col">
							<label for="pais">Pais: </label> 
							<select class="form-control" id="pais" name="paisciudad">

							
								<c:forEach items="${selectPaises}" var="p">


									<c:if test="${empty paisSeleccionado}">
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
						</div>
						<!-- div selec continente -->
						
						<div class="col-12 d-flex justify-content-center">
							<button type="submit" class="btn btn-primary mb-2 mt-5 mr-5 p-3">Actualizar</button>

							<a href="listado-ciudades"
							class="btn btn-danger bg-danger mb-2 mt-5 p-3">Cancelar</a>
						</div>
					

				</form>
						
						

			</div>
			
			<!--Contiene todo el form para SOLO actualizar el la foto-->
			<div class="col border-left">
				<div class="text-center ">
				
					<img alt="Bandera" src="${ciudadEditar.pais.bandera}" class="rounded">

					<form method="post" action="UploadServlet" enctype="multipart/form-data" >
						<small class="d-block">Imagen actual</small>
						<input type="file" name="file" class="d-inline mt-3">
						<button type="submit" value="Upload">subir</button>
					</form>
				</div>
			</div>
			

	</div>
</div>

<jsp:include page="includes/pie.jsp" />