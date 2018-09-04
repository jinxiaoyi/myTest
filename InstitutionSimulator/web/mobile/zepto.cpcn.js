/**
 * This jQuery plugin displays pagination links inside the selected elements.
 * 
 * This plugin needs at least jQuery 1.4.2
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 2.2
 * @param {int} maxentries Number of entries to paginate
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
 (function($){
	
	$.fn.slideLeft = function(){
		$(this).click(function(){
          $($(this).attr('target')).show().animate({
            top:0,left:'0'
          });
        });
		
	}
	
})(Zepto);
