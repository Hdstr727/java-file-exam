import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    public void readQuestions(String filePath, int lineNumber) {
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            int currentLineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    System.out.println(line);
                    break;
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String correctAnswer = "";
    String optionA = "";
    String optionB = "";
    String optionC = "";
    String optionD = "";

    public void readAnswerOptions(String filePath, int lineNumber) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    String[] options = line.split("\\|");
                    for (String option : options) {
                        option = option.trim();
                        if (option.startsWith("A)")) {
                            optionA = option.substring(2).trim();
                            System.out.println("Option A: " + optionA);
                        } else if (option.startsWith("B)")) {
                            optionB = option.substring(2).trim();
                            System.out.println("Option B: " + optionB);
                        } else if (option.startsWith("C)")) {
                            optionC = option.substring(2).trim();
                            System.out.println("Option C: " + optionC);
                        } else if (option.startsWith("D)")) {
                            optionD = option.substring(2).trim();
                            System.out.println("Option D: " + optionD);
                        }
                    }
                    break;
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readAnswer(String filePath, int lineNumber) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    String[] options = line.split("\\|");
                    for (String option : options) {
                        option = option.trim();
                        if (option.startsWith("*")) {
                            correctAnswer = option.substring(1).trim();
                            System.out.println("Correct Answer: " + correctAnswer);
                        }
                    }
                    break;
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
