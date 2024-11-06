import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuDemo {

    public static void PC4_1GUI() {

        JFrame frame = new JFrame("Тестове меню");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200,200,600,300);
        frame.setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        JMenuItem newFile = new JMenuItem("Створити");
        fileMenu.add(newFile);

        JMenuItem openItem = new JMenuItem("Відкрити");
        fileMenu.add(openItem);

        JMenuItem saveItem = new JMenuItem("Зберегти");
        fileMenu.add(saveItem);

        JMenu saveAssItem = new JMenu("Зберегти як");
        fileMenu.add(saveAssItem);

        JMenuItem rtfItem = new JMenuItem("Зберегти як rtf");
        saveAssItem.add(rtfItem);
        JMenuItem xmlItem = new JMenuItem("Зберегти як xml");
        saveAssItem.add(xmlItem);
        JMenuItem openDocumentItem = new JMenuItem("Зберегти як open document");
        saveAssItem.add(openDocumentItem);
        JMenuItem txtItem = new JMenuItem("Зберегти як txt");
        saveAssItem.add(txtItem);
        JMenuItem otherItem = new JMenuItem("Зберегти в іншому форматі");
        saveAssItem.add(otherItem);
        fileMenu.addSeparator();

        JMenu printMenu = new JMenu("Зберегти як");
        fileMenu.add(printMenu);

        JMenuItem usualItem = new JMenuItem("Друк");
        printMenu.add(usualItem);
        JMenuItem speedItem = new JMenuItem("швидкий друк");
        printMenu.add(speedItem);
        JMenuItem prewiewItem = new JMenuItem("попередній перегляд");
        printMenu.add(prewiewItem);
        JMenuItem paramItem = new JMenuItem("Параметри сторінки");
        fileMenu.add(paramItem);
        JMenuItem sendMailItem = new JMenuItem("Надслати електронною поштою");
        fileMenu.add(sendMailItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Вийти");
        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        JMenu mainMenu = new JMenu("Основне");
        menuBar.add(mainMenu);

        JMenu pasteMenu = new JMenu("Вставити");
        mainMenu.add(pasteMenu);

        JMenuItem pasteItem = new JMenuItem("Вставити");
        pasteMenu.add(pasteItem);

        JMenuItem specialPasteItem = new JMenuItem("Спеціальна вставка");
        pasteMenu.add(specialPasteItem);

        JMenuItem cutItem = new JMenuItem("Віризати");
        mainMenu.add(cutItem);
        
        JMenuItem copeItem = new JMenuItem("Копіювати");
        mainMenu.add(copeItem);


        JMenu viewMenu = new JMenu("Вигляд");
        menuBar.add(viewMenu);

        JMenuItem moreItem  = new JMenuItem("збільшити");
        viewMenu.add(moreItem);

        JMenuItem lessItem  = new JMenuItem("зменшити");
        viewMenu.add(lessItem);

        JMenuItem stoItem = new JMenuItem("100%");
        viewMenu.add(stoItem);

    }

    public static void main(String[] args) {
        MenuDemo.PC4_1GUI();
    }
}