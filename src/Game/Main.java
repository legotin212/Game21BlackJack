package Game;
//игра 21

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Deck mydeck = new Deck();

        while(true) {
            Game mygame = new Game(mydeck);
            if(mygame.AskForContinue()!=true){
                break;
            }
        }
    }

}
