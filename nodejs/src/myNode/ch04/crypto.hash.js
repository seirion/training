var crypto = require('crypto');

var shasum = crypto.createHash('sha1');

shasum.update('crypto_hash');
var output = shasum.digest('hex');

console.log(shasum);
console.log('crypto_hash:', output);
