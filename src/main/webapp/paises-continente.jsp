<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
			<th scope="col">CIUDADES</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${paisesContinente}" var="p">
			<tr class="text-center">
				<td>${p.key}</td>
				<td>${p.value.nombre}</td>
				<td>
					
					<div class="country" >
						<i onload="cargarBandera()">
							${p.value.nombrecorto}
						</i>
					</div>
				</td>
				<td>${busqueda}</td>
				<td>
					
					<c:choose>
         
				         <c:when test = "${p.value.numciudades >0}">
				            <a href="ciudades-pais?id=${p.key}&nombre=${p.value.nombre}">Ver ciudades (${p.value.numciudades})</a>
				         </c:when>
				         
				         
				         <c:otherwise>
				            No existen ciudades registradas.
				         </c:otherwise>
				      </c:choose>
					
					
				</td>

				
			</tr>
		</c:forEach>

	</tbody>

</table>








<jsp:include page="includes/pie.jsp" />