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
app.post('/test/signin', function (req, res)
{
    let result = tools.signin(req.body.email, req.body.password);
    res.send({result: result});
});

//signup function testing route
app.post('/test/signup', function (req, res)
{
    let result = tools.signup(req.body.email, req.body.phone, req.body.password, req.body.name, req.body.age, req.body.gender);
    res.send({result: result});
});

//registerSymptoms function testing route
app.post('/test/registerSymptoms', function (req, res)
{
    let result = tools.registerSymptoms(req.email, req.symptoms, req.date);
    res.send({result: result});
});

//checkCondition function testing route
app.post('/test/checkCondition', function (req, res)
{
    let condition = tools.checkCondition(req.email);
    res.send({condition: 0});
});

app.listen(port, () => 
{
    console.log(`Test server listening at http://localhost:${port}`)
});