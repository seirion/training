var http = require('http');
var express = require('express');
var cookieParser = require('cookie-parser');


var app = express();

//app.use(express.cookieParser());
app.use(cookieParser());

app.get('/get', function(request, response) {
	response.send(request.cookies);
});

app.get('/set', function(request, response) {
	response.cookie('string', 'cookie');
	response.cookie('json', {
		name: 'cookie',
		property: 'good'
	});
	
	response.redirect('/get');
});


app.use(function(request, response, next) {
    response.send( '<h1>' + request.number + ' : ' + response.number + '</h1>');
});

http.createServer(app).listen(52273, function() {
	console.log("express server is running ... ");
});