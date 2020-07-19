
 // ejecuta la funcion cuando todo el documento de html DOM este listo y cargado
$(document).ready(function() {
    // seleccion por id => #example y ejecuta el plugin .DataTable();
    $('.tabla').DataTable({
    	
    	"language": {
    		"sProcessing":     "Procesando...",
    	    "sLengthMenu":     "Mostrar _MENU_ registros",
    	    "sZeroRecords":    "No se encontraron resultados",
    	    "sEmptyTable":     "Ningún dato disponible en esta tabla",
    	    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
    	    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
    	    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
    	    "sInfoPostFix":    "",
    	    "sSearch":         "Buscar:",
    	    "sUrl":            "",
    	    "sInfoThousands":  ",",
    	    "sLoadingRecords": "Cargando...",
    	    "oPaginate": {
    	        "sFirst":    "Primero",
    	        "sLast":     "Último",
    	        "sNext":     "Siguiente",
    	        "sPrevious": "Anterior"
    	    },
    	    "oAria": {
    	        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
    	        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
    	    },
    	    "buttons": {
    	        "copy": "Copiar",
    	        "colvis": "Visibilidad"
    	    }
    	}
    });
    
});

function init() {
	console.log('Documento cargado y listo');
	
}


function confirmar(nombre) {
	
	// The confirm() method returns true if the user clicked "OK", and false otherwise. 
	if ( confirm('¿ Estas seguro que quires eliminar ' + nombre + ' ?') ){
		
		console.debug(' continua el evento por defceto del ancla ');
		
	}else {
		console.debug(' prevenimos o detenemos el evento del ancla ');
		event.preventDefault();
	}
	
}



