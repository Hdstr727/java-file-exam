import javax.swing.*;

public class Application extends JFrame {

    private JPanel panelMain;
    private JRadioButton aRadioButton;
    private JRadioButton bRadioButton;
    private JRadioButton cRadioButton;
    private JRadioButton dRadioButton;
    private JButton goBackButton;
    private JLabel QuestionHeading;
    private JLabel Question;
    private JLabel Score;
    private JLabel OptionAText;
    private JLabel OptionBText;
    private JLabel OptionCText;
    private JLabel OptionDText;

    String question = "";
    String correctAnswer = "";
    String optionAValue = "";
    String optionBValue = "";
    String optionCValue = "";
    String optionDValue = "";


    ButtonGroup options = new ButtonGroup();
    public Application() {
        setContentPane(panelMain);
        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        options.add(aRadioButton); options.add(bRadioButton); options.add(cRadioButton); options.add(dRadioButton);

        FileHandler fileHandler = new FileHandler();
        String filePathQuestions = "questions.txt";
        String filePathAnswers = "answers.txt";
        String[] options = fileHandler.readAnswerOptions(filePathAnswers, 1);
        optionAValue = options[0];
        optionBValue = options[1];
        optionCValue = options[2];
        optionDValue = options[3];
        correctAnswer = fileHandler.readAnswer(filePathAnswers, 1);
        question = fileHandler.readQuestion(filePathQuestions, 1);

        Question.setText(question);
        OptionAText.setText(optionAValue);
        OptionBText.setText(optionBValue);
        OptionCText.setText(optionCValue);
        OptionDText.setText(optionDValue);
    }
}