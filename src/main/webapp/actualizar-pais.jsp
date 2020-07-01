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
	<c:redirect url="inicio"></c:redirect>
</c:if>

<!-- Si pasamos mucho tiempo interacturar en esta pagina, la session borrara el atributo ciudad editar, por lo tanto vuelve a recuperar los datos cuando eso pase -->
<c:if test="${empty paisEditar}">
	<c:redirect url="actualizar-pais?id=${param.id}"></c:redirect>
</c:if>


<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Actualizar pais" />

	<jsp:param name="title" value="Actualizar pais" />

</jsp:include>

<jsp:include page="includes/alerta.jsp"></jsp:include>
<div class="container">
	<div class="row row-cols-2 border p-4">

		<div class="col-12">
			<h1 class="text-center mb-5 text-primary">Actualizar Pais</h1>

		</div>

		<div class="col">

			<form action="actualizar-pais?id=${paisEditar.id}"
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
					<label for="nombrepais">Nombre pais:</label> 
					<input type="text"
						class="form-control" id="nombrepais" name="nombrepais"
						placeholder="${paisEditar.nombre}"
						value="${(not empty nombreIntroducido)? nombreIntroducido: paisEditar.nombre}">

				</div>

				<div class="form-group col">
					<div class="row row-cols-2">

						<div class="col-12">
							<label for="pais" class="d-block">ISO 3166 / CODIGO PAIS: </label>
							<input type="text"
						class="form-control" id="codigopais" name="codigopais"
						placeholder="${paisEditar.nombrecorto}"
						value="${(not empty nombreIntroducido)? nombrecortoIntroducido: paisEditar.nombrecorto}">
						</div>

					</div>


				</div>


				<div class="form-group col">
					<label for="continente">Continente: </label> <select
						class="form-control" id="continente" name="continentepais">

						<c:if test="${empty continenteSeleccionado }">
							<option value="1"
								${('1' eq paisEditar.continente.id)? 'selected':'' }>Africa</option>
							<option value="2"
								${('2' eq paisEditar.continente.id)? 'selected':'' }>América</option>
							<option value="3"
								${('3' eq paisEditar.continente.id)? 'selected':'' }>Antártida</option>
							<option value="4"
								${('4' eq paisEditar.continente.id)? 'selected':'' }>Asia</option>
							<option value="5"
								${('5' eq paisEditar.continente.id)? 'selected':'' }>Europa</option>
							<option value="6"
								${('6' eq paisEditar.continente.id)? 'selected':'' }>Oceania</option>

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
				
				<c:choose>
         
				         <c:when test = "${paisEditar.numciudades >1}">
				            <div class="alert alert-warning text-center mt-5" role="alert">
			  					Existen 
			  					
			  					<a class="text-dark" href="ciudades-pais?id=${paisEditar.id}&nombre=${paisEditar.nombre}">
			  						${paisEditar.numciudades} Ciudades registradas
			  					</a>  
			  					
			  					en este país.
							</div>
				         </c:when>
				         
				         <c:when test = "${paisEditar.numciudades == 1}">
				            <div class="alert alert-warning text-center mt-5" role="alert">
			  					Existe
			  					
			  					<a class="text-dark" href="ciudades-pais?id=${paisEditar.id}&nombre=${paisEditar.nombre}">
			  						${paisEditar.numciudades} Ciudad registrada
			  					</a>  
			  					
			  					en este país.
							</div>
				         </c:when>
				         
				         <c:when test = "${paisEditar.numciudades == 0}">
				            <div class="alert alert-info text-center mt-5" role="alert">
							   No existen ciudades registradas en este pais.
							</div>
				         </c:when>
				         
				   
				      </c:choose>
				
				
				
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
				<label class="d-block text-center">Portada</label>
				<div class="card bg-dark text-white">
					<img class="card-img ciudades-card" src="${paisEditar.bandera}"
						alt="portadaPais">
					<div class="card-img-overlay">
						<p class="titulo-card">
							<strong>${paisEditar.nombre}</strong>
						</p>

					</div>
				</div>
				
				<form method="post" action="UploadServlet"
					enctype="multipart/form-data">
					<label class="d-block mt-5">Actualizar portada</label>
					 <input type="file"
						name="file" class="d-inline mt-3">
					<button type="submit" value="Upload">subir</button>
				</form>
			</div>
		</div>


	</div>
</div>

<jsp:include page="includes/pie.jsp" />