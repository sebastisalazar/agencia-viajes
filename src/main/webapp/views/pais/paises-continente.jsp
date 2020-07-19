<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Todas las rutas relativas comienzan por el href indicado -->
    <!--  ${pageContext.request.contextPath} == http://localhost:8080/supermerkado-master -->
<base href="${pageContext.request.contextPath}/" />

<c:if test="${empty paisesContinente}">
	<c:redirect url="paises-continente?id=${param.id}&nombre=${param.nombre}" />
</c:if>

<jsp:include page="/includes/cabecera.jsp">

	<jsp:param name="pagina" value="Paises" />

	<jsp:param name="title" value="Paises" />

</jsp:include>


<h1 class="text-primary text-center mb-3"> Paises en ${busquedaPaises}</h1>


<table class="table table-striped table-hover tabla ">
	<thead class="thead-dark text-center">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">PAIS</th>
			<th scope="col">CODIGO PAIS</th>
			<th scope="col">CONTINENTE</th>
			<th scope="col">CIUDADES</th>
			
			<c:if test="${not empty loginUsuario}">
				<c:if test="${loginUsuario.rol.id == 2}">
					<th scope="col">EDITAR</th>
					<th scope="col">ELIMINAR</th>
				</c:if>
			</c:if>
		</tr>
	</thead>
	
	<c:if test="${paisesContinente.containsKey('vacio')==false}">
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
						<td>${busquedaPaises}</td>
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
						
						<c:if test="${not empty loginUsuario}">
							<c:if test="${loginUsuario.rol.id == 2}">
								<td>
									<a href="actualizar-pais?id=${p.key}"> 
										<i class="fas fa-pencil-alt"></i>
									</a>
								</td>
				
								<td>
									<a onclick="confirmar('${p.value.nombre}')" href="eliminar-pais?id=${p.key}"> 
										<i class="fas fa-trash-alt"></i>
									</a>
								</td>
							</c:if>
						</c:if>
		
						
					</tr>
				</c:forEach>
		
			</tbody>
	
	</c:if>
	
	
	
</table>
	

<jsp:include page="/includes/pie.jsp" />