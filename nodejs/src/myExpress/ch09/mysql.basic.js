var mysql = require('mysql');


var client = mysql.createConnection({
	user: 'root',
	password: '6789'
});


client.query('use Company');
client.query('select * from products', function(error, result, fields) {
	if (error) {
		console.log('failed');
	}
	else {
		console.log(result);
	}
});