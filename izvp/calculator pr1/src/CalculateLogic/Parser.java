package CalculateLogic;

import java.util.ArrayList;

public class Parser {
    static int curentToken = 0;
    private static int GetPrecedence(Token current){
        switch (current.getType()) {
            case TokenType.PLUS:
            case TokenType.MINUS:  
                return 1;

            case TokenType.MULTY:
            case TokenType.DIVISION: 
                return 2;

            case TokenType.POWER:
                return 3;

            default:
                return -1;
        }
    }

    static double ComputeOperation(Token current, double lhs, double rhs){
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
            return lhs / rhs;
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
                throw new Exception("there isn't right bracket");
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
            return Math.sqrt(ComputeExpresion(1, tokens));
        }
        curentToken++;
        return curent.getValue();
    }

    public static Double Compute(ArrayList<Token> tokens) throws Exception{
        curentToken = 0;
        return ComputeExpresion(1, tokens);
    }
  
}
