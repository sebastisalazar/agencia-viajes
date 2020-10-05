
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
						<label for="email">Email:</label> <input type="text"
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

					<div class="accordion" id="accordionExample">
						<div class="card caja-contrasenia">
							<div class="card-header btn-contrasenia" id="headingOne">
								<h2 class="mb-0">
									<button class="btn btn-link btn-block text-center collapsed"
										type="button" data-toggle="collapse"
										data-target="#collapseOne" aria-expanded="false"
										aria-controls="collapseOne">Cambiar contraseña</button>
								</h2>
							</div>

							<div id="collapseOne" class="collapse"
								aria-labelledby="headingOne" data-parent="#accordionExample">
								<div class="card-body">

									<label for="password1">Escribe la nueva contraseña: </label><input
										class="text-center" id="password1" type="password"
										name="password1" oninput="Iguales()"> <br> <br>
									<label for="password2">Rescribe la nueva contraseña: </label>
									
									<input
										class="text-center" id="password2" type="password"
										name="password2" oninput="Iguales()"> 
									<input
										class="text-center" id="password1copia" type="hidden"
										name="password1copia"> 
									<input class="text-center"
										id="password2copia" type="hidden" name="password2copia">
									
									<small id="mensaje" class="text-danger d-block"
										style="visibility: hidden">Las contraseñas no
										coinciden</small>
								</div>
							</div>
						</div>

					</div>


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

			<div class="col border-right">

				<div class="form-group col">

					<div class="col-12">
						<h2 class="text-center mb-5 text-primary">Datos personales</h2>
					</div>

					<div class="col-12">

						<label class="d-block mt-2" for="nombre">Nombre</label> <input
							type="text" class="form-control" id="nombre" name="nombre"
							placeholder="${loginUsuario.nombre}"
							value="${loginUsuario.nombre}"> <label
							class="d-block mt-2" for="nombre">Primer Apellido</label> <input
							type="text" class="form-control" id="ape1" name="ape1"
							placeholder="${loginUsuario.ape1}" value="${loginUsuario.ape1}">

						<label class="d-block mt-2" for="nombre">Segundo Apellido</label>
						<input type="text" class="form-control" id="ape2" name="ape2"
							placeholder="${loginUsuario.ape2}" value="${loginUsuario.ape2}">

						<label class="d-block mt-2" for="nombre">DNI/NIE</label> <input
							type="text" class="form-control" id="dni_nie" name="dni_nie"
							placeholder="${loginUsuario.DNI_NIE}"
							value="${loginUsuario.DNI_NIE}">

						<div class="row row-cols-2  ">

							<div class="col-12">
								<label class="d-block mt-2" for="nombre">Nacionalidad</label>
							</div>
							<div class="col">

								<input type="text" class="form-control" id="pais"
									name="nacionalidad" placeholder="${loginUsuario.nacionalidad}"
									value="${loginUsuario.nacionalidad}">
							</div>

							<div class="col">
								<div class="country d-block text-center"
									style="margin: 0px; padding: 8px 6px">
									<i></i> <b></b>
								</div>
							</div>

						</div>


						<label class="d-block mt-2" for="nombre">Residencia</label> <input
							type="text" class="form-control" id="residencia"
							name="residencia" placeholder="${loginUsuario.residencia}"
							value="${loginUsuario.residencia}">

					</div>

				</div>

			</div>

			<div class="col">

				<div class="form-group col">

					<div class="col-12">
						<h2 class="text-center mb-5 text-primary">Datos de pago</h2>
					</div>

					<div class="col-12">

						<label class="d-block mt-2" for="numTarjeta">Tarjeta de
							credito</label> <input type="text" class="form-control" id="numTarjeta"
							name="numTarjeta" placeholder="${loginUsuario.numTarjeta}"
							value="${loginUsuario.numTarjeta}">

					</div>

					<div class="col-12">

						<label class="d-block mt-2" for="numTarjeta">Caducidad</label> <input
							type="text" class="form-control" id="numTarjeta"
							name="numTarjeta" placeholder="${loginUsuario.caducidadTarjeta}"
							value="${loginUsuario.caducidadTarjeta}">

					</div>

					<div class="col-12">

						<label class="d-block mt-2" for="numTarjeta">Numero se
							seguridad</label> <input type="text" class="form-control" id="numTarjeta"
							name="numTarjeta"
							placeholder="${loginUsuario.numseguridadTarjeta}"
							value="${loginUsuario.numseguridadTarjeta}">

					</div>


					<div class="col-12">

						<label class="d-block mt-2" for="titular">Titular</label> <input
							type="text" class="form-control" id="numTarjeta"
							name="numTarjeta" placeholder="${loginUsuario.titular}"
							value="${loginUsuario.titular}">

					</div>

				</div>


			</div>



		</div>


	</div>

</div>





<jsp:include page="/includes/pie.jsp" />