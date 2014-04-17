var http = require('http');

http.createServer(function(request, response) {
    response.writeHead(200, {'Content-Type': 'text/html'});
    response.end("Test 2");
}).listen(52274, function() {
	console.log("server is running ...");
});