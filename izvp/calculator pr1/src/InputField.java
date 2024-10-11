import java.util.ArrayList;

import javax.swing.*;
import CalculateLogic.*;

public class InputField extends JTextField {
    String expresion = "";
    InputField(){
        super();
    }
    public void AddPart(String part){
        if(expresion.contains(" ")){
            expresion = "";
        }
        if(part == "<-"){
            if(!expresion.isEmpty()){
                expresion = expresion.substring(0, expresion.length()-1);
            }
            }
            else if(part == "C"){
            expresion = "";
        }
        else if(part == "="){
            try{

            
            ArrayList<Token> tokens = Tokenizer.Tokenize(expresion+ "    ");
            double value = Parser.Compute(tokens);
            expresion = Double.toString(value);
            }
            catch(Exception e){
                expresion = e.getMessage() + " ";
            }
        }
        else{
            expresion += part;
        }
        setText(expresion);
    }
}
