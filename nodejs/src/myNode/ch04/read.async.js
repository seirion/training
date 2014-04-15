var fs = require('fs');

var text = fs.readFile('textfile.txt', 'utf8', function(error, data) {
	console.log(data);
});