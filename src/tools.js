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
        let result = checkUserData(email, null);

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
        return "CONDITION";
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
