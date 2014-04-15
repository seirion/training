
process.setMaxListeners(10);
process.addListener('exit', function() {
	console.log('finished');
});

process.addListener('exit', function() {
	console.log('finished2');
});