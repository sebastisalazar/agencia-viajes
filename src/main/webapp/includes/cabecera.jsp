<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v4.0.1">

<!-- Todas las rutas relativas comienzan por el href indicado -->
<!--  ${pageContext.request.contextPath} == http://localhost:8080/agencia-viajes master -->
<base href="${pageContext.request.contextPath}/" />



<title>${param.title}</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.5/examples/product/">


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<!-- Bootstrap core CSS -->
<link href="/docs/4.5/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<!-- fontawesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css"
	rel="stylesheet">

<!-- Favicons -->
<link rel="apple-touch-icon"
	href="/docs/4.5/assets/img/favicons/apple-touch-icon.png"
	sizes="180x180">
<link rel="icon" href="/docs/4.5/assets/img/favicons/favicon-32x32.png"
	sizes="32x32" type="image/png">
<link rel="icon" href="/docs/4.5/assets/img/favicons/favicon-16x16.png"
	sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/4.5/assets/img/favicons/manifest.json">
<link rel="mask-icon"
	href="/docs/4.5/assets/img/favicons/safari-pinned-tab.svg"
	color="#563d7c">
<link rel="icon" href="/docs/4.5/assets/img/favicons/favicon.ico">
<meta name="msapplication-config"
	content="/docs/4.5/assets/img/favicons/browserconfig.xml">
<meta name="theme-color" content="#563d7c">


<!-- datatables -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>




<!-- Custom styles for this template -->
<link href="css/product.css" rel="stylesheet">



</head>
<body onload="init()">
	<nav class="site-header sticky-top py-1">
		<div
			class="container d-flex flex-column flex-md-row justify-content-between">
			<a class="py-2" href="#" aria-label="Product"> <svg
					xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					fill="none" stroke="currentColor" stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2" class="d-block mx-auto"
					role="img" viewBox="0 0 24 24" focusable="false">
      <title>Product</title>
      <circle cx="12" cy="12" r="10" />
      <path
						d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94" /></svg>
			</a> <a
				class="py-2 d-none d-md-inline-block ${( 'Inicio' eq param.pagina ) ? 'active' : ''}"
				href="inicio">Inicio</a> <a
				class="py-2 d-none d-md-inline-block ${( 'Destinos' eq param.pagina ) ? 'active' : ''}"
				href="listado-ciudades">Ciudades</a> 
				
					<div class="nav-item dropdown">
				
						<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
							role="button" aria-haspopup="true" aria-expanded="false">Continentes</a>
						<div class="dropdown-menu">
						
							<c:forEach items="${continentes}" var="c">
								
								<a class="dropdown-item" href="paises-continente?id=${c.id}&nombre=${c.nombre}">${c.nombre}</a>
							
							</c:forEach>
								
								
						</div>
					</div>
				
				
				<a class="py-2 d-none d-md-inline-block ${( 'Registro' eq param.pagina ) ? 'active' : ''}"
				href="crear-ciudad">Nueva Ciudad</a>
				
				<a class="py-2 d-none d-md-inline-block ${( 'Registro' eq param.pagina ) ? 'active' : ''}"
				href="crear-pais.jsp">Nuevo Pais</a>

			


			<c:if test="${not empty loginEmail}">

				<span class="form-inline "> <a
					class="nav-link  bg-dark rounded-left text-white" href="#"><small
						class="font-weight-bold">${loginEmail}</small></a> <a
					class="nav-link  bg-danger  rounded-right text-white" href="logout"><small><i
							class="fas fa-times font-weight-bold"></i></small></a>
				</span>


			</c:if>

			<c:if test="${empty loginEmail}">
				<span class="form-inline"> <a
					class="nav-link  btn btn-outline-dark bg-dark text-white font-weight-bold"
					href="login.jsp"><small class="font-weight-bold">Iniciar
							Sesión</small></a>
				</span>

			</c:if>

		</div>
	</nav>



	<main role="main" class="container">

		<div class="container my-5">

			<!-- Si el mensaje se muestra lo pinta y lo pone a null para pintarlo solo una vez -->
			<c:if test="${not empty alerta}">
				<jsp:include page="alerta.jsp"></jsp:include>
			</c:if>