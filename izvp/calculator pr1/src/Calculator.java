import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    InputField inputField = new InputField();

    Button button1 = new Button("1", "1", inputField);
    Button button2 = new Button("2", "2", inputField);
    Button button3 = new Button("3", "3", inputField);
    Button button4 = new Button("4", "4", inputField);
    Button button5 = new Button("5", "5", inputField);
    Button button6 = new Button("6", "6", inputField);
    Button button7 = new Button("7", "7", inputField);
    Button button8 = new Button("8", "8", inputField);
    Button button9 = new Button("9", "9", inputField);

    Button buttonPlus = new Button("+", "+", inputField);
    Button buttonMinus = new Button("-", "-", inputField);
    Button buttonMulti = new Button("*", "*", inputField);
    Button buttonDivide = new Button("/", "/", inputField);

    Button buttonDot = new Button(".", ".", inputField);
    Button buttonC = new Button("C", "C", inputField);
    Button button0 = new Button("0", "0", inputField);

    Button buttonPi = new Button("π", "π", inputField);
    Button buttonPow = new Button("^", "^", inputField);
    Button buttonEq = new Button("=", "=", inputField);

    Button buttonDelete = new Button("<-", "<-", inputField);

    Button buttonSIN = new Button("sin", "sin(", inputField);
    Button buttonCOS = new Button("cos", "cos(", inputField);
    Button buttonSQRT = new Button("sqrt", "sqrt(", inputField);
    Button buttonLBRAC = new Button("(", "(", inputField);
    Button buttonRBRAC = new Button(")", ")", inputField);



    public Calculator() throws HeadlessException {
        super("Calculator");
        setBounds(300, 300, 400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JPanel panelButtons = new JPanel(new GridLayout(6,4,10,10));
        panel.setLayout(new BorderLayout());
        panelButtons.add(button1);
        panelButtons.add(button2);
        panelButtons.add(button3);
        panelButtons.add(buttonPlus);
        panelButtons.add(buttonEq);
        panelButtons.add(button4);
        panelButtons.add(button5);
        panelButtons.add(button6);
        panelButtons.add(buttonMinus);
        panelButtons.add(buttonPow);
        panelButtons.add(button7);
        panelButtons.add(button8);
        panelButtons.add(button9);
        panelButtons.add(buttonMulti);
        panelButtons.add(buttonPi);
        panelButtons.add(buttonDot);
        panelButtons.add(button0);
        panelButtons.add(buttonC);        
        panelButtons.add(buttonDivide);
        panelButtons.add(buttonDelete);

        panelButtons.add(buttonSIN);
        panelButtons.add(buttonCOS);
        panelButtons.add(buttonSQRT);
        panelButtons.add(buttonLBRAC);
        panelButtons.add(buttonRBRAC);

        inputField.setForeground(Color.BLACK);

        panel.add(inputField, BorderLayout.NORTH);
        panel.add(panelButtons, BorderLayout.SOUTH);

        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setEnabled(false);
        add(panel,BorderLayout.NORTH);
        setVisible(true);

       
    }

}
