
var http = require('http');

http.createServer(function(request, response) {
	var date = new Date();
	date.setDate(date.getDate() + 7);
    response.writeHead(200, 
    		{'Content-Type': 'text/html',
    		'Set-Cookie': [
    		               'breakfast = toast; Expires = ' + date.toUTCString(),
    		               'dinner = chiken'
    		               ]
    });
    response.end('<H1>' + request.headers.cookie + '</H1>');
}).listen(52273);