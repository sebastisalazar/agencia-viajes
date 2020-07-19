<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty ciudadesPais}">
	<c:redirect url="ciudades-pais?id=${param.id}&nombre=${param.nombre}" />
</c:if>

<!-- Todas las rutas relativas comienzan por el href indicado -->
    <!--  ${pageContext.request.contextPath} == http://localhost:8080/supermerkado-master -->
<base href="${pageContext.request.contextPath}/" />

<jsp:include page="/includes/cabecera.jsp">

	<jsp:param name="pagina" value="Ciudades" />

	<jsp:param name="title" value="Ciudades" />

</jsp:include>


<h1 class="text-primary text-center mb-3"> Ciudades en ${busquedaCiudades}</h1>


<table class="table table-striped table-hover tabla ">
	<thead class="thead-dark text-center">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">CIUDAD</th>
			<th scope="col">CODIGO PAIS</th>
			<th scope="col">PAIS</th>
			<th scope="col">CONTINENTE</th>
			
			<c:if test="${not empty loginUsuario}">
				<c:if test="${loginUsuario.rol.id == 2}">
					<th scope="col">EDITAR</th>
					<th scope="col">ELIMINAR</th>
				</c:if>
			</c:if>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ciudadesPais}" var="c">
			<tr class="text-center">
				<td>${c.id}</td>
				<td>${c.nombre}</td>
				<td>
					
					<div class="country" >
						<i onload="cargarBandera()">
							${c.pais.nombrecorto}
						</i>
					</div>
					
				</td>
				<td>${c.pais.nombre}</td>
				<td>${c.continente.nombre}</td>
				
				<c:if test="${not empty loginUsuario}">
					<c:if test="${loginUsuario.rol.id == 2}">
						<td>
							<a href="actualizar-ciudad?id=${c.id}"> 
								<i class="fas fa-pencil-alt"></i>
							</a>
						</td>
		
						<td>
							<a onclick="confirmar('${c.nombre}')" href="eliminar-ciudad?id=${c.id}"> 
								<i class="fas fa-trash-alt"></i>
							</a>
						</td>
					</c:if>
				</c:if>
			</tr>
		</c:forEach>

	</tbody>

</table>

<jsp:include page="/includes/pie.jsp" />