package beans;

import model.Station;
import model.Train;
import service.TrainService;
import websocket.StationWebSocketEndpoint;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class StationBean implements Serializable {

    private Station station;

    private List<Train> trainList;

    @Inject
    private TrainService trainService;

    public void setStation(Station station) {
        this.station = station;
    }

    public void loadTrains() {
        if (station.getName() != null && !station.getName().isBlank()) {
            trainList = trainService.getTrainListOnStation(station.getName());
        }

    }

    public Station getStation() {
        if (station == null)
            station = new Station();
        return station;
    }

    public List<Train> getTrainList() {
        if (trainList == null)
            trainList = new ArrayList<>();
        return trainList;
    }
}
