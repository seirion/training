var fs = require('fs');
var http = require('http');
var express = require('express');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var jade = require('jade');


var _id = 'babo';
var _passwd = '1';

var app = express();

app.use(cookieParser());
app.use(bodyParser());

app.get('/', function(request, response) {
	if (request.cookies.id) {
		fs.readFile('index.jade', 'utf8', function(error, data) {
			response.writeHead(200, {'Content-Type': 'text/html'});
			response.end(jade.compile(data)());
		});
	}
	else {
		response.redirect('/login');
	}
});

app.get('/login', function(request, response) {
	fs.readFile('login.jade', 'utf8', function(error, data) {
		response.writeHead(200, {'Content-Type': 'text/html'});
		response.end(jade.compile(data)());
	});
});

app.get('/logout', function(request, response) {
	console.log(new Date(Date.now()).toUTCString());
	console.log(new Date(Date.now() - 3600000*24).toUTCString());
	response.cookie('id', null, {expires: new Date(Date.now() - 3600000*24)});
	response.redirect('/');
});

app.post('/login', function(request, response) {
	var id = request.param('id');
	var passwd = request.param('passwd');
	
	if (id === _id && passwd === _passwd) {
		response.cookie('id', id);
		response.redirect('/');
	}
	else {
		response.redirect('/login');
	}
});


app.use(function(request, response, next) {
    response.send( '<h1>' + request.number + ' : ' + response.number + '</h1>');
});

http.createServer(app).listen(52275, function() {
	console.log("express server is running ... ");
});