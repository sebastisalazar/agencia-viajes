<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Si se intenta entrar siendo un usuario normal, es decir NO ADMIN, redigirá 
<c:if test="${not empty loginUsuario}">
         <c:if test = "${loginUsuario.rol.id == 1}">
            <c:redirect url="inicio"></c:redirect>
      	</c:if>
	
</c:if>

<!-- Si se intenta acceder sin logearse redigirá 
<c:if test="${empty loginUsuario}">
	<c:redirect url="login.jsp"></c:redirect>
</c:if>-->

<!-- Todas las rutas relativas comienzan por el href indicado -->
    <!--  ${pageContext.request.contextPath} == http://localhost:8080/supermerkado-master -->
<base href="${pageContext.request.contextPath}/" />

<!-- Si la sesion caduca y se recarga borrará el atributo lista, por lo tanto volvemos a llamar al controlador para que nos lo vuelva a pasar -->
<c:if test="${empty listaUsuarios}">
	<c:redirect url="listado-usuarios"></c:redirect>
</c:if>


<jsp:include page="/includes/cabecera.jsp">

	<jsp:param name="pagina" value="Ciudades" />

	<jsp:param name="title" value="Ciudades" />

</jsp:include>

<h1 class="text-primary text-center mb-3">Lista usuarios</h1>


<table class="table table-striped table-hover tabla ">
	<thead class="thead-dark text-center">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">EMAIL</th>
			<th scope="col">PASSWORD</th>
			<th scope="col">FOTO DE PERFIL</th>
			<th scope="col">TIPO</th>
			<th scope="col">EDITAR</th>
			<th scope="col">ELIMINAR</th>
				
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listaUsuarios}" var="u">
			<tr class="text-center">
				<td>${u.id}</td>
				<td>${u.email}</td>
				<td>${u.password}</td>
				<td>${u.imagen}</td>
				<td>${u.rol.nombre}</td>
				
						<td>
							<a href="actualizar-usuario?id=${u.id}"> 
								<i class="fas fa-pencil-alt"></i>
							</a>
						</td>
		
						<td>
							<a onclick="confirmar('${u.email}')" href="eliminar-usuario?id=${u.id}"> 
								<i class="fas fa-trash-alt"></i>
							</a>
						</td>
					
			</tr>
		</c:forEach>

	</tbody>

</table>


<jsp:include page="/includes/pie.jsp" />

