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
            else if(currentChar == 'π'){
                tokens.add(GetToken(buffer.toString()));
            }
            else if(Character.isLetter(currentChar)){
                
                buffer.setLength(0);
                while(Character.isLetter(currentChar)){
                    currentChar = expresion.charAt(i);
                    buffer.append(currentChar);
                    i++; 
                    currentChar = expresion.charAt(i);
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
        }else if(buffer.toString().equals("π")){
            token = new Token(TokenType.PI);
        }else if(buffer.toString().equals("(")){
            token = new Token(TokenType.LEFT_BRACKET);
        }else if(buffer.toString().equals(")")){
            token = new Token(TokenType.RIGHT_BRACKET);
        }else if(Character.isDigit(buffer.charAt(0))){
            token = new Token(TokenType.NUMBER, Double.parseDouble(buffer.toString()));
        }
        else if(Character.isLetter(buffer.charAt(0))){
            if(buffer.toString().equals("cos")){
                token = new Token(TokenType.COS);
            }
            else if(buffer.toString().equals("sin")){
                token = new Token(TokenType.SIN);
            }
            else if(buffer.toString().equals("sqrt")){
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