(function($) {
  // size = flag size + spacing
  var default_size = {
    w: 20,
    h: 15
  };

  function calcPos(letter, size) {
    return -(letter.toLowerCase().charCodeAt(0) - 97) * size;
  }

  $.fn.setFlagPosition = function(iso, size) {
    size || (size = default_size);

    var x = calcPos(iso[1], size.w),
      y = calcPos(iso[0], size.h);

    return $(this).css('background-position', [x, 'px ', y, 'px'].join(''));
  };
})(jQuery);




// USAGE:

(function($) {
	
	
  $(function() {
	
    var $target = $('.country');
    
//ACTUALIZAR CIUDAD
    var actual=document.getElementById('pais').value;//pilla el valor seleccionado. ejemplo value="1,es" donde 1 es el id el pais y es es para que pille la bandera
    console.log(actual);
    
    //solo queremos el nombre corto asi que lo cortamos diciendole que empiece desde la coma hasta el final
    var actual=actual.substring(actual.indexOf(',')+1);
    console.log(actual);
   
  
    // on load:
    $target.find('i').setFlagPosition(actual);
    $target.find('b').text($("#pais").find(':selected').text());
    
	  
	

//CAMBIO DE PAIS AL ACTUALIZAR
    //cuando se seleccione otro pais
    $('select').change(function() {
    
        var pos=this.value; //pilla el valor seleccionado. ejemplo value="1,es" donde 1 es el id el pais y es es para que pille la bandera
        console.log(pos);
        
        //solo queremos el nombre corto asi que lo cortamos diciendole que empiece desde la coma hasta el final
        var pos= pos.substring(pos.indexOf(',')+1);
        console.log(pos);
        
        $target.find('i').setFlagPosition(pos);
        $target.find('b').text($(this).find(':selected').text());
    });
    
    
    
  });
  

})(jQuery);

	
	
	  function cargarBandera() {
		
	    var $target = $('.country');
	    $target.find('i').setFlagPosition('de');
	    

	  };
	  

