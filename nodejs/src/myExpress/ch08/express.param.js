
var http = require('http');
var express = require('express');


var app = express();

app.use(function(request, response) {
	var name = request.param('name');
	var age = request.param('age');
    response.send(name + ' - ' + age);
});

http.createServer(app).listen(52273, function() {
	console.log("express server is running ... ");
});