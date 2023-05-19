import java.util.List;
import java.util.ArrayList;
class Game {
    private Center center;
    private Deck deck;
    private List<Integer> scores;
    private List<Player> players;
    private Card leadCard;
    private int currentPlayerIndex;
        public Game() {
        deck = new Deck();
        players = new ArrayList<>();
        scores = new ArrayList<>();
        center = new Center();
        currentPlayerIndex = 1;
    }
    
    public void startGame() {
        deck.initializeDeck();
        deck.shuffleDeck();
        dealCards();
        leadCard = deck.dealCard();
        
        for (int i = 1; i <= 4; i++) {
            Player player = new Player("Player" + (i));
            players.add(player);
            scores.add(0);
            System.out.println();

            System.out.print("player" + i + ": " + "[");
         for(int d = 0; d <= 6; d++){
            if(d == 6){ System.out.print(deck.dealCard() + "] ");
            } else {
            System.out.print(deck.dealCard() + ", ");
}
}  
        }
        setFirstPlayer();
        playGame();
    }

    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i <= 7; i++) {
                Card card = deck.dealCard();
                player.receiveCard(card);
            }
        }

    }
    
    private void setFirstPlayer() {
        switch (leadCard.getRank()) {
            case "A":
            case "5": 
            case "9": 
            case "K": currentPlayerIndex = 0; // Player1
            case "2": 
            case "6":
            case "10":  currentPlayerIndex = 1; // Player2
            case "3": 
            case "7": 
            case "J":  currentPlayerIndex = 2; // Player3
            case "4": 
            case "8":
            case  "Q":   currentPlayerIndex = 3; // Player4
    }
    System.out.println();
    System.out.println("Center: " + center.getCards());
    System.out.print("Deck: "+ "[");
    for (int i = 0; i < deck.getCards().size(); i++){
        System.out.print(deck.getCards().get(i) + ", ");

    }
    System.out.println("] ");

    System.out.println("Turn: " + players.get(currentPlayerIndex).getName());
    System.out.print(" Score: ");
    for (int i = 0; i < players.size(); i++) {
        Player player = players.get(i);
        System.out.print(  player.getName() + " = " + scores.get(i)+ " | ");
    }
    
    System.out.println();

    System.out.println("> " + leadCard.getSuit() + " " + leadCard.getRank());
    
       
    }
    public int determineTrickWinner() {
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
    public void playGame() {
        while (!players.get(currentPlayerIndex).getHand().isEmpty()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            Card playedCard = currentPlayer.getHand().get(0); // For simplicity, always play the first card
    
            if (currentPlayer.canPlayCard(playedCard, leadCard)) {
                System.out.println(currentPlayer.getName() + " plays:[ " + playedCard.getSuit() + " " + playedCard.getRank()+ "]");
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