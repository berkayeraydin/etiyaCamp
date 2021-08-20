// import Product from "./models/product.js"
// import ProductService from "./services/productService.js"

import Game from "./models/game.js"
import GameService from "./services/gameService.js"
import User from "./models/user.js"
import UserService from "./services/userService.js"
import SellingService from "./services/sellingService.js"


// let product1 = new Product(1,"Elma",10)
// let product2 = new Product(2,"Armut",10)
// let product3 = new Product(3,"Üzüm",8)
// let product4 = new Product(4,"İncir",14)

let game1 = new Game(1,"CS",10,20)
let game2 = new Game(2,"FİFA",10,15)
let game3 = new Game(3,"PES",10,15)

let user1 = new User(1,"Berkay",23)
let user2 = new User(2,"Sena",19)

// console.log(product.id)
// console.log(product.name)
// console.log(product.unitPrice)
// console.log(product)

// let productService = new ProductService();
// productService.add(product1)
// productService.add(product2)
// productService.add(product3)
// productService.add(product4)

let gameService = new GameService();
gameService.add(game1)
gameService.add(game2)
gameService.add(game3)

gameService.remove(10)
gameService.remove(2)

console.log(gameService.list())

let userService = new UserService();

userService.add(user1)
userService.add(user2)

console.log(userService.list())

let sellingService = new SellingService();

sellingService.sell(user2,game1)
sellingService.sell(user1,game1)

sellingService.sellList()


// console.log(productService.list())




