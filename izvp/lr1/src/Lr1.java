import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Lr1 extends JFrame {
    public Lr1() {
        setTitle("Лаборадорна робота №1");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<String> images = new ArrayList<>();
        images.add("Flag.jpg");
        images.add("image2.jpg");
        images.add("image3.jpg");

        PhotoLabel imageLabel = new PhotoLabel(images);

        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.updateImage();

        add(imageLabel, BorderLayout.CENTER);

        JButton buttLeft = new JButton("←");
        JButton buttRight = new JButton("→");

        JProgressBar progresbar =new JProgressBar();

        buttLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                imageLabel.previousImage();

                progresbar.setValue((progresbar.getValue() + 10)%100 );
            }
        });
        buttRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                imageLabel.nextImage();
                progresbar.setValue((progresbar.getValue() + 10)%100 );
            }
        });

        add(buttLeft, BorderLayout.WEST);
        add(buttRight, BorderLayout.EAST);
        add(progresbar, BorderLayout.SOUTH);

        setVisible(true);
    }

    
   

    public static void main(String[] args) {
        Lr1 lr = new Lr1();
    }
}
