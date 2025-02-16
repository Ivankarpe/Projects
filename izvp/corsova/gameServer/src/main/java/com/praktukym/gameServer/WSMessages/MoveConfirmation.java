package com.praktukym.gameServer.WSMessages;

public class MoveConfirmation{
    final public String type = "MoveConfirmation";
    public String id;
    public boolean result;

    public MoveConfirmation(String id, boolean result) {
        this.id = id;
        this.result = result;
    }

}
