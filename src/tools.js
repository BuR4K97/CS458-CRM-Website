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
            options = { user: result.user, message:"Successful Login" };
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
            user.data = []
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
        let result = retrieveUserData(dailySymptoms.email);
        let user = result.user;
        if(result.status === UserMatchStatus.MATCH_SUCCESS) 
        {
            var register = false;
            for(var i = 0; i < user.data.length; i++) 
            {
                let data = user.data[i];
                if(dailySymptoms.date === data.date) 
                {
                    user.data[i] = { date: dailySymptoms.date, symptoms: dailySymptoms.symptoms };
                    register = true;
                    break;
                }
            }
            if(!register) 
            {
                user.data.push({ date: dailySymptoms.date, symptoms: dailySymptoms.symptoms });
            }
            user.data.sort((prev, next) =>
            {
                let prevDate = Date.parse(prev.date);
                let nextDate = Date.parse(next.date);
                if(prevDate < nextDate) return -1;
                if(nextDate < prevDate) return 1;
                return 0;
            });
            fs.writeFile(users_file, JSON.stringify(users), (err) => { if(err) throw err; });
            return { user: user, result: true, message:"Success" };
        }
        else return { user: null, result: false, message: "Invalid user" };
    },

    checkCondition: function (user) 
    {
        const condition = Object.freeze({"HEALTHY":0, "IN_RISK":1, "EARLY_COVID":2, "SEVERE":3}); //Condition enum
        let result = retrieveUserData(user.email);
        if(result.status === UserMatchStatus.MATCH_SUCCESS) 
        {
            let data = result.user.data;
            let lastRecord = data[data.length - 1].symptoms;
            if(data.length < 1) return condition.HEALTHY;
            if(lastRecord["chestPain"] || lastRecord["breatheDifficulty"])
            {
                return { condition: condition.SEVERE } ;
            }
            else if(lastRecord.dizziness || lastRecord["tasteLoss"] || lastRecord.headache >= 1)
            {
                return { condition: condition.EARLY_COVID };
            }
            else if(lastRecord.fever > 37.5 || lastRecord.coughing >= 1 || lastRecord["quickTiring"])
            {
                return { condition: condition.IN_RISK };
            }
            else
            {
                return { condition: condition.HEALTHY };
            }
        }
        else return { condition: -1, message: "Invalid user"};
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

function retrieveUserData(email) 
{
    for(var i = 0; i < users.length; i++)
    {
        if(users[i].email === email)
        {
            return { user: users[i], status: UserMatchStatus.MATCH_SUCCESS };
        }
    }
    return { user: null, status: UserMatchStatus.INVALID_USER };
}
