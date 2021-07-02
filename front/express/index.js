var express = require('express');
var path = require('path');
var router = express.Router();
var app = express();

var port = normalizePort(process.env.PORT || '8800');
function normalizePort(val) {
    var port = parseInt(val, 10);
  
    if (isNaN(port)) {
      // named pipe
      return val;
    }
  
    if (port >= 0) {
      // port number
      return port;
    }
  
    return false;
  }
/* GET home page. */
router.get('/', function (req, res, next) {
    res.sendFile(path.join(__dirname, '../quiz/dist', 'index.html'));
});

module.exports = router;