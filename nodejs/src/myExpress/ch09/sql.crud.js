var fs = require('fs');
var http = require('http');
var express = require('express');
var bodyParser = require('body-parser');
var ejs = require('ejs');
var jade = require('jade');
var mysql = require('mysql');


var app = express();
var client = mysql.createConnection({
	user: 'root',
	password: '6789',
	database: 'Company'
});

app.use(bodyParser());

app.get('/', function(request, response) {
	fs.readFile('list.html', 'utf8', function(error, data) {
		client.query('select * from products', function(error, results) {
			response.send(ejs.render(data, {data: results}));
		});
	});
});

app.get('/delete/:id', function(request, response) {
	client.query('delete from products where id = ?', [request.param('id')], function() {
		response.redirect('/');
	});
});

app.get('/insert', function(request, response) {
	fs.readFile('insert.html', 'utf8', function(error, data) {
		response.send(data);
	});
});

app.post('/insert', function(request, response) {
	var body = request.body;
	
	client.query('insert into products (name, modelNumber, series) values (?, ?, ?)',
			[body.name, body.modelnumber, body.series],
			function() {
				response.redirect('/');
	});
});

app.get('/edit/:id', function(request, response) {
	fs.readFile('edit.html', 'utf8', function(error, data) {
		client.query('select * from products where id = ?', [request.param('id')], function(error, results) {
			response.send(ejs.render(data, {data: results[0]}));
		});
	});
});

app.post('/edit/:id', function(request, response) {
	var body = request.body;
	console.log(body.name, body.modelnumber, body.series, request.param('id'));
	client.query('update products set name=?, modelnumber=?, series=? where id=?',
			[body.name, body.modelnumber, body.series, request.param('id')],
			function(error) {
				console.log(error);
				response.redirect('/');
	});
	
});

http.createServer(app).listen(52273, function() {
	console.log("express server is running ... ");
});
