
var http = require('http');
var express = require('express');
var connect = require('connect');


var app = express();

app.use(connect.logger('short'));
app.use(express.static(__dirname + '/public'));

app.use(function(request, response, next) {
	response.writeHead(200, {'Content-Type': 'text/html'});
    response.end( '<img src="/Penguins.jpg" />');
});

http.createServer(app).listen(52273, function() {
	console.log("express server is running ... ");
});