import java.util.*;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    Question(String q, String[] opts, int correct) {
        this.question = q;
        this.options = opts;
        this.correctAnswer = correct;
    }
}

class QuestionBank {
    static List<Question> beginner = new ArrayList<>();
    static List<Question> intermediate = new ArrayList<>();
    static List<Question> hard = new ArrayList<>();
 
    static {  //static { } block runs when class loads (before any method is called)
        // Beginner Questions (50+ examples, add more as needed)
        beginner.add(new Question("What is 2+2?", new String[]{"1", "2", "3", "4"}, 4));
        beginner.add(new Question("What is the capital of India?", new String[]{"Mumbai", "Delhi", "Bangalore", "Chennai"}, 2));
        beginner.add(new Question("Which is a primary color?", new String[]{"Green", "Red", "Purple", "Orange"}, 2));
        beginner.add(new Question("How many continents are there?", new String[]{"5", "6", "7", "8"}, 3));
        beginner.add(new Question("What is the largest planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 3));
        beginner.add(new Question("What color is the sky?", new String[]{"Red", "Blue", "Green", "Yellow"}, 2));
        beginner.add(new Question("How many legs does a spider have?", new String[]{"6", "8", "10", "12"}, 2));
        beginner.add(new Question("What is 10-5?", new String[]{"3", "4", "5", "6"}, 3));
        beginner.add(new Question("Which animal is known as the King of the Jungle?", new String[]{"Tiger", "Lion", "Elephant", "Giraffe"}, 2));
        beginner.add(new Question("What do bees make?", new String[]{"Milk", "Honey", "Bread", "Cheese"}, 2));
        // Add more to reach 50...

        // Intermediate Questions
        intermediate.add(new Question("What is the square root of 16?", new String[]{"2", "4", "8", "16"}, 2));
        intermediate.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"Shakespeare", "Dickens", "Austen", "Hemingway"}, 1));
        intermediate.add(new Question("What is the chemical symbol for water?", new String[]{"H2O", "CO2", "O2", "NaCl"}, 1));
        intermediate.add(new Question("What is the capital of France?", new String[]{"London", "Berlin", "Paris", "Rome"}, 3));
        intermediate.add(new Question("What is 15 divided by 3?", new String[]{"3", "4", "5", "6"}, 3));
        intermediate.add(new Question("Who painted the Mona Lisa?", new String[]{"Van Gogh", "Picasso", "Da Vinci", "Michelangelo"}, 3));
        intermediate.add(new Question("What is the boiling point of water?", new String[]{"0°C", "50°C", "100°C", "200°C"}, 3));
        intermediate.add(new Question("What is the currency of Japan?", new String[]{"Won", "Yen", "Ringgit", "Baht"}, 2));
        intermediate.add(new Question("What is the largest ocean?", new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, 4));
        intermediate.add(new Question("Who discovered gravity?", new String[]{"Einstein", "Newton", "Galileo", "Tesla"}, 2));
        // Add more to reach 50...

        // Hard Questions
        hard.add(new Question("What is the derivative of x^2?", new String[]{"x", "2x", "x^2", "2"}, 2));
        hard.add(new Question("Who discovered penicillin?", new String[]{"Fleming", "Pasteur", "Darwin", "Einstein"}, 1));
        hard.add(new Question("What is the speed of light?", new String[]{"3x10^8 m/s", "3x10^6 m/s", "3x10^10 m/s", "3x10^4 m/s"}, 1));
        hard.add(new Question("What is the atomic number of carbon?", new String[]{"4", "6", "8", "12"}, 2));
        hard.add(new Question("What is the integral of 1/x?", new String[]{"x", "ln|x|", "e^x", "x^2"}, 2));
        hard.add(new Question("Who wrote 'The Great Gatsby'?", new String[]{"Hemingway", "Fitzgerald", "Faulkner", "Steinbeck"}, 2));
        hard.add(new Question("What is Planck's constant?", new String[]{"6.626x10^-34 J s", "1.602x10^-19 C", "9.109x10^-31 kg", "1.381x10^-23 J/K"}, 1));
        hard.add(new Question("What is the capital of Australia?", new String[]{"Sydney", "Melbourne", "Canberra", "Perth"}, 3));
        hard.add(new Question("What is the formula for force?", new String[]{"F=ma", "E=mc^2", "PV=nRT", "F=Gm1m2/r^2"}, 1));
        hard.add(new Question("Who developed the theory of relativity?", new String[]{"Newton", "Einstein", "Bohr", "Heisenberg"}, 2));
        
    }

    static Question getRandomQuestion(String mode) { 
        List<Question> list = null;
        switch(mode.toLowerCase()) {
            case "beginner": list = beginner; break;
            case "intermediate": list = intermediate; break;
            case "hard": list = hard; break;
        }
        if(list != null && !list.isEmpty()) {   //list != null checks if list exists//!list.isEmpty() checks if list has questions
            return list.get((int)(Math.random() * list.size()));
        }
        return null;
    }
}

public class Questions {
    public static void displayQuestion(Question q, int questionNum){
        System.out.println("\nQuestion " + questionNum + ":");
        System.out.println(q.question);
        for(int i=0; i<q.options.length; i++){
            System.out.println((i+1) + ". " + q.options[i]);
        }
        System.out.println("Enter your choice (1-4): ");
    }

    public static Question getQuestion(String playMode){
        return QuestionBank.getRandomQuestion(playMode);
    }

    public static void checkAnswer(int choice, Question q){
        if(choice == q.correctAnswer){
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect! The correct answer was option " + q.correctAnswer);
        }
    }
}