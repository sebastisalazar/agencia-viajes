<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Inicio" />

	<jsp:param name="title" value="Inicio" />

</jsp:include>

<div class="container">


	<div class="col-12 text-center input-group md-form form-sm form-1 pl-0">
		<div class="input-group-prepend">
			<span class="input-group-text cyan lighten-2" id="basic-text1"><i
				class="fas fa-search text-white" aria-hidden="true"></i></span>
		</div>
		<input class="form-control my-0 py-1" type="text" placeholder="Search"
			aria-label="Search">
	</div> 
	
	<div class="container row row-cols-3 my-4">

		<div class="col-12">
			<h1 class="text-primary text-center">Ciudades más visitadas</h1>
		</div>
		<c:forEach items="${ciudadesMasVisitadas}" var="c">
			<div class="card" style="width: 18rem;">
				<img src="${c.pais.bandera}" class="card-img-top" alt="bandera">
				<div class="card-body">
					<h5 class="card-title">${c.nombre}</h5>
					<a href="#" class="btn btn-primary">Ver vuelos</a>
				</div>
			</div>
		</c:forEach>

	</div>

</div>


<jsp:include page="includes/pie.jsp" />