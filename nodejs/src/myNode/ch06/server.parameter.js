
var http = require('http');
var url = require('url');


http.createServer(function(request, response) {
	var query = url.parse(request.url, true).query;
	
    response.writeHead(200, {'Content-Type': 'text/html'});
    response.end('<H1>' + JSON.stringify(query) + '</H1>');
}).listen(52273);