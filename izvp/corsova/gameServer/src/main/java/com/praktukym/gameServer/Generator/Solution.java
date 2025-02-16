package com.praktukym.gameServer.Generator;

import java.util.EmptyStackException;
import java.util.Stack;

public class Solution {
    static char[][] initialboard;

    public static void main(String[] args) throws Exception {

        // char[][] board = { { '5', '.', '.', '.', '.', '7', '.', '1', '.' },
        // { '.', '4', '.', '.', '.', '.', '.', '.', '.' },
        // { '1', '9', '.', '.', '.', '.', '.', '.', '.' },
        // { '9', '.', '.', '.', '.', '.', '.', '4', '5' },
        // { '.', '.', '.', '3', '.', '9', '.', '.', '6' },
        // { '4', '.', '3', '.', '.', '6', '.', '.', '.' },
        // { '7', '3', '.', '5', '.', '.', '.', '.', '.' },
        // { '.', '.', '.', '.', '.', '2', '.', '7', '9' },
        // { '.', '1', '.', '.', '6', '.', '.', '.', '.' } };

        // char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        // {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        // {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        // {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        // {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        // {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        // {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        // {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        // {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        char[][] board ={{'.', '.', '.', '.', '.', '.', '.', '7', '.'},
                         {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                         {'4', '9', '.', '.', '.', '.', '8', '5', '.'},
                         {'.', '.', '.', '.', '.', '.', '5', '.', '.'},
                         {'.', '.', '.', '.', '.', '3', '.', '9', '1'},
                         {'.', '.', '.', '.', '.', '.', '.', '3', '7'},
                         {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                         {'.', '8', '4', '.', '.', '.', '.', '.', '.'},
                         {'9', '.', '7', '.', '.', '5', '.', '4', '2'},};
            
 
        long startTime = System.nanoTime();
        solveSudoku(board);

        // End timing
        long endTime = System.nanoTime();

        // Calculate elapsed time in milliseconds
        long elapsedTime = (endTime - startTime) / 1_000_000;
        System.out.println("Time taken: " + elapsedTime + " ms");
    }

    public static boolean checkPlace(char[][] board, int x, int y, int num) {
        int xSq = x / 3;
        int ySq = y / 3;

        for (int i = xSq * 3; i < (xSq + 1) * 3; i++) {
            for (int j = ySq * 3; j < (ySq + 1) * 3; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (Character.getNumericValue(board[j][i]) == num) {
                    return false;
                }

            }
        }

        for (int i = 0; i < 9; i++) {
            if (Character.getNumericValue(board[i][x]) == num || Character.getNumericValue(board[y][i]) == num) {
                return false;
            }
        }

        return true;

    }

    public static char[][] solveSudoku(char[][] board) {

        initialboard = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            initialboard[i] = board[i].clone();
        }

        Stack<Integer> s = new Stack<>();
        int current = 0;

        while (current < 81) {
            // System.err.println(current);
            int x = current % 9;
            int y = current / 9;
            if (initialboard[y][x] != '.') {
                current++;
                continue;
            }

            int num;

            if (board[y][x] == '.') {
                num = 1;
            } else {
                num = Character.getNumericValue(board[y][x]) + 1;
                if (num > 9) {
                    board[y][x] = '.';
                   

                    current = s.pop();

                    continue;
                }
            }
            for (; num <= 9; num++) {
                if (checkPlace(board, x, y, num)) {
                    board[y][x] = Character.forDigit(num, 10);
                    s.add(current);
                    current++;
                    break;
                }
                if (num == 9) {

                    board[y][x] = '.';
                    
                    current = s.pop();

                    break;
                }

            }

        }

        // printBoard(board);
        return (board);
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
    public static boolean ifSolvable(char[][] board){
        char[][] copyBoard = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            copyBoard[i] = board[i].clone();
        }
        try{
            solveSudoku(copyBoard);
        }
        catch(EmptyStackException e){
            return false;
        }
        return true;
    }
}