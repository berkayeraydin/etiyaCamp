export default class UserService{

    constructor(){

        this.users = [] 
    }

    add(user){

        this.users.push(user)
    }

    list(){

        return this.users
    }

    remove(id){ 

        let indexToRemove = -1 

        for (let index = 0; index < this.users.length; index++) {

            if(this.users[index].id===id){
                indexToRemove=index
            }
        }

        if (indexToRemove===-1){

            console.log("Böyle bir kullanıcı yok")
        }else{

            this.users.splice(indexToRemove,1);
        }    
    }
}