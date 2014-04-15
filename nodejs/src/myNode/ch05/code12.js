
process.on('exit', function() {
	console.log('good bye ~');
});

// event 강제 발생
process.emit('exit');
process.emit('exit');
process.emit('exit');
process.emit('exit');


// 프로그램 실행
console.log('keep on running');