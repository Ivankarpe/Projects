import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App extends JFrame {
    public App() {
        setTitle("Практична робота. \"JList, JComboBox\"");
        setSize(318, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();


        DefaultListModel<String> listmodel = new DefaultListModel<>();
        JList<String> list = new JList<>(listmodel);

        JComboBox combo = new JComboBox();
        combo.setEditable(true);
        combo.addItem("Звірі");
        combo.addItem("Птахи");
        combo.addItem("риби");
        combo.addActionListener(new ActionListener() {
            // реалізовуємо метод обробки події
            public void actionPerformed(ActionEvent event) {
                if (combo.getSelectedItem().equals("Звірі")) {
                    DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
                    listModel.removeAllElements();
                    listModel.addElement("Качкодзьоб");
                    listModel.addElement("Каркадил");
                    listModel.addElement("зебро");

                }

                else if (combo.getSelectedItem().equals("Птахи")) {
                    DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
                    listModel.removeAllElements();
                    listModel.addElement("Снігур");
                    listModel.addElement("Тукан");
                    listModel.addElement("Ара-ара");

                }
                else if (combo.getSelectedItem().equals("риби")) {
                    DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
                    listModel.removeAllElements();
                    listModel.addElement("фугу");
                    listModel.addElement("камбала");
                    listModel.addElement("акула молот");
                    

                }
                else{
                    DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
                    listModel.removeAllElements();
                    listModel.addElement("Неправильний варіант");

                }
            }
        });
        panel.add(combo, BorderLayout.WEST);

        panel.add(list, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(panel); 

        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        App lr = new App();
    }
}
