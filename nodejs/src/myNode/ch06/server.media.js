
var fs = require('fs');
var http = require('http');


http.createServer(function(request, response) {
	fs.readFile('Chrysanthemum.jpg', function(error, data) {
		response.writeHead(200, {'Content-Type': 'image/jpeg'});
		response.end(data);
	});
}).listen(52273);

http.createServer(function(request, response) {
	fs.readFile('Kalimba.mp3', function(error, data) {
		response.writeHead(200, {'Content-Type': 'audio/mp3'});
		response.end(data);
	});
}).listen(52274);