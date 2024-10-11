package CalculateLogic;

import java.util.ArrayList;

public class Tokenizer {
    public static ArrayList<Token> Tokenize(String expresion) throws Exception{
        ArrayList<Token> tokens = new ArrayList<>();

        for(int i = 0; i < expresion.length(); i++){

            StringBuffer buffer = new StringBuffer();
            char currentChar = expresion.charAt(i);

            if(currentChar == ' '){
                continue;
            }

            buffer.append(currentChar);

            if(Character.isDigit(currentChar)){
                //buffer.append(currentChar);
                    //i++;
                buffer.setLength(0);
                while(Character.isDigit(currentChar) || currentChar == '.'){
                    currentChar = expresion.charAt(i);
                    buffer.append(currentChar);
                    i++; 
                    currentChar = expresion.charAt(i);
                }
                i--;
                tokens.add(GetToken(buffer.toString()));
            }

            else if(Character.isLetter(currentChar)){
                //buffer.append(currentChar);
                i++;
                while(Character.isLetter(currentChar)){
                    currentChar = expresion.charAt(i);
                    buffer.append(currentChar);
                    i++; 
                }
                i--;
                tokens.add(GetToken(buffer.toString()));
            }
            else{
              tokens.add(GetToken(buffer.toString()));
            }
        }

        return tokens;
    }

    private static Token GetToken(String buffer) throws Exception{
        Token token;
        
        if(buffer.toString().equals("+")){
            token = new Token(TokenType.PLUS);
        }else if(buffer.toString().equals("*")){
            token = new Token(TokenType.MULTY);
        }else if(buffer.toString().equals("^")){
            token = new Token(TokenType.POWER);
        }else if(buffer.toString().equals("-")){
            token = new Token(TokenType.MINUS);
        }else if(buffer.toString().equals("/")){
            token = new Token(TokenType.DIVISION);
        }else if(buffer.toString().equals("(")){
            token = new Token(TokenType.LEFT_BRACKET);
        }else if(buffer.toString().equals(")")){
            token = new Token(TokenType.RIGHT_BRACKET);
        }else if(Character.isDigit(buffer.charAt(0))){
            token = new Token(TokenType.NUMBER, Double.parseDouble(buffer.toString()));
        }
        else if(Character.isLetter(buffer.charAt(0))){
            if(buffer.toString() == "cos"){
                token = new Token(TokenType.COS);
            }
            else if(buffer.toString() == "sin"){
                token = new Token(TokenType.SIN);
            }
            else if(buffer.toString() == "sqrt"){
                token = new Token(TokenType.SQRT);
            }
            else{
                token = new Token(TokenType.IDENTYFYER, buffer.toString());
            }
        }
        else{
            throw new Exception("Exception message" + buffer.toString());
        }
        return token;
    }
}



/*
 * 
 * 
 * List<Token> result = new ArrayList<>();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+') {
                result.add(new Token(TokenType.plus, 0, null));
            } else if (str.charAt(i) == '*') {
                result.add(new Token(TokenType.times, 0, null));
            } else if (str.charAt(i) == '^') {
                result.add(new Token(TokenType.stepin, 0, null));
            } else if (str.charAt(i) == '-') {
                result.add(new Token(TokenType.minus, 0, null));
            } else if (str.charAt(i) == '/') {
                result.add(new Token(TokenType.divide, 0, null));
            } else if (str.charAt(i) == '(') {
                result.add(new Token(TokenType.leftBarcke, 0, null));
            } else if (str.charAt(i) == ')') {
                result.add(new Token(TokenType.rightBracke, 0, null));
            } else if (Character.isDigit(str.charAt(i))) {
                buf.append(str.charAt(i));
                i++;
                while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.')) {
                    buf.append(str.charAt(i));
                    i++;
                }
                i--;
                result.add(new Token(TokenType.numbe, Float.parseFloat(buf.toString()), null));
                buf.setLength(0);
            } else if (Character.isAlphabetic(str.charAt(i))) {
                buf.append(str.charAt(i));
                i++;
                while (i < str.length() && Character.isAlphabetic(str.charAt(i))) {
                    buf.append(str.charAt(i));
                    i++;
                }
                i--;
                if (buf.toString().equals("sin")) {
                    result.add(new Token(TokenType.sing, 0, null));
                } else if (buf.toString().equals("cos")) {
                    result.add(new Token(TokenType.cosg, 0, null));
                } else if (buf.toString().equals("sqrt")) {
                    result.add(new Token(TokenType.sqrtq, 0, null));
                } else {
                    result.add(new Token(TokenType.identyfie, 0, buf.toString()));
                }
                buf.setLength(0);
            } else if (str.charAt(i) == ' ') {
                continue;
            } else {
                System.err.println("wtf man? \"" + str.charAt(i) + "\"");
                System.exit(1);
            }
        }
        return result;
    }
 */