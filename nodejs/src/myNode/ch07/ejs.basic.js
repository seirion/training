var fs = require('fs');
var http = require('http');
var ejs = require('ejs');


http.createServer(function(request, response) {
	fs.readFile('page.ejs', 'utf8', function(error, data) {
		response.writeHead(200, {'Content-Type': 'text/html'});
		response.end(ejs.render(data, {name: "babo11"}));
	});
}).listen(52274, function() {
	console.log("server is running ...");
});