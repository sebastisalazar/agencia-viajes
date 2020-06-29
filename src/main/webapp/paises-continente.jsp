<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not  empty loginEmail }">
	<c:redirect url="listado-ciudades"/>
</c:if>

<c:if test="${ empty paisesContinente }">
	<c:redirect url="paises-continente"/>
</c:if>


<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Continente" />

	<jsp:param name="title" value="Continente" />

</jsp:include>


<h1 class="text-primary text-center mb-3"> Paises en ${busqueda}</h1>


<table class="table table-striped table-hover tabla ">
	<thead class="thead-dark text-center">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">PAIS</th>
			<th scope="col">BANDERA</th>
			<th scope="col">CONTINENTE</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${paisesContinente}" var="p">
			<tr class="text-center">
				<td>${p.id}</td>
				<td>${p.nombre}</td>
				<td>
					<div class="country" >
						<i onload="cargarBandera()">
							${p.nombrecorto}
						</i>
					</div>
				
				</td>
				<td>${busqueda}</td>

				
			</tr>
		</c:forEach>

	</tbody>

</table>








<jsp:include page="includes/pie.jsp" />