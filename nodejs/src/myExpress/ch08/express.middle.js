
var http = require('http');
var express = require('express');


var app = express();

app.use(function(request, response, next) {
	console.log('the 1');
	next();
});

app.use(function(request, response, next) {
	console.log('the 2');
	next();
});

app.use(function(request, response, next) {
	console.log('the 3');
	response.writeHead(200, {'Content-Type': 'text/html'});
    response.end('Hi Hi Hi !!');
});

http.createServer(app).listen(52273, function() {
	console.log("express server is running ... ");
});