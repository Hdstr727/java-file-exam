import javax.swing.*;

public class Application extends JFrame {

    private JPanel panelMain;
    private JRadioButton aRadioButton;
    private JRadioButton bRadioButton;
    private JRadioButton cRadioButton;
    private JRadioButton dRadioButton;
    private JButton goBackButton;
    private JButton goForwardButton;
    private JLabel QuestionHeading;
    private JLabel Question;
    private JLabel Score;
    private JLabel OptionA;
    private JLabel OptionB;
    private JLabel OptionC;

    ButtonGroup options = new ButtonGroup();
    public Application() {
        setContentPane(panelMain);
        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        options.add(aRadioButton); options.add(bRadioButton); options.add(cRadioButton); options.add(dRadioButton);
        
    }
}