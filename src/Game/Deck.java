package Game;

import java.util.*;

public class Deck {
    Queue<Cards> deck;

    //init Deck
    public Deck(){
        this.deck = new LinkedList<>();
        List<Cards> list = new ArrayList<>();
        for (int i=0;i<4;i++){
            list.add(Cards.Six);
            list.add(Cards.Seven);
            list.add(Cards.Eight);
            list.add(Cards.Nine);
            list.add(Cards.Ten);
            list.add(Cards.Jack);
            list.add(Cards.Queen);
            list.add(Cards.King);
            list.add(Cards.Ace);
        }

         Collections.shuffle(list);
         for (int x =0; x<list.toArray().length;x++){
             deck.add(list.get(x));
         }

    }
    public  Cards GiveCard(){
        Cards card = deck.peek();
        deck.poll();
        return card;
    }


}
