import { users } from "../data/users.js"
import { DataError } from "../models/dataError.js"


export default class UserService2 {
    constructor() {
        this.customers = []
        this.employees = []
        this.errors = []
    }

    load() {
        for (let user of users) {
            if (this.validateAge(user) === true) {
            } else {
                switch (user.type) {
                    case "customer":
                        if (this.validateCustomer(user) === false && this.validateEmail(user)===false) {
                            this.customers.push(user)
                        }
                        break;

                    case "employee":
                        if (this.validateCustomer(user) === false) {
                            this.employees.push(user)
                        }
                        break;

                    default:
                        this.errors.push(new DataError("Invalid data", user))
                        break;
                }
            }

        }
        console.log(this.customers)
        console.log(this.employees)
        console.log(this.errors)
    }
    validateCustomer(user) {
        let requiredFields = ["id", "firstName", "lastName", "age"];
        let hasErrors = false;
        for (let field of requiredFields) {
            if (!user[field]) {
                this.errors.push(new DataError("Geçersiz alan: " + field, user))
                hasErrors = true;
            }
        }
        return hasErrors;
    }

    validateAge(user) {
        let hasErrors = false;
        let age = user.age
        if (age < 0 || !Number.isInteger(age)) {
            this.errors.push(new DataError("Geçersiz yaş: ", user))
            hasErrors = true;
        }
        return hasErrors
    }

    validateEmail(user) {
        let hasErrors = false;
        let pattern =  /^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)+.(com|org|net|edu|gov|mil|biz|info|mobi)$/;
        let email = user.email;
        if (!email.match(pattern)) {
            this.errors.push(new DataError("Geçersiz email: ", user))
            hasErrors = true
        }
        return hasErrors;
    }
}