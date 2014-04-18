
var http = require('http');
var fs = require('fs');
var socketio = require('socket.io');

var server = http.createServer(function(request, response) {
	fs.readFile('page.html', function(error, data) {
		response.writeHead(200, {'Content-Type': 'text/html'});
		response.end(data);
	});
	console.log("express server is running ... ");
});

server.listen(52277, function() {
	console.log('server is running ... ');
});


var io = socketio.listen(server);
io.sockets.on('connection', function(socket) {
	
});