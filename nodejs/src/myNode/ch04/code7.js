var url = require('url');

var parsedObject = url.parse('http://www.hanb.co.kr/book/look.html?name=longlee&age=20', true);
console.log(parsedObject);