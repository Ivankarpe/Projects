package CalculateLogic;

public class Token {
    TokenType type;
    double value;
    String identyfier;

    Token(TokenType type, double value){
        setType(type);
        setValue(value);
    }

    Token(TokenType type, String identyfier){
        setType(type);
        setIdentyfier(identyfier);
    }
    Token(TokenType type){
        setType(type);
    }

    public String getIdentyfier() {
        return identyfier;
    }
    public TokenType getType() {
        return type;
    }
    public double getValue() {
        return value;
    }
    public void setIdentyfier(String identyfier) {
        this.identyfier = identyfier;
    }
    public void setType(TokenType type) {
        this.type = type;
    }
    public void setValue(double value) {
        this.value = value;
    }
}
