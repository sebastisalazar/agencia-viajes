
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Todas las rutas relativas comienzan por el href indicado -->
<!--  ${pageContext.request.contextPath} == http://localhost:8080/agencia-viajes master -->
<base href="${pageContext.request.contextPath}/" />

<jsp:include page="/includes/cabecera.jsp">

	<jsp:param name="pagina" value="Inicio" />

	<jsp:param name="title" value="Proximos vuelos" />

</jsp:include>

<div id="layoutSidenav">

	<jsp:include page="/includes/office-side-nav-bar.jsp" />

	<div class="container">
		<div class="row row-cols-2 border p-4">

			<div class="col-12">
				<h1 class="text-center mb-5 text-primary">Mi cuenta</h1>

			</div>

			<div class="col">

				<form action="actualizar-usuario?id=${loginUsuario.id}"
					method="POST">

					<!-- Pinta los mensajes si hay alguno -->
					<c:if test="${not empty requeridos}">

						<jsp:include page="/includes/requeridos.jsp"></jsp:include>

						<!-- Una vez pintado borra los mensajes -->
						<%
							session.removeAttribute("requeridos");
						%>
					</c:if>



					<div class="form-group col">
						<label for="email">Email:</label> 
						<input type="text"
							class="form-control" id="email" name="email"
							placeholder="${loginUsuario.email}"
							value="${(not empty passwordIntroducido)? passwordIntroducido: loginUsuario.email}">
							

					</div>

					<div class="form-group col">
						<label for="password">Password:</label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="${loginUsuario.password}"
							value="${(not empty passwordIntroducido)? passwordIntroducido: loginUsuario.password}">

					</div>

					<div class="form-group col">
						<div class="row row-cols-2">

							<div class="col-12">
								<label for="rol">Tipo:</label> <select class="form-control"
									id="rol" name="rol">

									<c:if test="${empty rolSeleccionado}">
										<option value="1" ${(loginUsuario.rol.id == 1)? 'selected':''}>Usuario</option>
										<option value="2" ${(loginUsuario.rol.id == 2)? 'selected':''}>Admin</option>

									</c:if>

									<c:if test="${not empty rolSeleccionado }">
										<option value="1" ${(rolSeleccionado eq '1')? 'selected':''}>Usuario</option>
										<option value="2" ${(rolSeleccionado eq '2')? 'selected':''}>Admin</option>
									</c:if>

								</select>

							</div>


						</div>


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
					<label class="d-block text-center">Foto de perfil</label>
					<div class="card bg-dark text-white">
						<img class="card-img ciudades-card" src="${loginUsuario.imagen}"
							alt="fotoUsuario">
						<div class="card-img-overlay">
							<p class="titulo-card">
								<small>${loginUsuario.email}</small>
							</p>

						</div>
					</div>

					<form method="post" action="UploadServlet"
						enctype="multipart/form-data">
						<label class="d-block mt-5">Actualizar foto de perfil</label> <input
							type="file" name="file" class="d-inline mt-3">
						<button type="submit" value="Upload">subir</button>
					</form>
				</div>
			</div>


		</div>

		<div class="row row-cols-2 border p-4">

			<div class="col-12">
				<h2 class="text-center mb-5 text-primary">Datos personales</h2>
			</div>

			<div class="col-6 border-right">

				<div class="form-group col">
					<div class="row row-cols-2">

						<div class="col-12">
							
							 <input type="text"
							class="form-control" id="nombre" name="nombre"
							placeholder="${loginUsuario.nombre}"
							value="${loginUsuario.nombre}">
							
							 <input type="text"
							class="form-control" id="ape1" name="ape1"
							placeholder="${loginUsuario.ape1}"
							value="${loginUsuario.ape1}">
							
							  <input type="text"
							class="form-control" id="ape1" name="ape1"
							placeholder="${loginUsuario.ape2}"
							value="${loginUsuario.ape2}">
							
							<!--   <input type="password"
							class="form-control" id="password" name="password"
							placeholder="${loginUsuario.password}"
							value="${(not empty passwordIntroducido)? passwordIntroducido: loginUsuario.password}">
							
							 <input type="password"
							class="form-control" id="password" name="password"
							placeholder="${loginUsuario.password}"
							value="${(not empty passwordIntroducido)? passwordIntroducido: loginUsuario.password}">
							
							 <input type="password"
							class="form-control" id="password" name="password"
							placeholder="${loginUsuario.password}"
							value="${(not empty passwordIntroducido)? passwordIntroducido: loginUsuario.password}">-->
							
							
							${loginUsuario.DNI_NIE}
							${loginUsuario.nacionalidad}
							${loginUsuario.residencia}
							${loginUsuario.numTarjeta}
							${loginUsuario.caducidadTarjeta}
							${loginUsuario.numseguridadTarjeta}
							${loginUsuario.titular}

						</div>


					</div>


				</div>

			</div>

		</div>


	</div>

</div>





<jsp:include page="/includes/pie.jsp" />