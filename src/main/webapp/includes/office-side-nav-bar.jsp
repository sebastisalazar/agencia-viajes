<div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Inicio</div>
                            <a class="nav-link" href="views/frontoffice/index.jsp">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Mi panel
                            </a>
                            
                            <div class="sb-sidenav-menu-heading">Consultas</div>
                            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Vuelos
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="views/frontoffice/proximos-vuelos">Proximos</a>
                                    <a class="nav-link" href="views/frontoffice/vuelos-cogidos">Tomados</a>
                                    <a class="nav-link" href="views/frontoffice/vuelos-cancelados">Cancelados</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Busqueda
                            </a>
                            
                            <div class="sb-sidenav-menu-heading">Mi cuenta</div>
                            <a class="nav-link" href="views/frontoffice/datos-usuario.jsp">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Mis datos
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logeado como:</div>
                        ${loginUsuario.email}
                    </div>
                </nav>
            </div>