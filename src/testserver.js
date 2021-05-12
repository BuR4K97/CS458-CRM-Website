const http = require('http');
const fs = require('fs');
const express = require('express');
const tools = require('./tools');

// Parse URL-encoded bodies (as sent by HTML forms)
const app = express();
app.use(express.json())

const hostname = '127.0.0.0';
const port = 3002; //test server port

const users_file = 'users.json';
const users = JSON.parse(fs.readFileSync(users_file));

//signin function testing route
app.post('/login', function (req, res)
{
    let result = tools.login(req.body);
    res.send(result);
});

//signup function testing route
app.post('/signup', function (req, res)
{
    let result = tools.signup(req.body);
    res.send(result);
});

//registerSymptoms function testing route
app.post('/registerDailySymptoms', function (req, res)
{
    let result = tools.registerDailySymptoms(req.body);
    res.send(result);
});

//checkCondition function testing route
app.post('/checkCondition', function (req, res)
{
    let condition = tools.checkCondition(req.body);
    res.send({condition: 0});
});

app.listen(port, () => 
{
    console.log(`Test server listening at http://localhost:${port}`)
});