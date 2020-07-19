
<!-- Todas las rutas relativas comienzan por el href indicado -->
<!--  ${pageContext.request.contextPath} == http://localhost:8080/agencia-viajes master -->
<base href="${pageContext.request.contextPath}/" />
<jsp:include page="/includes/cabecera.jsp">

	<jsp:param name="pagina" value="Inicio" />

	<jsp:param name="title" value="Backoffice" />

</jsp:include>

<h1>BackOffice</h1>
<p>Estas logeado como administrador</p>

<jsp:include page="/includes/pie.jsp" />