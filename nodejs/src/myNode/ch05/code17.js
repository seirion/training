
var a = require('./code16');

a.timer.on('tick', function() {
	console.log('이벤트가 호출 되었습니다.');
});