
var serviceURL = 'http://currency-api.appspot.com/api/{source}/{target}.json?key=c14bce616f21334f84f3f3c18d7f10986ccf0310';
var support = {
	USD: 'USD',
	THB: 'THB'
};

exports.support = support;

exports.getRate = function( o, source, target ) {
    var client = Ti.Network.createHTTPClient({
        // function called when the response data is available
        onload: function(e) {
            var data = JSON.parse(this.responseText);
            if( data == null ){
            	o.error( 'data is null' );
            } else {
            	o.success( data );
            }
            //if (o.success) { o.success(data, timeUsed); }
        },

        // function called when an error occurs, including a timeout
        onerror : function(e) {
            o.error( e.error );
        },
        
        timeout : 5000  // in milliseconds
    });

	if (o.start) { o.start(); }
	// Prepare the connection
	if ( source && target ) {
		client.open('GET', serviceURL.replace( '{source}', source ).replace( '{target}', target ) );
		
	    // Send the request.
	    client.send();
	} else {
		o.error('Missing parameters');
		return;
	}
};