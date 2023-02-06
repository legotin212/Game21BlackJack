package Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    ArrayList<Cards> cards;
    private int Score;
    String name;
    int CurrentBet;
    Balance balance;
    private Deck deck;
    //To start a new game
    public Player(Deck deck,Boolean flag) throws InterruptedException {
        this.balance = new Balance(1000);
        this.deck = deck;
        cards = new ArrayList<>();
        ChooceBet();
        takeCard();
        takeCard();
        CountScore(cards);
    }

    //Continue the game
    public Player(Deck deck) throws InterruptedException {
        try(ObjectInputStream objectInputStream =
                    new ObjectInputStream(
                            new FileInputStream("Balance.bin")
                    )) {
            this.balance =(Balance) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.deck = deck;
        cards = new ArrayList<>();
        ChooceBet();
        takeCard();
        takeCard();
        CountScore(cards);

    }

    private void takeCard() throws InterruptedException {
        cards.add(deck.GiveCard());
        System.out.println("Your cards now are: " + cards);
        Thread.sleep(1000);

    }

    private void askForTakingCard(int Scr) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like an extra card?");
        System.out.println("Press 1 for 'Yes' and press 2 for 'No");
        int answer = sc.nextInt();
        if (answer == 1) {
            takeCard();
            CountScore(cards);

        }
        else {Score = Scr;}
    }


    private void CountScore(ArrayList<Cards> cards) throws InterruptedException {
        int Scr = 0;
        for (int i = 0; i<cards.size();i++){
            switch(cards.get(i)){
                case Six -> Scr += 6;
                case Seven -> Scr +=7;
                case Eight -> Scr +=8;
                case Nine -> Scr +=9;
                case Ten -> Scr += 10;
                case Jack -> Scr +=2;
                case Queen -> Scr +=3;
                case King -> Scr +=4;
                case Ace -> Scr +=11;
            }
        }
        if(Scr >21 && cards.contains(Cards.Ace)){
            Scr -=10;
        }
        if (Scr <21){
            System.out.println("Your Score is " + Scr);
            askForTakingCard(Scr);
        }
        else {
            System.out.println("Your Score is " + Scr);
            Score = Scr;

        }

    }
    private void ChooceBet() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Set your bet");
        int bet = sc.nextInt();
        if (bet <= balance.balance) {
           this.CurrentBet = bet;
           System.out.println("Your bet is accepted");
           System.out.println("Current bet: " + getCurrentBet());
           balance.balance -= bet;
           System.out.println("Current ballance is: " + getBalance());


        } else {
            System.out.println("Not enough money");
            System.out.println("Set a valid bet");
            ChooceBet();
        }
    }

    public int getCurrentBet() {
        return CurrentBet;
    }

    public int getBalance() {
        return balance.getBalance();
    }

    public int getScore() {
        return Score;
    }
}
