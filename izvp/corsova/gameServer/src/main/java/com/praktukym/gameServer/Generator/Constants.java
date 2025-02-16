package com.praktukym.gameServer.Generator;

public class Constants {
    public static final char[][] board = {{'.', '.', '.', '.', '.', '.', '.', '.', '.'}, 
                                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'}, 
                                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'}, 
                                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'}, 
                                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'}, 
                                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'}, 
                                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'}, 
                                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'}, 
                                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'}};
    public static char[][] getEmtyBoard(){
        
        char[][] emptyBoard = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            emptyBoard[i] = board[i].clone();
        }
        return emptyBoard;
    }
                                    
}
