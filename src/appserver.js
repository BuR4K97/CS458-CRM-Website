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
    if(req.session.user) res.redirect('home');
    else res.redirect('login');
});

//Login
app.get('/login', function (req, res)
{
    if(req.session.user) res.redirect('home');
    else res.render('login');
});

//Signup
app.get('/signup', function (req, res)
{
    if(req.session.user) res.redirect('home');
    else res.render('signup');
});

//Home
app.get('/home', function (req, res)
{
    if(req.session.user == null) res.redirect('login');
    else 
    {
        let condition = tools.checkCondition(req.session.user).condition;
        res.render('home', { user: req.session.user, condition: condition });
    }
});

//Logout
app.get('/logout', function (req, res)
{
    req.session.user = null;
    res.redirect('login');
});

//Login POST
app.post('/login', function (req, res)
{
    var options = tools.login(req.body);
    if(options.user) 
    {
        req.session.user = options.user;
        res.redirect('home');
    }
    else res.render('login', options);
});

//Signup POST
app.post('/signup', function (req, res)
{
    const Gender =
    {
        MALE: 0,
        FEMALE: 1
    }

    if(req.body.gender === 'Male') req.body.gender = Gender.MALE;
    else req.body.gender = Gender.FEMALE;

    var options = tools.signup(req.body);
    if(options.user) 
    {
        req.session.user = options.user;
        res.redirect('home');
    }
    else res.render('signup', options);
});

//Home POST
app.post('/home', function (req, res)
{
    const Headache = { NONE: 0, MILD: 1, SEVERE: 2 };
    const Coughing = { NONE: 0, MILD: 1, SEVERE: 2 };

    var headache, coughing, dizziness, tasteLoss, breatheDifficulty, chestPain, quickTiring;
    if(req.body.headache === "None") headache = Headache.NONE;
    if(req.body.headache === "Mild") headache = Headache.MILD;
    if(req.body.headache === "Severe") headache = Headache.SEVERE;
    if(req.body.coughing === "None") coughing = Coughing.NONE;
    if(req.body.coughing === "Mild") coughing = Coughing.MILD;
    if(req.body.coughing === "Severe") coughing = Coughing.SEVERE;
    dizziness = req.body.hasOwnProperty("dizziness");
    tasteLoss = req.body.hasOwnProperty("tasteLoss");
    breatheDifficulty = req.body.hasOwnProperty("breatheDifficulty");
    chestPain = req.body.hasOwnProperty("chestPain");
    quickTiring = req.body.hasOwnProperty("quickTiring");
    let options = tools.registerDailySymptoms({ email: req.session.user.email, date: req.body.date, symptoms:
    {
        fever: req.body.fever,
        headache: headache,
        coughing: coughing,
        dizziness: dizziness,
        tasteLoss: tasteLoss,
        breatheDifficulty: breatheDifficulty,
        chestPain: chestPain,
        quickTiring: quickTiring,
    }});
    req.session.user = options.user;
    let condition = tools.checkCondition(req.session.user).condition;
    res.render('home', { user: req.session.user, condition: condition });
});

app.listen(port, () => 
{
    console.log(`Web application listening at http://localhost:${port}`)
});

