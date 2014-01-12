package deckofcards;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    Deck deck;
    Scanner sc = new Scanner(System.in);
    private ArrayList<Cards> dealer = new ArrayList<>();
    private ArrayList<Cards> player = new ArrayList<>();
    public int playerScore = 0;
    public int dealerScore = 0;
    int i = 0;
    boolean hasAnAce = false;

    public Blackjack() {
        deck = new Deck();
        deck.shuffleDeck();
        System.out.print("Your Cards:  ");
        while (i < 2) {
            player.add(deck.fullDeck.get(i));
            System.out.print(player.get(i).getCard() + "\t");
            i++;
        }
        System.out.print("\nDealers Cards:  ");
        while (i < 4) {
            dealer.add(deck.fullDeck.get(i));
            i++;
        }
        System.out.print("Hidden card \t");
        System.out.println(dealer.get(1).getCard() + "\t");

        while (true) {
            i++;
            System.out.println("\nEnter 'D' to Draw, 'H' to Hold, or 'Q' to Quit:");
            String choice = sc.nextLine();
            switch (choice.toUpperCase()) {
                case "Q":
                    System.exit(0);
                    break;
                case "H":
                    endHand();
                    return;
                case "D":
                    player.add(deck.fullDeck.get(i));
                    for (Cards card : player) {
                        System.out.print(card.getCard() + "\t");
                    }
                    break;
            }
        }
    }

    private int getAnyScore(ArrayList<Cards> oneSide) {
        int theScore = 0;
        for (Cards card : oneSide) {
            if (card.getCard().contains("Two")) {
                theScore += 2;
            } else if (card.getCard().contains("Three")) {
                theScore += 3;
            } else if (card.getCard().contains("Four")) {
                theScore += 4;
            } else if (card.getCard().contains("Five")) {
                theScore += 5;
            } else if (card.getCard().contains("Six")) {
                theScore += 6;
            } else if (card.getCard().contains("Seven")) {
                theScore += 7;
            } else if (card.getCard().contains("Eight")) {
                theScore += 8;
            } else if (card.getCard().contains("Nine")) {
                theScore += 9;
            } else if (card.getCard().contains("Ace")) {
                hasAnAce = true;
                theScore += 11;
            } else {
                theScore += 10;
            }
        }
        return theScore;
    }

    private void endHand() {
        playerScore = getAnyScore(player);
        System.out.println("Player score: " + playerScore);
        if (playerScore > 21) {
            System.out.println("Sorry, you busted.");
        } else {
            dealerScore = getAnyScore(dealer);
            while (dealerScore < 17) {
                dealer.add(deck.fullDeck.get(i));
                System.out.println("Dealer chooses another card:  ");
                dealerScore = getAnyScore(dealer);
                if (hasAnAce = true) {
                    if (dealerScore > 21) {
                        dealerScore -= 10;
                        hasAnAce = false;
                    }
                }
                i++;
            }
            for (Cards card : dealer) {
                System.out.print(card.getCard() + "\t");
            }
            System.out.println("Dealer score: " + dealerScore);
            if (dealerScore > 21 && playerScore <= 21) {
                System.out.println("Dealer busted, You win!");
            } else if (playerScore > 21) {
                System.out.println("Sorry, you busted.");
            } else if (dealerScore >= playerScore) {
                System.out.println("Sorry, you lose.");
            } else {
                System.out.println("You Win!");
            }
        }
    }

}
