package com.praktukym.gameServer.WSsesions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class WSsesionService {

    static private Map<String, String> games; // <sesionid, topic>
    static private Map<String, ArrayList<WebSocketSession>> users; // <topic, sesionids>

    public WSsesionService() {
        games = new HashMap<>();
        users = new HashMap<>();
    }

    public void  addSession(WebSocketSession session, String topic) {
        if (users.containsKey(topic)) {
            users.get(topic).add(session);
        }
        else {
            ArrayList<WebSocketSession> sessions = new ArrayList<>();
            sessions.add(session);
            users.put(topic, sessions);
        }


        if(!games.containsKey(session.getId())) {
            games.put(session.getId(), topic);
        }
    }

    public void removeSession(WebSocketSession session) {
        if(games.containsKey(session.getId())) {
            String topic = games.get(session.getId());
            users.get(topic).remove(session);
            games.remove(session.getId());
        }
    }

    public boolean isUserInGame(String sessionid) {
        return games.containsKey(sessionid);
    }

    public String getGameTopic(String sessionid) {
        return games.get(sessionid);
    }

    public ArrayList<WebSocketSession> getUsersInGame(String topic) {
        ArrayList<WebSocketSession> result = new ArrayList<>();
        if(!users.containsKey(topic)){
            return result;
        }
        for (WebSocketSession session : users.get(topic)) {
            result.add(session);
        }
        return result;
    }
    
   


    


}
