const fs = require('fs');

const users_file = 'users.json';
const users = JSON.parse(fs.readFileSync(users_file));

    module.exports = {

        signin: function (email, password) {
            var result = checkUserData(email, password);

            var options;
            if(result.status === UserMatchStatus.MATCH_SUCCESS) {
                options = { email: result.email, name: result.name };
            }
            else if(result.status === UserMatchStatus.INVALID_PASSWORD) {
                options = { alert: true, message: "Wrong password. Please try again or reset your password."};
            }
            else if(result.status === UserMatchStatus.INVALID_USER) {
                options = { alert: true, message: "There is no such account. Please try signing up."};
            }
            return options;
        },

        signup: function (email, password, name, age, gender) {
            let user = { Email: email, Password: password, Name: name, Age: age, Gender: gender};
            var result = checkUserData(email, password);

            if(result.status === UserMatchStatus.INVALID_USER) {
                users.push(user);
                fs.writeFile(users_file, JSON.stringify(users), (err) =>
                {
                    if(err) throw err;
                });
                return { email: user.Email, name: user.Name };
            }
            else
                return { alert: true, message: "This e-mail is already registered. Please try signing in."};
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

    function checkUserData(email, password)
    {
        for(var i = 0; i < users.length; i++){
            if(users[i].Email === email)
            {
                if(users[i].Password === password)
                {
                    return { email: users[i].Email, name: users[i].Name, status: UserMatchStatus.MATCH_SUCCESS };
                }
                else
                    return { email: users[i].Email, name: users[i].Name, status: UserMatchStatus.INVALID_PASSWORD };
            }
        }
        return { email: null, name: null, status: UserMatchStatus.INVALID_USER };
    }

