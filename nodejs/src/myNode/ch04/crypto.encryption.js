
var crypto = require('crypto');

var key = '아무도 알 지 못하는 나만의 키';
var name = 'PASSWORD';
var age = '20';


// 암호화
var cipher = crypto.createCipher('aes192', key);
cipher.update(name, 'utf8', 'base64');
cipher.update(age, 'utf8', 'base64');
var cipheredOutput = cipher.final('base64');


// 풀기
var decipher = crypto.createDecipher('aes192', key);
decipher.update(cipheredOutput, 'base64', 'utf8');
var decipheredOutput = decipher.final('utf8');

console.log('from: ' + name + age);
console.log('cipheredOutput: ' + cipheredOutput);
console.log('decipheredOutput: ' + decipheredOutput);

