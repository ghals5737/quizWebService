var express = require('express');
var path = require('path');
var router = express.Router();
var app = express();

var port = normalizePort(process.env.PORT || '8800');

/* GET home page. */
router.get('/', function (req, res, next) {
    res.sendFile(path.join(__dirname, '../public', 'index.html'));
});

module.exports = router;