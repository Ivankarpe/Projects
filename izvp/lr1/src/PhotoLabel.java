import javax.swing.*;

import java.util.ArrayList;

public class PhotoLabel extends JLabel{
    private ArrayList<String> images;
    int currentIndex = 0;


    public PhotoLabel(ArrayList<String> images){
        this.images = images;
    }

    public void updateImage() {
        ImageIcon icon = new ImageIcon(images.get(currentIndex));
        setIcon(icon);
    }

    
    public void nextImage() {
        currentIndex = (currentIndex + 1) % images.size();
        updateImage();
    }

    
    public void previousImage() {
        currentIndex = (currentIndex - 1 + images.size()) % images.size();
        updateImage();
    }

}
