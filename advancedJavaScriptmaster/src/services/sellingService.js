
export default class SellingService{

    constructor(){
        this.sales = []
    }

    sell(user,game){

        if(user.age < game.minAge){
            console.log("Merhaba "+user.name+", "+game.name +" oyunun yaş sınırı "+ game.minAge + " dir. Yaşınız "+ user.age + " olduğundan oyunu satın alamazsınız.")
        }else{
            console.log("Merhaba "+user.name + ", Oyunu satın aldınız. Aldığınız oyununun bilgileri; Oyun ismi '"+game.name + "', Oyun tutarı "+game.unitPrice+ " TL dir.")
        }
    }

    sellList(){
        return this.sales;
    }

}