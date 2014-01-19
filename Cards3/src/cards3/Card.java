package cards3;
        
public class Card {
    
    private Suit suit;
    private Rank rank;
    
    
    public enum Suit {

        HEARTS,
        CLUBS,
        SPADES,
        DIAMONDS;
       @Override
        public String toString() {
		return name().charAt(0)+name().substring(1).toLowerCase();
                        }
    }

    public enum Rank {

        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING;
        @Override
        public String toString() {
		return name().charAt(0)+name().substring(1).toLowerCase();
                        }
    }

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

   @Override
    public String toString() {
            String retVal = rank + " of " + suit;
            return retVal;
    }
}
