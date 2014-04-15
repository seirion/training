var events = require('events');
var emitter = new events.EventEmitter();


// var emitter = new process.EventEmitter();


emitter.on('tick', function() {
	console.log('이벤트가 발생하였습니다.');
});


emitter.emit('tick');