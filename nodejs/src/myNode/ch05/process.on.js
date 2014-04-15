process.on('exit', function() {
	console.log('good bye ~');
});

process.on('uncaughtException', function(error) {
	console.log('uncaughtException has been occured...');
});

var count = 0;

var id = setInterval(function(){
	count++;
	if (count === 3) {
		clearInterval(id);
	}
	//error.error.error();
	console.log(count);
}, 2000);