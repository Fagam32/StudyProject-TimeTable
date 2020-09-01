package beans;

import model.Station;
import model.Train;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class StationBean implements Serializable {

    private Station station;

    private List<Train> trainList;

    public void setStation(Station station) {
        this.station = station;
    }

    public void loadTrains() {
        trainList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Train train = new Train("Train_" + i, "From_" + i, "To_" + i, "09-09-2020 10:0" + i, "09-09-2020 11:0" + i);
            trainList.add(train);
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
