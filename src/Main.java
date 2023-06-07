public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        String filePathQuestions = "questions.txt";
        String filePathAnswers = "answers.txt";
        fileHandler.readQuestions(filePathQuestions, 1);
        fileHandler.readAnswerOptions(filePathAnswers, 1);
        fileHandler.readAnswer(filePathAnswers, 1);

        new Application();
    }
}