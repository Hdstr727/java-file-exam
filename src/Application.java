import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Application extends JFrame implements ActionListener {

    String filePathQuestions = "questions.txt";
    String filePathAnswers = "answers.txt";
    String filePathScores = "scores.txt";
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
    	panelMain = new JPanel();
    	Color color = new Color(153, 255, 255);

        QuestionHeading = new JLabel("Jautājums:");
        aRadioButton = new JRadioButton("A");
        bRadioButton = new JRadioButton("B");
        cRadioButton = new JRadioButton("C");
        dRadioButton = new JRadioButton("D");
        goForwardButton = new JButton("Uz priekšu");
        QuestionText = new JLabel("");
        Score = new JLabel("Punkti: 0");
        OptionAText = new JLabel("");
        OptionBText = new JLabel("");
        OptionCText = new JLabel("");
        OptionDText = new JLabel("");

     
        QuestionHeading.setBounds(80, 40, 90, 30);
        QuestionText.setBounds(200, 40, 400, 30);
        Score.setBounds(650, 40, 90, 30);
        aRadioButton.setBounds(80, 120, 90, 30);
        bRadioButton.setBounds(80, 220, 90, 30);
        cRadioButton.setBounds(80, 320, 90, 30);
        dRadioButton.setBounds(80, 420, 90, 30);
        OptionAText.setBounds(340, 120, 180, 30);
        OptionBText.setBounds(340, 220, 180, 30);
        OptionCText.setBounds(340, 320, 180, 30);
        OptionDText.setBounds(340, 420, 180, 30);
        goForwardButton.setBounds(650, 500, 120, 30);
        
        panelMain.setBackground(color);
        aRadioButton.setBackground(color);
        bRadioButton.setBackground(color);
        cRadioButton.setBackground(color);
        dRadioButton.setBackground(color);
        
        panelMain.add(QuestionHeading);
        panelMain.add(QuestionText);
        panelMain.add(Score);
        panelMain.add(aRadioButton);
        panelMain.add(bRadioButton);
        panelMain.add(cRadioButton);
        panelMain.add(dRadioButton);
        panelMain.add(OptionAText);
        panelMain.add(OptionBText);
        panelMain.add(OptionCText);
        panelMain.add(OptionDText);
        panelMain.add(goForwardButton);
        
        
        setContentPane(panelMain);
        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
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
        goForwardButton.setEnabled(false);

        aRadioButton.addItemListener(new RadioButtonListener());
        bRadioButton.addItemListener(new RadioButtonListener());
        cRadioButton.addItemListener(new RadioButtonListener());
        dRadioButton.addItemListener(new RadioButtonListener());
    }

    private int currentQuestion = 0;
    private int score = 0;
    private int correctAnswerCount = 0;
    private int incorrectAnswerCount = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goForwardButton) {
            Question current = questions.get(currentQuestion);
            String selectedOption = getSelectedOption();

            if (selectedOption != null) {
                if (selectedOption.equals(current.getCorrectAnswer())) {
                    score += 2;
                    correctAnswerCount++;
                } else {
                    if (score > 0) {
                        score--;
                    }
                    incorrectAnswerCount++;

                }
            } else {
                JOptionPane.showMessageDialog(null, "Ludzu izveleties atbildi", "Nav izveleta atbilde", JOptionPane.WARNING_MESSAGE);
                return;
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
                
                String questionText = current.getQuestion();
                
                if (questionText.length() <= 48) {
                	QuestionText.setBounds(250, 40, 400, 30);
                } else {
                	QuestionText.setBounds(200, 40, 400, 30);
                }
            } else {
                 String name = "";
                do {
                    name = JOptionPane.showInputDialog("Jūs ieguvāt " + score + " punktus! \n Ievadi savu vārdu!");
                } while(name.equalsIgnoreCase(""));
                FileHandler.writeToFile(filePathScores, name, score, correctAnswerCount, incorrectAnswerCount);

                int choice;

                do {
                    choice = showOptionDialog();

                    if (choice == JOptionPane.YES_OPTION) {
                        FileHandler.readQuizScores(filePathScores);
                    } else if (choice == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    } else if (choice == JOptionPane.CANCEL_OPTION) {
                        restartQuiz();
                    }
                } while (choice != JOptionPane.NO_OPTION && choice != JOptionPane.CANCEL_OPTION);
            }

        }

        }

    private class RadioButtonListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            goForwardButton.setEnabled(options.getSelection() != null);
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

    public static int showOptionDialog() {
        String[] buttonLabels = {"Jā", "Nē, apturēt", "Nē, restartēt"};
        int choice;
        choice = JOptionPane.showOptionDialog(null, "Vai vēlaties apskatīt rezultātus?", "Izvēle",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonLabels, buttonLabels[0]);

        return choice;
    }
    private void restartQuiz() {
        Collections.shuffle(questions);
        currentQuestion = 0;
        score = 0;
        correctAnswerCount = 0;
        incorrectAnswerCount = 0;

        Question current = questions.get(currentQuestion);
        QuestionText.setText(current.getQuestion());
        OptionAText.setText(current.getOptionA());
        OptionBText.setText(current.getOptionB());
        OptionCText.setText(current.getOptionC());
        OptionDText.setText(current.getOptionD());
        Score.setText("Punkti: " + score);

        options.clearSelection();
        goForwardButton.setEnabled(false);
    }

}