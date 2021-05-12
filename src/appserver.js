const http = require('http');
const fs = require('fs');
const express = require('express');
const tools = require('./tools');

// Parse URL-encoded bodies (as sent by HTML forms)
const app = express();
app.set('view engine', 'ejs');
app.use(express.urlencoded({ extended : true }));

const hostname = '127.0.0.0';
const port = 3000;

const users_file = 'users.json';
const users = JSON.parse(fs.readFileSync(users_file));

//Index page
app.get('/', function (req, res)
{
    res.render('index');
});

//Signup page
app.get('/signup', function (req, res)
{
    res.render('signup');
});

app.post('/signup', function (req, res)
{

    var options = tools.signup(req.body.email, req.body.phone, req.body.password, req.body.name, req.body.age, req.body.gender);

    if( options.message === undefined)
        res.render('home', options);
    else
        res.render('signup', options);

});

//Login page
app.get('/login', function (req, res)
{
    res.render('login');
});

app.post('/login', function (req, res)
{
    var options = tools.signin(req.body.email, req.body.pass);

    if(options.message === undefined) {
        res.render('home', options);
    }
    else {
        res.render('login', options);
    }
});

//Forgot password page
app.get('/resetpass', function (req, res)
{
    res.render('resetpass');
});

app.listen(port, () => {
    console.log(`Web application listening at http://localhost:${port}`)
});

