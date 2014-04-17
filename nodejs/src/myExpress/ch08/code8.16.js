
var http = require('http');
var express = require('express');
var connect = require('connect');


var app = express();

app.use(connect.logger('short'));
//app.use(app.router);
app.use(express.static(__dirname + '/public'));

//app.route('/a').get(function(request, response) {
	//response.send('<a href="/b">go to B');
//});
app.get('/a', function(request, response) {
	response.send('<a href="/b">go to B');
});
app.get('/b', function(request, response) {
	response.send('<a href="/a">go to A');
});

app.get('/page/:id', function(request, response) {
	var name = request.param('id');
	response.send('<h1>' + name + '</h1>');
});

app.all('*', function(request, response) {
	response.send(404, 'page not found ~~~');
});
http.createServer(app).listen(52273, function() {
	console.log("express server is running ... ");
});