var fs = require('fs');
var http = require('http');
var connect = require('connect');
var express = require('express');


var app = express();

app.use(connect.logger());

var db = (function() {
	var _db = {};
	var storage = [];
	var count = 1;
	
	_db.get = function(id) {
		if (id) {
			id = (typeof id === 'string' ? Number(id) : id);
			
			for (var i in storage) {
				if (storage[i].id === id) {
					return storage[i];
				}
			}
		}
	};

	_db.insert = function(data) {
		data.id = count++;
		storage.push(data);
		return data;
	};
	
	_db.remove = function(id) {
    	id = (typeof id === 'string' ? Number(id) : id);
		for (var i in storage) {
			if (storage[i].id === id) {
				storage.splice(i, 1);
				return true;
			}
		}
		return false;
	};

	return _db;
})();

app.get('/user', function(request, response) {
	console.log('get /user');
	response.send(db.get());
});

app.get('/user/:id', function(request, response) {
	response.send(db.get(request.param('id')));
});

app.post('/user', function(request, response) {
	var name = request.param('name');
	var age = request.param('age');
	
	console.log(name);
	
	response.send(name + ' ' + age);
	return;
	if (name && age) {
		response.send(db.insert({name: name, age: age}));
	}
	else {
		throw new Erorr('error');
	}
});

app.put('/user', function(request, response) {
});

app.del('/user', function(request, response) {
});

http.createServer(app).listen(52279, function() {
	console.log("express server is running ... ");
});