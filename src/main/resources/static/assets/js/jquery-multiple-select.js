// JavaScript Document
(function ( $ ) {
 
    $.fn.JqueryMultipleSelect = function(options) {
        /// Jquery Multiple Select
	$(this).click(function(){
		
		/// Extend Value 
		var settings = $.extend({
            // These are the defaults.
            color: "#556b2f",
            ResultBackgroundColor: "#f00",
			ResultClass:'hi-there',
			AddExtraClassinResult:'custom-result-list',
	        AddExtraClassinSelectList:'custom-select-list',
	        AddClassOnDeleteIcon:'custom-delete-icon',
        }, options );
		
		// Extend End 
		
		
		
		
		var getMultipleSelectValue = $(this).text();
		$('.cancel-multiple-select').addClass(settings.AddClassOnDeleteIcon);
		$(this).addClass(settings.AddExtraClassinSelectList);
		if($(this).hasClass('active-multiple-select')==true)
		{
			$(this).removeClass('active-multiple-select');
			$('.result-value-multiple-select').each(function(){
				
				var getDeselectText = $(this).children('.inner-multiple-select').text();
				if(getMultipleSelectValue == getDeselectText)
				{
				   $(this).remove();	
				}else 
				{
					
				}
				
				
				});
			
		} else
		{
		   $(this).addClass('active-multiple-select');  
           $('.'+settings.ResultClass).addClass(settings.AddExtraClassinResult).css('background-color',settings.ResultBackgroundColor);
           $('.'+settings.ResultClass).append('<span class="result-value-multiple-select"><span class="inner-multiple-select">'+getMultipleSelectValue+'</span><span class="cancel-multiple-select">x</span></span>');
		
		} // active-multiple-select if condition End
		
		
		
		
		}); /// This Click End 
		
    };
	
	/// Cancel Multiple Select Box 
	/// Jquery Multiple Select END`
	$(document).on('click','.cancel-multiple-select',function(){
		    
			var getCancelText = $(this).parent().children('.inner-multiple-select').text();
			// Each loop Start
			$('.active-multiple-select').each(function(){
				var getAllCancelText = $(this).text();
				//alert(getAllCancelText);
				if(getCancelText == getAllCancelText )
				{
					$(this).removeClass('active-multiple-select');
					
			    } else
				{
					//alert('Not Find Match Class')
					
				}
				});
			// Each loop End
			$(this).parent().remove();
		
		});	
	
	
 
}( jQuery ));




