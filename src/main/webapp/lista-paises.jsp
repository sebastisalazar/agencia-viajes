<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 

<jsp:include page="includes/cabecera.jsp" >
 
<jsp:param name="pagina" value="Destinos" />
 
<jsp:param name="title" value="Paises Destinos" /> 
 
</jsp:include>



	<h1 class="text-primary text-center mb-3"> Paises destinos </h1>

	
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">NOMBRE</th>
				<th scope="col">BANDERA</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${lista}" var="pais">
				<tr>
					<td >${pais.id}</td>
					<td>${pais.nombre}</td>
					<td><img src="https://picsum.photos/100"></td>
				</tr>
			</c:forEach>
		
		</tbody>
	
	</table>
	
	
<jsp:include page="includes/pie.jsp"/>  

