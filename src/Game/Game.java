package Game;

import java.util.Scanner;

public class Game {
    Player player;
    Deck gamedeck;
    Dealer dealer;

    Boolean newGameFlag;

    public Game(Deck deck) throws InterruptedException {

        this.gamedeck = deck;
        if (AskForResetBalance()){ this.player=new Player(gamedeck,true);
        }
        else{this.player=new Player(gamedeck);}
        this.dealer =new Dealer(gamedeck);
        DecideWinner();
        player.balance.SaveBalance();




    }
    public Boolean AskForResetBalance(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to reset your balance?");
        System.out.println("Press 1 for 'Yes' and press 2 for 'No");
        if(sc.nextInt()==1){
            System.out.println("New Game has been started");
            System.out.println("Your balance now equals 1000");
            return true;
        }
        else{return false;}
    }

    public boolean AskForContinue(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to play again?");
        System.out.println("Press 1 for 'Yes' and press 2 for 'No");
        if(sc.nextInt()==2){
            System.out.println("Thank you. Farewell!");
            return false;
        }
        return true;
    }

    private void DecideWinner(){
        if(dealer.getScore()== player.getScore()){
            System.out.println("It is a draw");
            player.balance.balance += player.getCurrentBet();
            System.out.println("Your current balance is " + player.getBalance());

        }
        else if((dealer.getScore()!= 21 && player.getScore() == 21) ||(dealer.getScore()>21 && player.getScore()<21) ||
                (dealer.getScore()<21 && player.getScore()<21 && player.getScore()> dealer.getScore()) ||
                (dealer.getScore()>21 && player.getScore()> 21 && player.getScore()< dealer.getScore())){
            System.out.println("You won " + player.getCurrentBet()*2);
            player.balance.balance += player.getCurrentBet()*2;
            System.out.println("Your current ballance is " + player.getBalance());
        }
        else{
            System.out.println("You lost " + player.getCurrentBet());
            System.out.println("Your current ballance is " + player.getBalance());
        }


    }
}
