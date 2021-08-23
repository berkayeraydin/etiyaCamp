export default class Game{


    constructor(id,name,unitPrice,minAge){

        if(!id || !name || !unitPrice || !minAge){
            console.log("Oyun Bilgileri Boş Bırakılamaz.")
        }
        //prototyping
        this.id=id;
        this.name=name;
        this.unitPrice=unitPrice;
        this.minAge=minAge;
    }
}