const fs = require('fs');

const users_file = 'users.json';
const users = JSON.parse(fs.readFileSync(users_file));

module.exports = 
{
    login: function (user) 
    {
        let result = checkUserData(user.email, user.password);

        var options;
        if(result.status === UserMatchStatus.MATCH_SUCCESS) 
        {
            options = { user: result.user };
        }
        else if(result.status === UserMatchStatus.INVALID_PASSWORD) 
        {
            options = { user: null, message: "Wrong password. Please try again or reset your password."};
        }
        else if(result.status === UserMatchStatus.INVALID_USER) 
        {
            options = { user: null, message: "There is no such account. Please try signup."};
        }
        return options;
    },

    signup: function (user) 
    {
        let result = checkUserData(user.email, null);

        if(result.status === UserMatchStatus.INVALID_USER) 
        {
            users.push(user);
            fs.writeFile(users_file, JSON.stringify(users), (err) =>
            {
                if(err) throw err;
            });
            return { user: user };
        }
        else return { user: null, message: "This e-mail is already registered. Please try login."};
    },

    registerDailySymptoms: function (dailySymptoms) 
    {
        return false;
    },

    checkCondition: function (user) 
    {
        const condition = Object.freeze({"HEALTHY":0, "IN_RISK":1, "EARLY_COVID":2, "SEVERE":3}); //Condition enum
        let lastRecord = user.data[user.data.length - 1];

        if(lastRecord["chest-pain"] || lastRecord["breathe-difficulty"]){
            return condition.SEVERE;
        }
        else if(lastRecord.dizziness || lastRecord["taste-loss"] || lastRecord.headache >= 1){
            return condition.EARLY_COVID;
        }
        else if(lastRecord.fever > 37.5 || lastRecord.coughing >= 1 || lastRecord["quick-tiring"]){
            return condition.IN_RISK;
        }
        else{
            return condition.HEALTHY;
        }
    }
};

const UserMatchStatus = 
{
    INVALID_USER : 0,
    INVALID_PASSWORD : 1,
    MATCH_SUCCESS : 2
};

function checkUserData(email, password)
{
    for(var i = 0; i < users.length; i++)
    {
        if(users[i].email === email)
        {
            if(users[i].password === password)
            {
                return { user: users[i], status: UserMatchStatus.MATCH_SUCCESS };
            }
            return { user: null, status: UserMatchStatus.INVALID_PASSWORD };
        }
    }
    return { user: null, status: UserMatchStatus.INVALID_USER };
}
