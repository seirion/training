
var http = require('http');

http.createServer(function(request, response) {
    var cookie = request.headers.cookie;
    
    if (cookie) {
        cookie = cookie.split(';').map(function(element) {
            element = element.split('=');
            return {
                key: element[0],
                value: element[1]
            };
        });
    }
    response.writeHead(200,
            {'Content-Type': 'text/html',
            'Set-Cookie': [ 'name = babo', 'age = 10' ]
    });
    response.end('<H1>' + request.headers.cookie + '</H1>');
}).listen(52274);