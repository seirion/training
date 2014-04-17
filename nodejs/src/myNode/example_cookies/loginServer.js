/*
 * 이곳에 다음의 기능을 구현하는 코드를 작성하세요.
 * 
 * 1. 클라이언트의 각 요청에 따른 적당한 컨텐츠를 제공할 수 있어야 한다.
 * 2. "localhost:52273" 으로 GET 방식의 요청이 들어오면 login.html 문서를 제공하여야 한다.
 * 3. "localhost:52273" 으로 POST 방식의 요청이 들어오면 사용자가 입력한 id, passwd 정보를 미리 약속된 값과 비교하여
 *    올바른 로그인 요청인 경우 index.html 페이지를 보여주고 잘못된 로그인 요청인 경우 다시 로그인 페이지로 보내야 한다.
 * 4. "localhost:52273/logout" 으로 요청이 들어오면 로그인 관련 쿠키를 제거하고 login.html 페이지로 보내야 한다. 
 * 5. 로그인에 성공한 경우 쿠키를 이용하여 "login=longlee" 와 같은 정보를 기록하여라.
 * 
 */

// 미리 약속된 로그인 정보
var _id = 'babo';
var _passwd = '1';


var http = require('http');
var url = require('url');
var fs = require('fs');
var querystring = require('querystring');

http.createServer(function(request, response) {
	if (request.method === 'GET') {
		fs.readFile('login.html', function(error, data) {
            response.writeHead(200, {'Content-Type': 'text/html'});
            response.end(data);
		});
	}
	else if (request.method === 'POST') {
		request.on('data', function(data){
			console.log(data);
			var q = querystring.parse(data.toString(), '&', '=');
			console.log(q.id); 
			console.log(q.passwd); 
			if (q.id === _id && q.passwd === _passwd) {
				console.log('ok'); 
                fs.readFile('index.html', function(error, data) {
                    response.writeHead(200, {'Content-Type': 'text/html',
                		'Set-Cookie': [
    		               'id = ' + q.id
    		               ]
                    });
                    response.end(data);
                });
			}
			else {
				console.log('not ok'); 
                response.writeHead(302, {'Location': 'login.html'});
                response.end();
			}
		});
	}
	var query = url.parse(request.url, true).query;
	
}).listen(52273);