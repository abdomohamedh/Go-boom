import java.util.List;
import java.util.ArrayList;
class Game {
    private Deck deck;
    private List<Player> players;
    private Card leadCard;
    private int currentPlayerIndex;
    public Game() {
        deck = new Deck();
        players = new ArrayList<>();
        currentPlayerIndex = 1;
    }
    
    public void startGame() {
        deck.initializeDeck();
        deck.shuffleDeck();
    
        for (int i = 0; i < 4; i++) {
            Player player = new Player("Player" + (i + 1));
            players.add(player);
        }
    
        dealCards();
    
        leadCard = deck.dealCard();
        System.out.println("First lead card: " + leadCard.getSuit() + " " + leadCard.getRank());
    
        setFirstPlayer();
    
        playGame();
    }
    
    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                Card card = deck.dealCard();
                player.receiveCard(card);
            }
        }
    }
    
    private void setFirstPlayer() {
        switch (leadCard.getRank()) {
            case "A", "5", "9", "K" -> currentPlayerIndex = 0; // Player1
            case "2", "6", "10" -> currentPlayerIndex = 1; // Player2
            case "3", "7", "J" -> currentPlayerIndex = 2; // Player3
            case "4", "8", "Q" -> currentPlayerIndex = 3; // Player4
    }
    System.out.println("First player: " + players.get(currentPlayerIndex).getName());
    }
    private int determineTrickWinner() {
        int maxRankIndex = -1;
        int maxRank = -1;
        
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            Card card = player.getLeadCard();
    
            if (card != null && card.getSuit().equals(leadCard.getSuit())) {
                int cardRank = getCardRank(card);
                if (cardRank > maxRank) {
                    maxRank = cardRank;
                    maxRankIndex = i;
                }
            }
        }
    
        System.out.println("Trick winner: " + players.get(maxRankIndex).getName());
        return maxRankIndex;
    }
    
    private int getCardRank(Card card) {
        String rank = card.getRank();
        if (rank.equals("A")) {
            return 14;
        } else if (rank.equals("K")) {
            return 13;
        } else if (rank.equals("Q")) {
            return 12;
        } else if (rank.equals("J")) {
            return 11;
        } else {
            return Integer.parseInt(rank);
        }
    }
    private void playGame() {
        while (!players.get(currentPlayerIndex).getHand().isEmpty()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            Card playedCard = currentPlayer.getHand().get(0); // For simplicity, always play the first card
    
            if (currentPlayer.canPlayCard(playedCard, leadCard)) {
                System.out.println(currentPlayer.getName() + " plays: " + playedCard.getSuit() + " " + playedCard.getRank());
                currentPlayer.playCard(playedCard);
    
                // Update lead card for next turn
                leadCard = playedCard;
    
                // Determine the winner of the trick and update currentPlayerIndex accordingly
                currentPlayerIndex = determineTrickWinner();
            } else {
                System.out.println(currentPlayer.getName() + " cannot play: " + playedCard.getSuit() + " " + playedCard.getRank());
            }
    
            currentPlayerIndex = (currentPlayerIndex + 1) % 4; // Move to the next player
            break;
        }
        System.out.println("Game over!");
        
    }
}