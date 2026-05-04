import java.util.Scanner;
/*
Register user
Print welcome
Send welcome email
Loop questions
Check answer, update score
User chooses continue/stop
Game over
Show final summary
Send final result email
Exit */
public class KonBC {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        // Registration
        Registration player = Registration.register(sc);
        System.out.println("\nHello " + player.getUsername() + "! You are registered with " + player.getEmail() + ".");
        System.out.println("Selected play mode: " + player.getPlayMode() + "\n");

        // Send welcome email
        System.out.println("Sending welcome email...");
        EmailService.sendWelcomeEmail(player.getEmail(), player.getUsername(), player.getPlayMode());
        System.out.println();

        // Game setup
        ScoreManager score = new ScoreManager();
        int questionCount = 1;
        boolean gameOver = false;

        while (!gameOver) {
            Question q = Questions.getQuestion(player.getPlayMode());
            if (q == null) {
                System.out.println("No questions available!");
                break;
            }

            // Display question
            Questions.displayQuestion(q, questionCount);

            // Get user answer
            int choice = sc.nextInt();

            // Validate choice
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice (1-4 only)!");
                score.incrementWrong();
                gameOver = true;
                break;
            }

            // Check answer
            boolean isCorrect = (choice == q.correctAnswer);
            if (isCorrect) {
                System.out.println("Correct!");
                score.incrementCorrect();

                // Display current score
                System.out.println("---");
                System.out.println("Current Prize: " + ScoreManager.formatPrize(score.getCurrentPrizeMoney()));
                System.out.println("Next Prize: " + ScoreManager.formatPrize(score.getNextPrizeMoney()));
                System.out.println("Correct: " + score.getCorrectAnswers() + "/" + score.getTotalQuestions());
                System.out.println("---");

                
                // Ask to continue
                System.out.println("Continue? (1=Yes, 0=No): ");
                int continueChoice = sc.nextInt();

                if (continueChoice == 0) {
                    System.out.println("You chose to exit. Game Over!");
                    gameOver = true;
                } else {
                    questionCount++;
                }

            } else {
                // Wrong answer - game over
                System.out.println("Wrong! Correct answer was " + q.correctAnswer);
                score.incrementWrong();
                System.out.println("Final Prize: " + ScoreManager.formatPrize(score.getCurrentPrizeMoney()));
                gameOver = true;
            }
        }

        // Game Over - Display final results
        System.out.println("\nGAME OVER");
        System.out.println(score.getResultSummary());

        // Send final result email
        System.out.println("Sending final results email...");
        EmailService.sendFinalResultEmail(player.getEmail(), player.getUsername(), score);
        System.out.println("Thank you for playing!");
    }
}
