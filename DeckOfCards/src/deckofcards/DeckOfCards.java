package deckofcards;

import java.util.Scanner;

public class DeckOfCards {

    public static void main(String[] args) {

        Deck deck = new Deck();
        deck.printDeck();

        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to play Blackjack?  y/n");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("y")) {
            Blackjack blackjack = new Blackjack();
        }  else {
            System.out.println("Okay, maybe a different time.");
        }
    }
    }
