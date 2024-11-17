import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuDemo {

    public static void PC4_1GUI() {
        JFrame frame = new JFrame("Тестове меню");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 600, 300);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Файл Menu
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        // Файл > Створити
        JMenuItem newFile = new JMenuItem("Створити");
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(newFile);

        // Файл > Відкрити
        JMenuItem openItem = new JMenuItem("Відкрити");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(openItem);

        // Файл > Зберегти
        JMenuItem saveItem = new JMenuItem("Зберегти");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(saveItem);

        // Файл > Зберегти як
        JMenu saveAsMenu = new JMenu("Зберегти як");
        fileMenu.add(saveAsMenu);

        JMenuItem rtfItem = new JMenuItem("Зберегти як rtf");
        saveAsMenu.add(rtfItem);
        JMenuItem openDocumentItem = new JMenuItem("Зберегти як open document");
        saveAsMenu.add(openDocumentItem);
        JMenuItem txtItem = new JMenuItem("Зберегти як txt");
        saveAsMenu.add(txtItem);
        JMenuItem otherItem = new JMenuItem("Зберегти в іншому форматі");
        saveAsMenu.add(otherItem);

        // Файл > Друк
        JMenu printMenu = new JMenu("Друк");
        fileMenu.add(printMenu);

        JMenuItem usualPrint = new JMenuItem("Друк");
        usualPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        printMenu.add(usualPrint);

        JMenuItem quickPrint = new JMenuItem("Швидкий друк");
        printMenu.add(quickPrint);

        JMenuItem previewItem = new JMenuItem("Попередній перегляд");
        printMenu.add(previewItem);

        // Файл > Параметри сторінки
        JMenuItem pageSetupItem = new JMenuItem("Параметри сторінки");
        fileMenu.add(pageSetupItem);

        // Файл > Надіслати електронною поштою
        JMenuItem sendMailItem = new JMenuItem("Надіслати електронною поштою");
        fileMenu.add(sendMailItem);

        // Файл > Про програму
        JMenuItem aboutItem = new JMenuItem("Про програму");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(aboutItem);

        // Show dialog on "Про програму" selection
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Ця програма крута!", "Про програму", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Файл > Вийти
        JMenuItem exitItem = new JMenuItem("Вийти");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(exitItem);

        // Add Action Listener to Exit Item
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Основне Menu
        JMenu mainMenu = new JMenu("Основне");
        menuBar.add(mainMenu);

        JMenuItem pasteItem = new JMenuItem("Вставити");
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        mainMenu.add(pasteItem);

        JMenuItem copyItem = new JMenuItem("Копіювати");
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        mainMenu.add(copyItem);

        JMenuItem cutItem = new JMenuItem("Вирізати");
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        mainMenu.add(cutItem);

        // Вигляд Menu
        JMenu viewMenu = new JMenu("Вигляд");
        menuBar.add(viewMenu);

        JMenuItem zoomInItem = new JMenuItem("Збільшити");
        zoomInItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
        viewMenu.add(zoomInItem);

        JMenuItem zoomOutItem = new JMenuItem("Зменшити");
        zoomOutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
        viewMenu.add(zoomOutItem);

        JMenuItem defaultZoomItem = new JMenuItem("100%");
        defaultZoomItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK));
        viewMenu.add(defaultZoomItem);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PC4_1GUI();
            }
        });
    }
}
