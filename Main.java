/*# Part 1

## A. Member Contributions

No | ID         | Name                               | Task descriptions | Contribution %
-- | ---------- | ---------------------------------- | ----------------- | --------------
1  | 1211305311 |ABDELRAHMAN MOHAMED HASSAN MOHAMED  | Tasks NO.(5,2,3)  |50٪
2  | 1221301202 |BOUKHARI, ABD ERRAHMANE             | Tasks NO.(1,4,6)  |50٪
3  |            |      |                   |
4  |            |      |                   |


## B. Feature Completion

Mark Y for complete, N for incomplete.

No | Feature                                                                         | Completed (Y/N)
-- | ------------------------------------------------------------------------------- | ---------------
1  | All cards should be faced up to facilitate checking.                            |Y
2  | Start a new game with randomized 52 cards.                                      |Y
3  | The first card in the deck is the first lead card and is placed at the center.  |Y
4  | The first lead card determines the first player.                                |Y
5  | Deal 7 cards to each of the 4 players.                                          |Y
6  | All players must follow the suit or rank of the lead card.                      |Y
7  | The highest-rank card with the same suit as the lead card wins the trick.       |N
8  | The winner of a trick leads the next card.                                      |N


## C. Link to Video Presentation

Upload your video presentation to your Google drive or YouTube, then paste the link below. Give your lab lecturer the permission to view the video.

https://youtu.be/kIR0SLfkcR4
--------------------------------------------------------------------------------------------------------------------------------------------------------*/
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;



public class Main {
public static void main(String[] args) {
Game game = new Game();
System.out.print("Starting the Go Boom game...");
game.startGame();
}
}
class Card {
    private String suit;
    private String rank;
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public String getRank() {
        return rank;
    }
    public String toString() {
        return rank + suit;
    }
    }
    class Center {
        public List<Card> cards;
    
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
    
    public int getCardRank(Card card) {
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
class Menu {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Game game = new Game();
            System.out.println("*********");
            System.out.println("*   Welcome to the Game!  *");
            System.out.println("*     1. Start Game       *");
            System.out.println("*     2. Exit             *");
            System.out.println("*********");
            System.out.print("Enter your choice:");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    game.startGame();
                    break;
                case 2:
                    System.out.println("Exiting the game...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            break;
        }

    }
}
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