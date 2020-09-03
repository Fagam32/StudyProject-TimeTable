package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.StationInfo;
import model.Train;

import javax.ejb.Stateless;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateless
public class TrainService implements Serializable {

    public List<Train> getTrainListOnStation(String stationName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        URL url;
        List<Train> trainList = null;
        try {
            url = new URL("http://localhost:8000/trains/" + stationName + "?date=" + LocalDate.now());
            Train[] trains = mapper.readValue(url, Train[].class);
            trainList = new ArrayList<>(Arrays.asList(trains));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //remove all stationInfos except necessary one
        if (trainList != null) {
            for (Train train : trainList) {
                List<StationInfo> path = train.getPath();
                path.removeIf(s -> !s.getStationName().equalsIgnoreCase(stationName));
            }
        } else {
            trainList = new ArrayList<>();
        }

        return trainList;
    }
}
