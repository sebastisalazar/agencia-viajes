<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Si se intenta entrar siendo un usuario normal, es decir NO ADMIN, redigirá 
<c:if test="${not empty loginUsuario}">
         <c:if test = "${loginUsuario.rol.id == 1}">
            <c:redirect url="inicio"></c:redirect>
      	</c:if>
	
</c:if> -->

<!-- Todas las rutas relativas comienzan por el href indicado -->
    <!--  ${pageContext.request.contextPath} == http://localhost:8080/supermerkado-master -->
<base href="${pageContext.request.contextPath}/" />

<jsp:include page="/includes/cabecera.jsp">

	<jsp:param name="pagina" value="Registro Usuario" />

	<jsp:param name="title" value="Usuario" />

</jsp:include>

<div class="d-flex flex-row justify-content-center border-0  ">
	
	
	<form class="px-5 py-3 border pb-5" action="crear-usuario" method="POST">
		<h1 class="text-primary text-center mb-3 mt-5">Registro usuario</h1>
		
		<!-- Pinta los mensajes si hay alguno -->
		<c:if test="${not empty requeridos}">

			<jsp:include page="/includes/requeridos.jsp"></jsp:include>

			<!-- Una vez pintado borra los mensajes -->
			<%
				session.removeAttribute("requeridos");
			%>
		</c:if>
		
		<div class="form-group pt-2">
			<label for="nombre">Nombre:</label> <input
				type="text" class="form-control px-5" id="nombre" name="nombre" value="${nombreIntroducidoC}">
		</div>
		
		<div class="form-group pt-2">
			<label for="ape1">Primer apellido:</label> <input
				type="text" class="form-control px-5" id="ape1" name="ape1" value="${ape1IntroducidoC}">
		</div>
		
		<div class="form-group pt-2">
			<label for="ape2">Segundo apellido:</label> <input
				type="text" class="form-control px-5" id="ape2" name="ape2" value="${ape2IntroducidoC}">
		</div>
		
		<div class="form-group col">
					<div class="row row-cols-2">

						<div class="col-6">
							<label for="pais">Pais: </label>
						</div>
						<div class="col-6">
							<label for="pais" class="pl-2 d-block">Bandera </label>
						</div>
						<div class="col-6">

							<select class="form-control" id="pais" name="pais">

								<c:forEach items="${selectPaises}" var="p">

									<c:if test="${empty paisSeleccionado}">
										<option value="${p.id},${p.nombrecorto}"
											${(p.id eq 1)? 'selected':'' }>${p.nombre}</option>

									</c:if>

									<c:if test="${not empty paisSeleccionado }">
										<option value="${p.id}"
											${(p.id eq paisSeleccionado)? 'selected':'' }>${p.nombre}</option>
									</c:if>
									
								</c:forEach>

							</select>

						</div>

						<div class="col" style="vertical-align: baseline">
							<div class="country d-block text-center" style="margin: 0px; padding: 8px 6px">
								<i></i> <b></b>
							</div>
						</div>

					</div>


				</div>
		
		<div class="form-group pt-2">
			<label for="dnie">DNIE / NIE :</label> <input
				type="text" class="form-control px-5" id="dnie" name="dnie" value="${dnieIntroducidoC}">
		</div>
		
		<div class="form-group pt-2">
			<label for="residencia">Direccion :</label> <input
				type="text" class="form-control px-5" id="direccion" name="direccion" value="${direccionIntroducidoC}">
		</div>
				
		<div class="form-group pt-2">
			<label for="email">Email:</label> <input
				type="email" class="form-control px-5" id="email" name="email" value="${emailIntroducidoC}">
		</div>
		<div class="form-group ">
			<label for="password">Password:</label> <input
				type="password" class="form-control px-5" id="password" name="password" value="${passwordIntroducidoC}">
		</div>
		
		<div class="d-flex justify-content-center">
			
			   <button type="submit" class="btn btn-primary px-5">Crear</button>
			
			
		</div>
	</form>

</div>



<jsp:include page="/includes/pie.jsp" />

