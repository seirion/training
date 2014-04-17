var fs = require('fs');
var http = require('http');
var jade = require('jade');


http.createServer(function(request, response) {
	fs.readFile('page.jade', 'utf8', function(error, data) {
		response.writeHead(200, {'Content-Type': 'text/html'});
		response.end(jade.compile(data)({name: "babo!!", description:"Hello !!"}));
	});
}).listen(52274, function() {
	console.log("server is running ...");
});