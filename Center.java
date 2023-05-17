import java.util.ArrayList;
import java.util.List;

class Center {
    private List<Card> cards;

    public Center() {
        cards = new ArrayList<>();
    }

    public void playCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void clear() {
        cards.clear();
    }
}
