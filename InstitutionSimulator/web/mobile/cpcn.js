window.alert = function(msg){
	$('span','#alert').text(msg);
	$('#alert').fadeIn();
	window.setTimeout(function(){
		$('#alert').fadeOut();
	},1200);
	
}


