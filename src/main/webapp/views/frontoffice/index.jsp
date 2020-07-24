<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Todas las rutas relativas comienzan por el href indicado -->
<!--  ${pageContext.request.contextPath} == http://localhost:8080/agencia-viajes master -->
<base href="${pageContext.request.contextPath}/" />

<jsp:include page="/includes/cabecera.jsp">

	<jsp:param name="pagina" value="Inicio" />

	<jsp:param name="title" value="FrontOffice" />

</jsp:include>

        <div id="layoutSidenav">
            
            <jsp:include page="/includes/office-side-nav-bar.jsp" />
            
            <div id="layoutSidenav_content">
                
                    <div class="container-fluid">
                        <h1 class="mt-4"> Mi panel</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Panel de administracion</li>
                        </ol>
                        <div class="row">
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                
                                    <div class="card-body">Proximos Vuelos <h3>${proximos}</h3></div>
                                    
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="views/frontoffice/proximos-vuelos">Consultar</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-success text-white mb-4">
                                    <div class="card-body">Vuelos cogidos <h3>${tomados}</h3></div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="views/frontoffice/vuelos-cogidos">Consultar</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-danger text-white mb-4">
                                    <div class="card-body">Vuelos cancelados<h3>${cancelados}</h3></div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="views/frontoffice/vuelos-cancelados">Consultar</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                       
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table mr-1"></i>
                                Historico de vuelos
                            </div>
                            
                            <div class="card-body">
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
									  
									  
										  <c:forEach items="${listabooking}" var="b">
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
                
            </div>
        </div>



<jsp:include page="/includes/pie.jsp" />