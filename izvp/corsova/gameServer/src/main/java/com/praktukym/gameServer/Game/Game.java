package com.praktukym.gameServer.Game;

public class Game {

    private int status;

    private String topic;

    private String host;
    private String guest;

    private char[][] hostBoard;
    private char[][] guestBoard;

    public String CheckWinner() {

        char[][] board = hostBoard;
        boolean hostWin = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(board[i][j] == 'S') {
                    hostWin = false;
                }
            }
        }
        char[][] board2 = guestBoard;
        boolean guestWin = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(board2[i][j] == 'S') {
                    guestWin = false;
                }
            }
        }

        if(hostWin) {
            return host;
        }
        if(guestWin) {
            return guest;
        }
        return null;
    }
    public String getGuest() {
        return guest;
    }

    public char[][] getGuestBoard() {
        return guestBoard;
    }

    public String getHost() {
        return host;
    }

    public char[][] getHostBoard() {
        return hostBoard;
    }

    

    public int getStatus() {
        return status;
    }

    public String getTopic() {
        return topic;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public void setGuestBoard(char[][] guestBoard) {
        this.guestBoard = guestBoard;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setHostBoard(char[][] hostBoard) {
        this.hostBoard = hostBoard;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
