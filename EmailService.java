import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

    private static final String SENDER_EMAIL = System.getenv("EMAIL_USER");
    private static final String SENDER_PASSWORD = System.getenv("EMAIL_PASS");
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    public static void sendWelcomeEmail(String userEmail, String username, String playMode) {
        sendEmail(userEmail, "Welcome to KBC Game!",
            "Hello " + username + ",\n\nWelcome to KBC Game!\n\n" +
            "Username: " + username + "\nPlay Mode: " + playMode + "\n\nGood Luck!");
    }

    public static void sendFinalResultEmail(String userEmail, String username, ScoreManager score) {
        sendEmail(userEmail, "KBC Game - Final Results",
            "Hello " + username + ",\n\nGame Over!\n\n" +
            "Correct Answers: " + score.getCorrectAnswers() + "\n" +
            "Total Questions: " + score.getTotalQuestions() + "\n" +
            "Prize Money: " + ScoreManager.formatPrize(score.getCurrentPrizeMoney()) + "\n\nThank you!");
    }

    private static void sendEmail(String to, String subject, String body) {
        if (SENDER_EMAIL == null || SENDER_PASSWORD == null) {
            System.out.println("Email credentials not set. Please set EMAIL_USER and EMAIL_PASS environment variables.");
            return;
        }

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
                }
            };

            Session session = Session.getInstance(props, auth);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent to " + to);

        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}
