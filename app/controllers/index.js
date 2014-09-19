var currency = require('currency');



$.btn_convert.addEventListener( 'click', function(){
	currency.getRate({
		success: function( data ) {
			$.lbl_result.text = $.txt_source_money.value * data.rate + ' ฿';
		},
		error: function( error_msg ) {
		}
	}, $.txt_source_currency.value , $.txt_target_currency.value );
});

$.index.open();
