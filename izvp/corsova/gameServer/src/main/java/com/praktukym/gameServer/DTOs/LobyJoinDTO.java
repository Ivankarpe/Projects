package com.praktukym.gameServer.DTOs;

public class LobyJoinDTO {

    public String code;
    public String idplayer;

    public String getIdplayer() {
        return idplayer;
    }

    public void setIdplayer(String idplayer) {
        this.idplayer = idplayer;
    }

    public String getTopic() {
        return code;
    }

    public void setTopic(String topic) {
        this.code = topic;
    }
}
