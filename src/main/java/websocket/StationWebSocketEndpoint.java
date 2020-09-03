package websocket;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/stations/{stationName}")
public class StationWebSocketEndpoint {
    private static final ConcurrentHashMap<String, Session> connections = new ConcurrentHashMap<>();


    public static void send(String stationName, String message) {
        Session session = connections.get(stationName);
        if (session != null)
            try {

                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @OnOpen
    public void openConnection(@PathParam("stationName") String stationName,
                               Session session) {
        connections.put(stationName, session);
    }

    @OnClose
    public void closeConnection(@PathParam("stationName") String stationName) {
        connections.remove(stationName);
    }

}
