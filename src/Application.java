import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Application extends JFrame implements ActionListener {

    String filePathQuestions = "questions.txt";
    String filePathAnswers = "answers.txt";
    ArrayList<Question> questions = new ArrayList<>();
    private JPanel panelMain;
    private JRadioButton aRadioButton;
    private JRadioButton bRadioButton;
    private JRadioButton cRadioButton;
    private JRadioButton dRadioButton;
    private JButton goForwardButton;
    private JLabel QuestionHeading;
    private JLabel QuestionText;
    private JLabel Score;
    private JLabel OptionAText;
    private JLabel OptionBText;
    private JLabel OptionCText;
    private JLabel OptionDText;

    ButtonGroup options = new ButtonGroup();

    public Application() {
        setContentPane(panelMain);
        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        options.add(aRadioButton);
        options.add(bRadioButton);
        options.add(cRadioButton);
        options.add(dRadioButton);

        FileHandler fileHandler = new FileHandler();

        int totalQuestions = fileHandler.getLineCount(filePathQuestions);

        for (int i = 1; i <= totalQuestions; i++) {
            String questionText = fileHandler.readQuestion(filePathQuestions, i);
            String[] options = fileHandler.readAnswerOptions(filePathAnswers, i);
            String correctAnswer = fileHandler.readAnswer(filePathAnswers, i);

            Question question = new Question();
            question.setQuestion(questionText);
            question.setOptionA(options[0]);
            question.setOptionB(options[1]);
            question.setOptionC(options[2]);
            question.setOptionD(options[3]);
            question.setCorrectAnswer(correctAnswer);

            questions.add(question);
        }

        Collections.shuffle(questions);
        QuestionText.setText(questions.get(0).getQuestion());
        OptionAText.setText(questions.get(0).getOptionA());
        OptionBText.setText(questions.get(0).getOptionB());
        OptionCText.setText(questions.get(0).getOptionC());
        OptionDText.setText(questions.get(0).getOptionD());
        goForwardButton.addActionListener(this);
    }

    private int currentQuestion = 0;
    private int score = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goForwardButton) {
            Question current = questions.get(currentQuestion);
            String selectedOption = getSelectedOption();

            if (selectedOption != null) {
                if (selectedOption.equals(current.getCorrectAnswer())) {
                    score += 2;
                } else {
                    if (score > 0) {
                        score--;
                    }
                }
            }
            Score.setText("Punkti: " + score);

            options.clearSelection();

            currentQuestion++;

            if (currentQuestion < questions.size()) {
                current = questions.get(currentQuestion);

                QuestionText.setText(current.getQuestion());
                OptionAText.setText(current.getOptionA());
                OptionBText.setText(current.getOptionB());
                OptionCText.setText(current.getOptionC());
                OptionDText.setText(current.getOptionD());
            } else {

            }
        }
    }
    private String getSelectedOption() {
        if (aRadioButton.isSelected()) {
            return OptionAText.getText();
        } else if (bRadioButton.isSelected()) {
            return OptionBText.getText();
        } else if (cRadioButton.isSelected()) {
            return OptionCText.getText();
        } else if (dRadioButton.isSelected()) {
            return OptionDText.getText();
        }
        return null;
    }
}

