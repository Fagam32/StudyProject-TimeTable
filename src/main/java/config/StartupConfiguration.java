package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Station;
import websocket.StationWebSocketEndpoint;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.websocket.Session;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Startup
@Singleton
public class StartupConfiguration {

    @PostConstruct
    public void getStationsFromRemoteServer() {
        List<Station> stationList = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            Station[] stations = mapper.readValue(new URL("http://localhost:8000/stations"), Station[].class);
            stationList = Arrays.asList(stations);
        } catch (Throwable ex) {
            System.out.print("No connection established");
        }
        if (stationList == null) {
            stationList = new ArrayList<>();
            Station s = new Station();
            s.setName("Abc");
            stationList.add(s);
        }
        ConcurrentHashMap<String, ArrayList<Session>> connections = StationWebSocketEndpoint.getConnections();
        for (Station s : stationList) {
            connections.put(s.getName(), new ArrayList<>());
        }
    }
}
