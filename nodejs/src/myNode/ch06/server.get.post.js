
var http = require('http');
var url = require('url');
var fs = require('fs');
var querystring = require('querystring');

http.createServer(function(request, response) {
	if (request.method === 'GET') {
		fs.readFile('form.html', function(error, data) {
            response.writeHead(200, {'Content-Type': 'text/html'});
            response.end(data);
		});
	}
	else if (request.method === 'POST') {
		request.on('data', function(data){
			console.log(data);
			var q = querystring.parse(data.toString(), '&', '=');
			console.log(JSON.stringify(q));
            response.writeHead(200, {'Content-Type': 'text/html'});
            response.end('<H1>' + JSON.stringify(q) + '</H1>');
//            response.end('<H1>' + data + '</H1>');
		});
	}
	var query = url.parse(request.url, true).query;
	
}).listen(52274);