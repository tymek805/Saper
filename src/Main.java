// Zespół: 
// Wojciech Krzos <3
// Filip Kubecki <3
// Tymoteusz Lango <3

public class Main {
    public static void main(String[] args){
        GUI gui = new GUI(8, 8, 10);
        GameLogic gameLogic = new GameLogic();
        
        gameLogic.randomBoardOfMines();
        gameLogic.howManyMines(2, 5);
        gameLogic.initializeBoardOfNumbers();
        gameLogic.endGame();
    }
}
