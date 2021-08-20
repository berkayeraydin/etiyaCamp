

export default class ProductService{

    constructor(){
        this.products = []
    }

    add(product){

        if(product.unitPrice <=0){
            console.log("Lütfen Fiyat Giriniz : ")
            return
        }
        
        this.products.push(product)
    }


    list(){
        return this.products
    }
}