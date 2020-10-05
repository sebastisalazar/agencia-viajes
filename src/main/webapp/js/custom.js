
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

//function para editar password de un usuario existente
var correcto=true;

function Iguales(){
	
	//guarda el dato de los campos password 1 y 2
    var clave1 = document.getElementById('password1').value;
    var clave2 = document.getElementById('password2').value;
    
    //si se rellena el campo password1 se obliga a que el segundo sea requerido
    if (clave1) {
    	$("#password2").attr( "required","true");
    	
    	
	}else{
		//si no se ha escrito nada se quita el required
		$("#password2").removeAttr( "required");

		//si el campo esta vacio se da por correcto
		correcto=true;
	}
    
    
    
    //comprobamos si al menos se ha rellenado el segundo campo
    if (clave2) {
    	
    	//se evalua si los datos NO son iguales
    	if (clave1 != clave2){
        	
    		//se pinta el mensaje quitando el style
        	$("#mensaje").removeAttr("style");
        	
        	//se pinta en color los campos que no coincidan
        	$("#password1").attr( "style", "background-color: rgba(255,0,0,0.2)" );
        	$("#password2").attr( "style", "background-color: rgba(255,0,0,0.2)" );
        	
        	//si los campos no son iguales se avisa que no es correcto
        	correcto=false;
        	
        	
        	//si son iguales
        }else{
        	
        	//se quita el color rojo a ambos campos
        	$("#password1").removeAttr("style");
        	$("#password2").removeAttr("style");
        	
        	//se vuelve a pintar en verde para mostrar que ahora si coinciden
        	$("#password1").attr( "style", "background-color: rgba(0,153,51,0.2)" );
        	$("#password2").attr( "style", "background-color: rgba(0,153,51,0.2)" );
        	
        	//Se oculta el mensaje
        	$("#mensaje").attr( "style", "visibility:hidden" );
        	
        	//si los campos coinciden se da por correcto
        	correcto=true;
        	
        }
    	
    	
    	//si no se ha rellenado el segundo campo o si esta vacio
	}else{
		
		
		//se quita el color rojo a ambos campos
		$("#password1").removeAttr("style");
    	$("#password2").removeAttr("style");
    	
    	//Se oculta el mensaje
		$("#mensaje").attr( "style", "visibility:hidden" );
		
		//si el campo esta vacio se da por correcto
		correcto=true;
		
	}
    
    
   
    
    
} 



