import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SimpleTableTest extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table1;

    private DefaultTableModel tableModel2;
    private JTable table2;

    private List<Product> products = new ArrayList<>();

    private Object[] columnsHeader = new String[] { "Id", "Name", "Price", "Count" };

    TextField idField;
    TextField nameField;
    TextField priceField;
    TextField countField;

    Icon ficon = new ImageIcon("hq720.jpg");
    Icon sicon = new ImageIcon("images.jpg");
    Icon ticon = new ImageIcon("sddefault.jpg");

    public SimpleTableTest() {
        super("лабарадорна 4");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnsHeader);

        products.add(new Product("123", "iphone", 100, 155.35 ));
        products.add(new Product("124", "mac", 125, 35.52 ));
        products.add(new Product("125", "apple", 25, 25.69 ));


        for (Product product : products){
            tableModel.addRow(new Object[]{product.id, product.name, product.price, product.count});
        }

        table1 = new JTable(tableModel);

        
        JScrollPane scrollpane = new JScrollPane(table1);

        JPanel textFields = new JPanel( new GridLayout(1,4));

        
        idField = new TextField();
        nameField = new TextField();
        priceField = new TextField();
        countField = new TextField();

        textFields.add(idField);
        textFields.add(nameField);
        textFields.add(priceField);
        textFields.add(countField);

        scrollpane.add(textFields);

        JButton addButt = new JButton("add");
        JButton delButt = new JButton("delete");

        addButt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               AddRow();
            }
           
        });

        delButt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               removeRow();
            }
           
        });
        JPanel buttons = new JPanel(new FlowLayout());

        buttons.add(addButt);
        buttons.add(delButt);





        tableModel2 = new DefaultTableModel(){
            public Class getColumnClass(int column){
                return getValueAt(0, column).getClass();
            }
        };
        tableModel2.setColumnIdentifiers( new String[] { "Name", "sum", "photo" });


        for (Product product : products){
            Icon photo = ficon;
            
            if(product.photo == 1){
                photo = sicon;
            }if(product.photo == 2){
                photo = ticon;
            }
            tableModel2.addRow(new Object[]{product.name, product.price * product.count, photo});
        }

        table2 = new JTable(tableModel2);
        table2.setRowHeight(150);
        JScrollPane scrollpane2 = new JScrollPane(table2);


        Box box = Box.createVerticalBox();
        box.add(scrollpane);
        box.add(Box.createVerticalStrut(10));
        box.add(textFields);
        box.add(Box.createVerticalStrut(10));
        box.add(buttons);
        box.add(Box.createVerticalStrut(10));
        box.add(scrollpane2);

        add(box);
        

        setSize(1920, 1080);
        setVisible(true);
    }

    public void AddRow(){
        
        products.add(new Product(idField.getText(), nameField.getText(),Integer.parseInt(countField.getText()), Double.parseDouble(priceField.getText())   ));
        tableModel.setRowCount(0);
        tableModel2.setRowCount(0);
        for (Product product : products){
            tableModel.addRow(new Object[]{product.id, product.name, product.price, product.count});
        }
        for(Product product : products){
            Icon photo = ficon;
            
            if(product.photo == 2){
                photo = sicon;
            }if(product.photo == 3){
                photo = ticon;
            }
            tableModel2.addRow(new Object[]{product.name, product.price * product.count, photo});
        }                   
    }


    public void removeRow(){
        
        int[] selectedRows = table1.getSelectedRows();
        if (selectedRows.length > 0) {
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                tableModel.removeRow(selectedRows[i]);
            }
        }
    }

    public static void main(String[] args) {
        SimpleTableTest ap = new SimpleTableTest();
    }
}