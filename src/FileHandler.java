import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public int getLineCount(String filePath) {
        int lineCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while (bufferedReader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }
    public String readQuestion(String filePath, int lineNumber) {
        String line = null;
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            int currentLineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    break;
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public String[] readAnswerOptions(String filePath, int lineNumber) {
        String[] options = new String[4];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    String[] optionValues = line.split("\\|");
                    for (String option : optionValues) {
                        option = option.trim();
                        if (option.startsWith("A)")) {
                            options[0] = option.substring(2).trim();
                        } else if (option.startsWith("B)")) {
                            options[1] = option.substring(2).trim();
                        } else if (option.startsWith("C)")) {
                            options[2] = option.substring(2).trim();
                        } else if (option.startsWith("D)")) {
                            options[3] = option.substring(2).trim();
                        }
                    }
                    break;
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return options;
    }

    public String readAnswer(String filePath, int lineNumber) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    String[] options = line.split("\\|");
                    for (String option : options) {
                        option = option.trim();
                        if (option.startsWith("*")) {
                            String correctAnswer = option.substring(1).trim();
                            return correctAnswer;
                        }
                    }
                    break;
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void writeToFile(String filePath, String name, int score, int correctAnswerCount, int incorrectAnswerCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String data = "name: "+ name + " | score: " + score + " | correct Answers: " + correctAnswerCount + " | incorrect Anwers: " + incorrectAnswerCount + " |";
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readQuizScores(String filePath) {

    }
}
