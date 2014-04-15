
process.on('exit', function() {
	console.log('안녕히 계세요');
});

// event 강제 발생
process.emit('exit');
process.emit('exit');
process.emit('exit');
process.emit('exit');


// 프로그램 실행
console.log('keep on running');