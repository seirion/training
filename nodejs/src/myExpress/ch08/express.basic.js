var http = require('http');
var express = require('express');


var app = express();

app.use(function(requeset, response) {
    response.writeHead(200, {'Content-Type': 'text/html'});
    response.end("Hello World !!");
});

http.createServer(app).listen(52273, function() {
	console.log("express server is running ... ");
});