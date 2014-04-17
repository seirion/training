

var http = require('http');
var express = require('express');
var connect = require('connect');


var app = express();

app.use(connect.logger('short'));

app.use(function(request, response, next) {
	request.number = 52;
	response.number = 273;
	
	next();
});


app.use(function(request, response, next) {
    response.send( '<h1>' + request.number + ' : ' + response.number + '</h1>');
});

http.createServer(app).listen(52273, function() {
	console.log("express server is running ... ");
});