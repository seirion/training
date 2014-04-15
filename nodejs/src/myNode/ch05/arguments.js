

process.on('exit', function() {
	console.log('안녕히 계세요');
	//console.log(arguments);
	
	var output = ' ';
	for (var i in arguments) {
		output += arguments[i] + ' ';
	}
	console.log(output);
});

process.exit();

// event 강제 발생
process.emit('exit', 1);
process.emit('exit', 1, 2);
process.emit('exit', 1, 2, 3);
process.emit('exit', 1, 2, 3, 4);


// 프로그램 실행
console.log('keep on running');