var http = require('http');
var express = require('express');


var app = express();

app.use(function(request, response) {
	var agent = request.header('User-Agent');
	console.log(agent);
	console.log(request.headers);
    response.send(200);
});

http.createServer(app).listen(52273, function() {
	console.log("express server is running ... ");
});