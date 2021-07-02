var express = require('express');
var path = require('path');
var app = express();
var port = 8800
/* GET home page. */
app.get('/', function (req, res, next) {
    res.sendFile(path.join(__dirname, '../quiz/dist', 'index.html'));
});
app.listen(port,()=>{})