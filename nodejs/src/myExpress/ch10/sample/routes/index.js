
/*
 * GET home page.
 */

exports.index = function(req, res){
  res.render('index', { title: 'Express' });
};

exports.product = function(req, res){
  res.render('product', { title: 'Product Page' });
};
