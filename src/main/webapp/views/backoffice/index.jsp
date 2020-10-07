
<!-- Todas las rutas relativas comienzan por el href indicado -->
<!--  ${pageContext.request.contextPath} == http://localhost:8080/agencia-viajes master -->
<base href="${pageContext.request.contextPath}/" />
<jsp:include page="/includes/cabecera.jsp">

	<jsp:param name="pagina" value="Inicio" />

	<jsp:param name="title" value="Backoffice" />

</jsp:include>




<div id="layoutSidenav">
            
            <jsp:include page="/includes/office-side-nav-bar.jsp" />
            
            <div id="layoutSidenav_content">
                
                    <div class="container-fluid">
                        <h1 class="mt-4"> Panel BackOffice</h1>
                        
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Panel de administracion</li>
                        </ol>
                        <div class="row">
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                
                                    <div class="card-body">Lista de usuarios </div>
                                    
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="listado-usuarios">Consultar</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                
                                    <div class="card-body">Lista de ciudades</div>
                                    
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="listado-ciudades">Consultar</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            
                            
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-success text-white mb-4">
                                    <div class="card-body">Nueva ciudad</div>
                                    <i class="fas fa-plus h1 text-center"></i>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="crear-ciudad">Registrar</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-success text-white mb-4">
                                    <div class="card-body">Nuevo<br>pais  <br></div>
                                    <i class="fas fa-plus h1"></i>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="crear-pais">Registrar</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            
                            
                        </div>
                       
                        
                    </div>
                
            </div>
        </div>

<jsp:include page="/includes/pie.jsp" />