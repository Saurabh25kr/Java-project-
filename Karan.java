import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Karan {

    private static Map<String, List<Question>> qzfast = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add questions to a quiz");
            System.out.println("3. Take a quiz");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createQuiz(scanner);
                    break;
                case 2:
                    addQuestions(scanner);
                    break;
                case 3:
                    takeQuiz(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createQuiz(Scanner scanner) {
        System.out.print("Enter the name of the quiz: ");
        String quizName = scanner.nextLine();

        if (qzfast.containsKey(quizName)) {
            System.out.println("A quiz with this name already exists.");
            return;
        }

        qzfast.put(quizName, new ArrayList<>());
        System.out.println("Quiz \"" + quizName + "\" created successfully.");
    }

    private static void addQuestions(Scanner scanner) {
        System.out.print("Enter the name of the quiz: ");
        String quizName = scanner.nextLine();

        if (!qzfast.containsKey(quizName)) {
            System.out.println("Quiz with this name doesn't exist.");
            return;
        }

        List<Question> quizQuestions = qzfast.get(quizName);

        while (true) {
            System.out.print("Enter the question (or type 'done' to finish): ");
            String questionText = scanner.nextLine();

            if (questionText.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter the correct answer: ");
            String correctAnswer = scanner.nextLine();

            List<String> options = new ArrayList<>();
            options.add(correctAnswer);

            System.out.print("Enter other options (or type 'done' to finish): ");
            String option = scanner.nextLine();

            while (!option.equalsIgnoreCase("done")) {
                options.add(option);
                System.out.print("Enter another option (or type 'done' to finish): ");
                option = scanner.nextLine();
            }

            quizQuestions.add(new Question(questionText, correctAnswer, options));
        }

        System.out.println("Questions added to quiz \"" + quizName + "\" successfully.");
    }

    private static void takeQuiz(Scanner scanner) {
        System.out.print("Enter the name of the quiz: ");
        String quizName = scanner.nextLine();

        if (!qzfast.containsKey(quizName)) {
            System.out.println("Quiz with this name doesn't exist.");
            return;
        }

        List<Question> quizQuestions = qzfast.get(quizName);

        if (quizQuestions.isEmpty()) {
            System.out.println("This quiz has no questions yet.");
            return;
        }

        int score = 0;

        for (Question question : quizQuestions) {
            System.out.println("\n" + question.getQuestionText());
            for (int i = 0; i < question.getOptions().size(); i++) {
                System.out.println((i + 1) + ". " + question.getOptions().get(i));
            }

            System.out.print("Enter your answer (number): ");
            int answerChoice = scanner.nextInt();
            scanner.nextLine(); 

            if (answerChoice == question.getOptions().indexOf(question.getCorrectAnswer()) + 1) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer was: " + question.getCorrectAnswer());
            }
        }

        System.out.println("\nYou scored " + score + " out of " + quizQuestions.size() + " questions.");
    }

    static class Question {
        private String questionText;
        private String correctAnswer;
        private List<String> options;

        public Question(String questionText, String correctAnswer, List<String> options) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.options = options;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public List<String> getOptions() {
            return options;
        }
    }
}