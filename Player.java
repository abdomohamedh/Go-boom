import java.util.ArrayList;
import java.util.List;
class Player {
    private String name;
    private List<Card> hand;
    private Card leadCard;
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void receiveCard(Card card) {
        hand.add(card);
    }
    
    public void playCard(Card card) {
        hand.remove(card);
        leadCard = card;
    }
    
    public List<Card> getHand() {
        return hand;
    }
    
    public Card getLeadCard() {
        return leadCard;
    }
    
    public boolean canPlayCard(Card card, Card leadCard) {
        if (leadCard == null) {
            return true; // First lead card, any card can be played
        }
        String leadSuit = leadCard.getSuit();
        String leadRank = leadCard.getRank();
    
        return card.getSuit().equals(leadSuit) || card.getRank().equals(leadRank);
    }
    }