var fs = require('fs');
var http = require('http');
var express = require('express');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var jade = require('jade');


var app = express();

app.use(cookieParser());
app.use(bodyParser({uploadDir: __dirname + '/upload'}));

app.get('/', function(request, response) {
	fs.readFile('upload.jade', 'utf8', function(error, data) {
		response.writeHead(200, {'Content-Type': 'text/html'});
		response.end(jade.compile(data)());
	});
});

app.post('/', function(request, response) {
	var comment = request.param('comment');
	var image = request.files.image;
	
	if (image) {
		var name = image.name;
		var path = image.path;
		var type = image.type;
		
		if (type.indexOf('image') != -1) {
			var outputPath = __dirname + '/upload/' + Date.now() + '_' + name;
			
			fs.rename(path, outputPath, function(error) {
				response.redirect('/');
			});
		}
		else {
			fs.unlink(path, function(error) {
				response.send(400);
			});
		}
	}
});


app.use(function(request, response, next) {
    response.send( '<h1>' + request.number + ' : ' + response.number + '</h1>');
});

http.createServer(app).listen(52275, function() {
	console.log("express server is running ... ");
});