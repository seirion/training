
var fs = require('fs');

var data = 'Hello World';

fs.writeFile('textfile_write.txt', data, 'utf8', function(error) {
	console.log('write async finished.');
});

fs.writeFileSync('textfile_write_sync.txt', data, 'utf8');
console.log('write sync finished.');