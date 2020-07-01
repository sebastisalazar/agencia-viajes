<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Si se intenta entrar siendo un usuario normal, es decir NO ADMIN, redigirá -->
<c:if test="${not empty loginUsuario}">
         <c:if test = "${loginUsuario.rol.id == 1}">
            <c:redirect url="inicio"></c:redirect>
      	</c:if>
	
</c:if>

<!-- Si se intenta acceder sin logearse redigirá -->
<c:if test="${empty loginUsuario}">
	<c:redirect url="login.jsp"></c:redirect>
</c:if>

<!-- Si se intenta entrar sin un id por parametro redigirá -->
<c:if test="${empty param.id}">
	<c:redirect url="listado-usuarios"></c:redirect>
</c:if>

<!-- Si pasamos mucho tiempo interacturar en esta pagina, la session borrara el atributo ciudad editar, por lo tanto vuelve a recuperar los datos cuando eso pase -->
<c:if test="${empty usuarioEditar}">
	<c:redirect url="actualizar-usuario?id=${param.id}"></c:redirect>
</c:if>


<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Actualizar usuario" />

	<jsp:param name="title" value="Actualizar usuario" />

</jsp:include>

<jsp:include page="includes/alerta.jsp"></jsp:include>
<div class="container">
	<div class="row row-cols-2 border p-4">

		<div class="col-12">
			<h1 class="text-center mb-5 text-primary">Actualizar Usuario</h1>

		</div>

		<div class="col">

			<form action="actualizar-usuario?id=${usuarioEditar.id}"
				method="POST">

				<!-- Pinta los mensajes si hay alguno -->
				<c:if test="${not empty requeridos}">

					<jsp:include page="includes/requeridos.jsp"></jsp:include>

					<!-- Una vez pintado borra los mensajes -->
					<%
						session.removeAttribute("requeridos");
					%>
				</c:if>



				<div class="form-group col">
					<label for="email">Email:</label> <input type="text"
						class="form-control" id="email" name="email"
						placeholder="${usuarioEditar.email}"
						value="${(not empty emailIntroducido)? emailIntroducido: usuarioEditar.email}">

				</div>
				
				<div class="form-group col">
					<label for="password">Password:</label> 
					<input type="password"
						class="form-control" id="password" name="password"
						placeholder="${usuarioEditar.password}"
						value="${(not empty passwordIntroducido)? passwordIntroducido: usuarioEditar.password}">

				</div>

				<div class="form-group col">
					<div class="row row-cols-2">

						<div class="col-12">
							<label for="rol">Tipo:</label> 
							<select class="form-control" id="rol" name="rol">

									<c:if test="${empty rolSeleccionado}">
										<option value="1" 
											${(usuarioEditar.rol.id == 1)? 'selected':''}>Usuario</option>
										<option value="2" 
											${(usuarioEditar.rol.id == 2)? 'selected':''}>Admin</option>

									</c:if>

									<c:if test="${not empty rolSeleccionado }">
										<option value="1" 
											${(rolSeleccionado eq '1')? 'selected':''}>Usuario</option>
										<option value="2" 
											${(rolSeleccionado eq '2')? 'selected':''}>Admin</option>
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
					<img class="card-img ciudades-card" src="${usuarioEditar.imagen}"
						alt="fotoUsuario">
					<div class="card-img-overlay">
						<p class="titulo-card">
							<small>${usuarioEditar.email}</small>
						</p>

					</div>
				</div>
				
				<form method="post" action="UploadServlet"
					enctype="multipart/form-data">
					<label class="d-block mt-5">Actualizar foto de perfil</label>
					 <input type="file"
						name="file" class="d-inline mt-3">
					<button type="submit" value="Upload">subir</button>
				</form>
			</div>
		</div>


	</div>
</div>

<jsp:include page="includes/pie.jsp" />