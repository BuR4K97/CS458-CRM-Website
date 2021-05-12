const http = require('http');
const express = require('express');
const session = require('express-session');
const tools = require('./tools');

// Parse URL-encoded bodies (as sent by HTML forms)
const app = express();
app.set('view engine', 'ejs');
app.use(express.urlencoded({ extended : true }));
app.use(session({ secret: 'secretkey', saveUninitialized: true, resave: true }));

const hostname = '127.0.0.0';
const port = 3000;

//Index
app.get('/', function (req, res)
{
    if(req.session.email) res.redirect('home');
    else res.redirect('login');
});

//Login
app.get('/login', function (req, res)
{
    if(req.session.email) res.redirect('home');
    else res.render('login');
});

//Signup
app.get('/signup', function (req, res)
{
    if(req.session.email) res.redirect('home');
    else res.render('signup');
});

//Home
app.get('/home', function (req, res)
{
    if(req.session.email == null) res.redirect('login');
    else res.render('home');
});

//Logout
app.get('/logout', function (req, res)
{
    req.session.email = null;
    res.redirect('login');
});

//Login POST
app.post('/login', function (req, res)
{
    var options = tools.login(req.body);
    if(options.user) 
    {
        req.session.email = options.user.email;
        res.redirect('home');
    }
    else res.render('login', options);
});

//Signup POST
app.post('/signup', function (req, res)
{
    const GENDER =
    {
        MALE: 0,
        FEMALE: 1
    }

    if(req.body.gender === 'Male') req.body.gender = GENDER.MALE;
    else req.body.gender = GENDER.FEMALE;
    
    var options = tools.signup(req.body);
    if(options.user) 
    {
        req.session.email = options.user.email;
        res.redirect('home');
    }
    else res.render('signup', options);
});

app.listen(port, () => 
{
    console.log(`Web application listening at http://localhost:${port}`)
});

