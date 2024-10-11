import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class InputField extends JTextField {
    String expresion = "";
    InputField(){
        super();
    }
    public void AddPart(String part){
        if(part == "C" || part == "CE"){

        }else{
            
        }
        expresion += part;
        setText(expresion);
    }
}
