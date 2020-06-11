<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Ciudades" />

	<jsp:param name="title" value="Ciudades" />

</jsp:include>

<h1 class="text-primary text-center mb-3">Ciudades destinos</h1>


<table class="table tabla">
	<thead class="thead-dark">
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
			<tr>
				<td>${c.id}</td>
				<td>${c.nombre}</td>
				<td>${c.pais.nombre}</td>
				<td><img src="https://picsum.photos/100"></td>
				<td>${c.continente.nombre}</td>
				<td><a href="editar-ciudad?id=${c.id}"> EDITAR</a></td>
				<td><a href="eliminar-ciudad?id=${c.id}"> ELIMINAR</a></td>
			</tr>
		</c:forEach>

	</tbody>

</table>


<jsp:include page="includes/pie.jsp" />
