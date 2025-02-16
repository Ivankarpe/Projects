package com.praktukym.gameServer.WSMessages;

public class winnerMessage {
    final public String type = "winner";
    public String id;

    public winnerMessage(String id) {
        this.id = id;
    }
}
