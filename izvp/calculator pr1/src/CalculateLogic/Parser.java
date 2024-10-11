package CalculateLogic;

import java.util.ArrayList;

public class Parser {
    static int curentToken = 0;
    private static int GetPrecedence(Token current){
        switch (current.getType()) {
            case PLUS:
            case MINUS:  
                return 1;

            case MULTY:
            case DIVISION: 
                return 2;

            case POWER:
                return 3;

            default:
                return -1;
        }
    }

    static double ComputeOperation(Token current, double lhs, double rhs) throws Exception{
        if(current.getType() == TokenType.PLUS){
            return lhs + rhs;
        }
        else if(current.getType() == TokenType.MINUS){
            return lhs - rhs;
        }
        else if(current.getType() == TokenType.MULTY){
            return lhs * rhs;
        }
        else if(current.getType() == TokenType.DIVISION){
            try{
                return lhs / rhs;
            }
            catch(ArithmeticException e){
                throw new Exception("Division by zero!");
            }
        }
        else if(current.getType() == TokenType.POWER){
            return Math.pow(lhs, rhs);
        }
        else{
            return -1;
        }
    }

    private static double ComputeExpresion(int minPrecedence, ArrayList<Token> tokens) throws Exception{
        double lhs = ComputeAtom(tokens);
        while (true) {
            if(curentToken >= tokens.size()){
                break;
            }

            Token current = tokens.get(curentToken);

            if((GetPrecedence(current) < minPrecedence)|| (GetPrecedence(current) == -1)){
                break;
            }

            int nextPrecedence = GetPrecedence(current) + 1;
            curentToken++;
            double rhs = ComputeExpresion(nextPrecedence, tokens);

            lhs = ComputeOperation(current, lhs, rhs);

        }
        return lhs;

    }

    private static double ComputeAtom(ArrayList<Token> tokens) throws Exception{
        Token curent = tokens.get(curentToken);
        if(curent.type == TokenType.LEFT_BRACKET){
            curentToken++;
            double value = ComputeExpresion(1, tokens);
            if(tokens.get(curentToken).getType() != TokenType.RIGHT_BRACKET){
                throw new Exception("There no right bracket!");
            }
            curentToken++;
            return value;
        }
        else if(curent.getType() == TokenType.COS){
            curentToken++;
            return Math.cos(ComputeExpresion(1, tokens));
        }
        else if(curent.getType() == TokenType.SIN){
            curentToken++;
            return Math.sin(ComputeExpresion(1, tokens));
        }
        else if(curent.getType() == TokenType.SQRT){
            curentToken++;
            try{

            Double value = Math.sqrt(ComputeExpresion(1, tokens));
            return value;
            }
            catch(ArithmeticException e){
                throw new Exception("sqrt of negative number");
            }
            
        }
        else if(curent.getType() == TokenType.PI){
            curentToken++;
            return Math.PI;
        }
        curentToken++;
        return curent.getValue();
    }

    public static Double Compute(ArrayList<Token> tokens) throws Exception{
        curentToken = 0;
        return ComputeExpresion(1, tokens);
    }
  
}
