const fs = require('fs');

const users_file = 'users.json';
const users = JSON.parse(fs.readFileSync(users_file));

module.exports = 
{
    signin: function (email, password) 
    {
        return false;
    },

    signup: function (email, password, name, age, gender) 
    {
        return false;
    },

    registerSymptoms: function (email, symptoms, date) 
    {
        return false;
    },

    checkCondition: function (email) 
    {
        return "CONDITION";
    }
};
