import java.util.Scanner;

public class Registration {
    private final String email;
    private final String username;
    private final String playMode;

    private Registration(String email, String username, String playMode) {
        this.email = email;
        this.username = username;
        this.playMode = playMode;
    }

    public static Registration register(Scanner sc) {
        System.out.println("=== Registration ===");

        String email;
        while (true) {
            System.out.print("Enter email id: ");
            email = sc.nextLine().trim();
            if (email.contains("@gmail.com") && email.contains(".") && email.contains("@") ) {
                break;
            }
            System.out.println("Invalid email address. Please enter a valid email.");
        }

        String username;
        while (true) {
            System.out.print("Enter username: ");
            username = sc.nextLine().trim();
            if (!username.isEmpty()) {
                break;
            }
            System.out.println("Username cannot be empty. Please enter a username.");
        }

        int modeChoice;
        while (true) {
            System.out.println("Select play mode:");
            System.out.println("1. Beginner");
            System.out.println("2. Intermediate");
            System.out.println("3. Hard");
            System.out.print("Enter your choice (1-3): ");

            if (sc.hasNextInt()) {
                modeChoice = sc.nextInt();
                sc.nextLine();
                if (modeChoice >= 1 && modeChoice <= 3) {
                    break;
                }
            } else {
                sc.nextLine();
            }
            System.out.println("Invalid mode choice. Please enter 1, 2, or 3.");
        }

        String playMode = switch (modeChoice) {
            case 1 -> "Beginner";
            case 2 -> "Intermediate";
            default -> "Hard";
        };

        return new Registration(email, username, playMode);
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPlayMode() {
        return playMode;
    }
}
