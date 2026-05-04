public class ScoreManager {

    private static final long[] PRIZE_MONEY = {
        10000, 20000, 50000, 100000, 200000, 500000,
        1000000, 2000000, 5000000, 10000000, 30000000, 70000000
    };

    private int correct = 0;
    private int total = 0;

    public void incrementCorrect() {
        correct++;
        total++;
    }

    public void incrementWrong() {
        total++;
    }

    public int getCorrectAnswers() {
        return correct;
    }

    public int getTotalQuestions() {
        return total;
    }

    public long getCurrentPrizeMoney() {
        if (correct == 0) return 0;
        if (correct > PRIZE_MONEY.length) return PRIZE_MONEY[PRIZE_MONEY.length - 1];
        return PRIZE_MONEY[correct - 1];
    }

    public long getNextPrizeMoney() {
        if (correct >= PRIZE_MONEY.length) return PRIZE_MONEY[PRIZE_MONEY.length - 1];
        return PRIZE_MONEY[correct];
    }

    public static String formatPrize(long amount) {
        if (amount == 0) return "0";
        if (amount == 10000) return "₹10,000";
        if (amount == 20000) return "₹20,000";
        if (amount == 50000) return "₹50,000";
        if (amount == 100000) return "₹1 Lakh";
        if (amount == 200000) return "₹2 Lakhs";
        if (amount == 500000) return "₹5 Lakhs";
        if (amount == 1000000) return "₹10 Lakhs";
        if (amount == 2000000) return "₹20 Lakhs";
        if (amount == 5000000) return "₹50 Lakhs";
        if (amount == 10000000) return "₹1 Crore";
        if (amount == 30000000) return "₹3 Crore";
        if (amount == 70000000) return "₹7 Crore";
        return ("₹" + amount);
    }

    public String getResultSummary() {
        return "Total Questions: " + total + "\nCorrect: " + correct + "\nWrong: " + (total - correct) + "\nPrize: " + formatPrize(getCurrentPrizeMoney());
    }
}
