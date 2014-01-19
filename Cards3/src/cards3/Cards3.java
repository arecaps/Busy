package cards3;

public class Cards3 {

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);
        deck.shuffle();
        System.out.println();
        System.out.println(deck);
    }
}
