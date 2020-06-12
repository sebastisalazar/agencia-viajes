<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Ciudades" />

	<jsp:param name="title" value="Ciudades" />

</jsp:include>

<h1 class="text-primary text-center mb-3">Lista ciudades</h1>


<table class="table table-striped table-hover tabla ">
	<thead class="thead-dark text-center">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">CIUDAD</th>
			<th scope="col">PAIS</th>
			<th scope="col">BANDERA</th>
			<th scope="col">CONTINENTE</th>
			<th scope="col">EDITAR</th>
			<th scope="col">ELIMINAR</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${lista}" var="c">
			<tr class="text-center">
				<td>${c.id}</td>
				<td>${c.nombre}</td>
				<td>${c.pais.nombre}</td>
				<td><img src="${c.pais.bandera}"></td>
				<td>${c.continente.nombre}</td>
				<td>
					<a href="actualizar-ciudad?id=${c.id}">
						<i class="fas fa-pencil-alt"></i>
					</a>
				</td>
				
				<td>
					<a onclick="confirmar('${c.nombre}')"href="eliminar-ciudad?id=${c.id}"> 
						<i class="fas fa-trash-alt"></i>
					</a>
				</td>
			</tr>
		</c:forEach>

	</tbody>

</table>


<jsp:include page="includes/pie.jsp" />

