import { games } from "../data/games.js"
import { DataError } from "../models/dataError.js"

export default class GameService2 {
    constructor() {
        this.gamesStrateji = []
        this.gamesFps = []
        this.gamesSurvivor = []
        this.gamesErrors = []
        this.yedek = []
    }
    load() {
        for (let game of games) {

            this.gameNameCheck(game)

            if (this.validateGame(game) === false ) {
                if (game.gameType == "Strateji") {
                    this.gamesStrateji.push(game)
                } else if (game.gameType == "Fps" ) {
                    this.gamesFps.push(game)
                } else if (game.gameType == "Survivor") {
                    this.gamesSurvivor.push(game)
                } else {
                    this.gamesErrors.push(new DataError("Tür Hatası", game))
                }
            }
        }

        for (const iterator of this.yedek) {
            games.push(iterator)
        }
        
        console.log(this.gamesStrateji)
        console.log(this.gamesFps)
        console.log(this.gamesSurvivor)
        console.log(this.gamesErrors)
        console.log(this.yedek)
        console.log(games)
    }

    add(game) {
        if (!this.gameNameCheck(game)) {
            games.push(game)
            if (this.validateGame(game) === false) {

                if (game.gameType === "Strateji") {
                    this.gamesStrateji.push(game)

                } else if (game.gameType == "Fps") {
                    this.gamesFps.push(game)

                } else if (game.gameType == "Survivor") {
                    this.gamesSurvivor.push(game)


                } else {
                    this.gamesErrors.push(new DataError("Tür Hatası", game))
                }
            }
        }
        console.log(this.gamesStrateji)
        console.log(this.gamesFps)
        console.log(this.gamesSurvivor)
        console.log(this.gamesErrors)
    }

    validateGame(game) {
        let requiredFields = ["id", "gameName", "unitPrice", "gameType"];
        let hasErrors = false;
        for (const field of requiredFields) {
            if (!game[field]) {
                this.gamesErrors.push(new DataError("Gerekli yerler Girilmemis : " + field, game))
                hasErrors = true
            }
        }
        return hasErrors
    }
    
    gameNameCheck(game) {
        for(let i = 0 ; i<games.length;i++){
            if(game.gameName == games[i].gameName && games.indexOf(game) != i){
                this.gamesErrors.push("Bu oyun tekrar edildi : "+ games[i].gameName,games[i])
                this.yedek.push(games[i])
                games.splice(i,1)
            }
        }
    }
}