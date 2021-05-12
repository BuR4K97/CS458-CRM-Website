const fs = require('fs');

const users_file = 'users.json';
const users = JSON.parse(fs.readFileSync(users_file));

module.exports = 
{
    signin: function (email, password) {
        var result;
        if(emailOrPhone(email))
            result = checkUserData(email, null, password);
        else
          result = checkUserData(null, email, password);

        var options;
        if(result.status === UserMatchStatus.MATCH_SUCCESS) {
            options = { email: result.email, phone: result.phone };
        }
        else if(result.status === UserMatchStatus.INVALID_PASSWORD) {
            options = { alert: true, message: "Wrong password. Please try again or reset your password."};
        }
        else if(result.status === UserMatchStatus.INVALID_USER) {
            options = { alert: true, message: "There is no such account. Please try signing up."};
        }
        return options;
    },

    signup: function (email, phone, password, name, age, gender) {
        var result;
        let user = { Email: email, Password: password, Name: name, Age: age, Gender: gender};
        if(phone === undefined || phone === null || phone.length === 0)
            result = checkUserData(email, null, null);
        else {
            result = checkUserData(email, phone, null);
            user.Phone = phone;
        }

        if(result.status === UserMatchStatus.INVALID_USER) {
            users.push(user);
            fs.writeFile(users_file, JSON.stringify(users), (err) =>
            {
                if(err) throw err;
            });
            return { email: user.Email, phone: user.Phone };
        }
        else if(result.email != null)
            return { alert: true, message: "This e-mail is already registered. Please try signing in."};
        else
            return { alert: true, message: "This phone number is already registered. Please try signing in."};
    },

    registerSymptoms: function (email, symptoms, date) {
        return false;
    },

    checkCondition: function (email) {
        return "CONDITION";
    }
};
    const UserMatchStatus = {
        INVALID_USER : 0,
        INVALID_PASSWORD : 1,
        MATCH_SUCCESS : 2
    };

    function checkUserData(email, phone, password)
    {
        for(var i = 0; i < users.length; i++)
        {
            if(users[i].Email === email)
            {
                if(users[i].Password === password)
                {
                    return { email: users[i].Email, phone: users[i].Phone, status: UserMatchStatus.MATCH_SUCCESS };
                }
                return { email: users[i].Email, phone: null, status: UserMatchStatus.INVALID_PASSWORD };
            }
            if(users[i].Phone === phone)
            {
                if(users[i].Password === password)
                {
                    return { email: users[i].Email, phone: users[i].Phone, status: UserMatchStatus.MATCH_SUCCESS };
                }
                return { email: null, phone: users[i].Phone, status: UserMatchStatus.INVALID_PASSWORD };
            }
        }
        return { email: null, phone: null, status: UserMatchStatus.INVALID_USER };
    }

    function emailOrPhone(input)
    {
        return /[^\d.+()]+/.test(input);
    }
