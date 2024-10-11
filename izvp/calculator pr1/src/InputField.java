import java.util.ArrayList;

import javax.swing.*;
import CalculateLogic.*;

public class InputField extends JTextField {
    String expresion = "";
    InputField(){
        super();
    }
    public void AddPart(String part) throws Exception{
        if(part == "C"){
            expresion = "";
        }else if(part == "CE"){
            ArrayList<Token> tokens = Tokenizer.Tokenize(expresion+ "    ");
            double value = Parser.Compute(tokens);
            expresion = Double.toString(value);
        }
        else{
            expresion += part;
        }
        setText(expresion);
    }
}
