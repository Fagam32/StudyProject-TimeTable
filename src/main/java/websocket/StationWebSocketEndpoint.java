package websocket;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/stations/{stationName}")
public class StationWebSocketEndpoint {
    private static final ConcurrentHashMap<String, ArrayList<Session>> connections = new ConcurrentHashMap<>();


    public static void send(String stationName, String message) {
        ArrayList<Session> sessions = connections.get(stationName);
        if (sessions != null) {
            for (Session s : sessions) {
                try {
                    s.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnOpen
    public void openConnection(@PathParam("stationName") String stationName,
                               Session session) {
        if (connections.get(stationName) != null) {
            connections.get(stationName).add(session);
        }
    }

    @OnClose
    public void closeConnection(@PathParam("stationName") String stationName,
                                Session session) {
        if (connections.get(stationName) != null) {
            connections.get(stationName).remove(session);
        }
    }

    public static ConcurrentHashMap<String, ArrayList<Session>> getConnections() {
        return connections;
    }
}
