package deckofcards;

import java.util.ArrayList;
import java.util.Collections;

public final class Deck {

    ArrayList<Cards> fullDeck;

    String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
    String[] rank = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    public Deck() {
        this.fullDeck = new ArrayList<Cards>();
        for (String suit : suits) {
            //oneSuit(suit);
            for (String theRank : rank) {
            Cards card = new Cards();
            card.setCard(suit, theRank);
            fullDeck.add(card);
            }
        }
    }

   /* public void oneSuit(String suit) {
        for (String rank1 : rank) {
            Cards card = new Cards();
            card.setCard(suit, rank1);
            fullDeck.add(card);
        }
    }*/

    public void printDeck() {
        for (Cards each : fullDeck) {
            System.out.println(each.getCard());
        }
    }
    public  void shuffleDeck(){
        Collections.shuffle(fullDeck);
    }
}
