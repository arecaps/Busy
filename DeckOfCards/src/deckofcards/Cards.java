
package deckofcards;

public class Cards {
    private String suit;
    private String rank;
    
    public void setCard(String suit, String rank){
        this.suit = suit;
        this.rank = rank;
    }
    public String getCard(){
        String card = rank + " of " + suit;
        return card;
    }
}
