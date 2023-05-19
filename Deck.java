import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Deck {
    private List<Card> cards;
    public Deck() {
        cards = new ArrayList<>();
    }
    public void initializeDeck() {
        String[] suits = {"♣", "♦", "♥", "♠"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    
        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new Card(suit, rank);
                cards.add(card);
            }
        }
    }
    
    public void shuffleDeck() {
        Collections.shuffle(cards);
        
    }

    public List<Card> getCards() {
        return cards;
        
    }
    
    public Card dealCard() {
        if (cards.isEmpty()) {

            return null; // Or handle deck exhaustion as desired
        }
        return cards.remove(0);
    }
    }