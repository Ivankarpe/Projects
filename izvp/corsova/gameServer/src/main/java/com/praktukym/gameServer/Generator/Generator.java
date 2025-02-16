package com.praktukym.gameServer.Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Generator {

    public static void main(String[] args) throws Exception {
        

        // for (int i = 0; i < 10000; i++) {
        //     long startTime = System.nanoTime();

        //     printBoard(GenerateBoard(70));
        //     // System.err.println();
            
        //     long endTime = System.nanoTime();

        //     long elapsedTime = (endTime - startTime) / 1_000_000;
        //     System.out.println("Time elapsed: " + elapsedTime + " milliseconds"+i);
        // }

        int aptemptss = 10;

        long startTime = System.nanoTime();
        
        for (int i = 0; i < aptemptss; i++) {

            
           //printBoard(Solution.solveSudoku(GenerateBoard(100)));
          //System.err.println();

            printBoard(Solution.solveSudoku(GenerateBoard(78)));
            // printBoard(GenerateBoard(78));
            //GenerateBoard(78);
            //System.err.println();
            System.err.println();
            if(i % 100 == 0){

            }

        }
            long endTime = System.nanoTime();

            long elapsedTime = (endTime - startTime) / 1_000_000;
            System.out.println("Time elapsed: " + elapsedTime + " milliseconds");

    }

    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public static char[][] GenerateBoard(int numberOfHoles) {
        char[][] board = Constants.getEmtyBoard();
        Random rand = new Random();

        ArrayList<Integer> cords = new ArrayList<>(81);
        for (int i = 0; i < 81; i++) {
            cords.add(i);
        }
        

        List<Integer> numList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        Collections.shuffle(numList);

            

        
        for(int i = 0; i<9; i++){

            board[0][i] = Character.forDigit(numList.get(i), 10);
        }
        
        
        Solution.solveSudoku(board);

        board[0][0] = '.';


        cords.clear();
            for (int j = 9; j < 81; j++) {
                cords.add(j);
            }
        for (int i = 0; i < numberOfHoles/2; i++) {
            

            int randCord = rand.nextInt(cords.size());
            int pos = cords.get(randCord);
            cords.remove(randCord);

            int x = pos % 9;
            int y = pos / 9;

            
            board[y][x] = '.';
            
        }


        Solution.solveSudoku(board);
        
        board[0][0] = '.';

        for (int i = 0; i < numberOfHoles; i++) {
            cords.clear();
            for (int j = 0; j < 81; j++) {
                cords.add(j);
            }

            int randCord = rand.nextInt(cords.size());
            int pos = cords.get(randCord);
            cords.remove(randCord);

            int x = pos % 9;
            int y = pos / 9;

            int current = Character.getNumericValue(board[y][x]);
            if(current == -1){
                continue;
            }
            boolean solvable = false;
            for (int j = 1; j < 9; j++) {
                if (j == current) {
                    continue;
                }
               
                if (Solution.checkPlace(board, x, y, j)) {
                    board[y][x] = Character.forDigit(j, 10);
                    if (Solution.ifSolvable(board)) {
                        solvable = true;
                        break;
                    }
                }
            }

            if (solvable) {
                
                board[y][x] = Character.forDigit(current, 10);
            } else {
                board[y][x] = '.';
            }
        }

        return board;
    }

}
