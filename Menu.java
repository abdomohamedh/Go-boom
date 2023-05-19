import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        while (true) {
            System.out.println("***************************");
            System.out.println("*   Welcome to the Game!  *");
            System.out.println("*     1. Start Game       *");
            System.out.println("*     2. Exit             *");
            System.out.println("***************************");
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
               
            }
            break;
            
             
        }
        
        }
    }
