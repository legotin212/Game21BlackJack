package Game;

import java.util.ArrayList;

public class Dealer {
    ArrayList<Cards> cards;
    Deck deck;
    private int Score;

    public Dealer(Deck deck) {
        this.deck = deck;
        cards = new ArrayList<>();
        takeCard();
        takeCard();
        System.out.println("Dealer cards " + cards);
        Score = CountScore(cards);

    }

    private void takeCard(){
        cards.add(deck.GiveCard());
        System.out.println("Dealer has taken a card");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private int CountScore(ArrayList<Cards> cards){
        int Score = 0;
        for (int i = 0; i<cards.size();i++){
            switch(cards.get(i)){
                case Six -> Score += 6;
                case Seven -> Score+=7;
                case Eight -> Score +=8;
                case Nine -> Score+=9;
                case Ten -> Score += 10;
                case Jack -> Score+=2;
                case Queen -> Score+=3;
                case King -> Score +=4;
                case Ace -> Score += 11;
            }
        }
        if(Score>21 && cards.contains(Cards.Ace)){
            Score-=10;
        }
        if(Score >= 18){
        System.out.println("Dealer Score is " + Score);
        return Score;
        }
        else{
            takeCard();
            System.out.println("Dealer cards " + cards);
            Score = CountScore(cards);
        }
        return Score;
    }

    public int getScore() {
        return Score;
    }
}
