public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        String filePathQuestions = "questions.txt";
        String filePathAnswers = "answers.txt";
        fileHandler.readData(filePathQuestions);
        fileHandler.readData(filePathAnswers);

        new Application();
    }
}