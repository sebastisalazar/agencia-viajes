
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Todas las rutas relativas comienzan por el href indicado -->
<!--  ${pageContext.request.contextPath} == http://localhost:8080/agencia-viajes master -->
<base href="${pageContext.request.contextPath}/" />

<jsp:include page="/includes/cabecera.jsp">

	<jsp:param name="pagina" value="Inicio" />

	<jsp:param name="title" value="Proximos vuelos" />

</jsp:include>

        <div id="layoutSidenav">
            
            <jsp:include page="/includes/office-side-nav-bar.jsp" />
            
            <div id="layoutSidenav_content">
                
                    <div class="container-fluid">
                        <h1 class="mt-4"> Vuelos anteriores</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Resultados acorde a la fecha de partida.</li>
                        </ol>
                       
                                <div class="table-responsive">
                                    <table class="table tabla">
									  <thead>
									    <tr>
									      <th scope="col">Booking ID</th>
									      <th scope="col">Destino</th>
									      <th scope="col">Aeorlinea</th>
									      <th scope="col">Fecha de reserva</th>
									      <th scope="col">Fecha de partida</th>
									      <th scope="col">Fecha de regreso</th>
									    </tr>
									  </thead>
									  <tbody>
									  
									  
										  <c:forEach items="${listaCogidos}" var="b">
	        									 <tr>
												      <th scope="row">${b.id}</th>
												      <td>${b.ci.nombre}</td>
												      <td>${b.ae.nombre}</td>
												      <td>${b.fecha_booking}</td>
												      <td>${b.fecha_partida}</td>
												      <td>${b.fecha_vuelta}</td>
												 </tr>
	      								  </c:forEach>
									  	
									  </tbody>
									</table>
                                </div>
                            </div>
                        </div>
                    </div>
                
           



<jsp:include page="/includes/pie.jsp" />