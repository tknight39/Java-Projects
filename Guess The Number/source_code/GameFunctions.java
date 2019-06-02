import java.util.Random;
import java.util.Scanner;

public class GameFunctions {
    private static int attempts;
    private static int randomNumber;
    private static int guessRange;
    private static int userGuess;

    private static Scanner scanner = new Scanner(System.in);

    /* Main game loop */
    public static void main() {

        do {
            playGame();
        } while (playAgain());
    }

    /*
     * Initializes game, gets input from user, and checks
     * to see if the guess is correct
     */
    private static void playGame() {
        gameInit();
        while (attempts > 0 && userGuess != randomNumber) {
            getInput();
            checkGuess();
        }
    }

    /*
     *  Takes care of the setup aspects of the game like
     *  Generating a new random number and finding how
     *  Many attemps the player will have
     */
    private static void gameInit() {
        setRandomNumber();
        setAttemptsAndRange();
    }

    /* Sets randomNumber to a value between 1 and 100 */
    private static void setRandomNumber() {
        Random random = new Random();
        int low = 0;
        int high = 100;
        randomNumber = random.nextInt(high - low) + low;
    }

    /* Sets the attempts value based on how large the randomNumber is */
    private static void setAttemptsAndRange() {
        if (randomNumber <= 10) {
            attempts = 2; guessRange = 10;
        }
        else if (randomNumber <= 25) {
            attempts = 4; guessRange = 25;
        }
        else if (randomNumber <= 50) {
            attempts = 5; guessRange = 50;
        }
        else if (randomNumber <= 75) {
            attempts = 7; guessRange = 75;
        }
        else {
            attempts = 10; guessRange = 100;
        }
    }

    /* Displays the number of attempts the user has left */
    private static void displayAttempts() {
        System.out.println("Attempts remaining: " + attempts);
    }

    /* Displays the random number, used at the end of the game */
    private static void displayRandomNumber() {
        System.out.println("The random number is " + randomNumber);
    }

    /* Gets input from the user */
    private static void getInput() {
        while (true) {
            try {
                displayAttempts();
                System.out.println("Enter a number between 0 and " + guessRange);
                userGuess = scanner.nextInt();

                if ( userGuess <= 100 && userGuess > 0) {
                    break;
                }
                else {
                    System.out.println("Number is out of guess range");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }

    /*  Checks to see if the guess is correct */
    private static void checkGuess() {
        if (userGuess != randomNumber) {
            if (userGuess < randomNumber) {
                System.out.println("Too Low!");
            }
            else {
                System.out.println("Too High!");
            }
            attempts--;
        }
    }

    /*
     * Tells the user their win/loss status and asks them to play again
     * Also Tells the user what the random number was
     */
    private static boolean playAgain() {
        String userInput;

        if (attempts > 0) {
            System.out.println("You won!");
        }
        else {
            System.out.println("You Lost!");
        }

        displayRandomNumber();

        while (true) {
            try {
                System.out.println("Play Again? yes/no");
                userInput = scanner.next();
                if (userInput.equals("yes") || userInput.equals("no")) {
                    if (userInput.equals("yes")) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    System.out.println("Invalid Input");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input, try again");
            }
        }
    }
}
