<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty ciudadesMasVisitadas }">
	<c:redirect url="inicio" />
</c:if>

<jsp:include page="includes/cabecera.jsp">

	<jsp:param name="pagina" value="Inicio" />

	<jsp:param name="title" value="Inicio" />

</jsp:include>


<div class="row my-4 d-flex justify-content-center">

	<!-- Boton busqueda -->
	<div class="col-sm-12 col-md-12 col-lg-12 text-center input-group  form-sm form-1">
		<div class="input-group-prepend">
			<span class="input-group-text cyan lighten-2" id="basic-text1"><i
				class="fas fa-search text-white" aria-hidden="true"></i></span>
		</div>
		<input class="form-control my-0" type="text" placeholder="Search"
			aria-label="Search">
	</div>

	<!-- titulo -->
	<div class="col-sm-12 col-md-12 col-lg-12 my-5">
		<h2 class="text-primary text-center">Ciudades más visitadas</h2>
	</div>

	<!-- Caja Cards -->
	<c:forEach items="${ciudadesMasVisitadas}" var="c">
		<div class="col-sm-5 col-md-4 col-lg-4 my-2 ">

			<div class="card bg-dark text-white">
				<img class="card-img ciudades-card" src="img/${c.portada}"
					alt="ciudad">
				<div class="card-img-overlay">
					<p class="titulo-card">
						<strong>${c.nombre}</strong> <a href="#" class="btn btn-primary">Ver
							vuelos <i class="fas fa-plane"></i>
						</a>
					</p>

				</div>
			</div>

		</div>

	</c:forEach>

</div>

<!-- Linea para dividir -->
<div class="my-5 col-12 border-bottom"></div>


<jsp:include page="includes/pie.jsp" />