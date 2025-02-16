package com.praktukym.gameServer.WSMessages;

public class updadeStateResponceMessage {
    final public String type = "updadeStateResponce";
    public String id;
    public char[][] Board;
    public int turn;
    

    public updadeStateResponceMessage(String id, char[][] board, int turn) {
        this.id = id;
        Board = board;
        this.turn = turn;
    }

}
