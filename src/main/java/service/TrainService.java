package service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        URL url;
        List<Train> trainList = null;
        try {
            url = new URL("http://localhost:8000/trains/" + stationName + "?date=" + LocalDate.now());
            System.out.println(url.toString());
            Train[] trains = mapper.readValue(url, Train[].class);
            trainList = Arrays.asList(trains);
            System.out.println("Trains were successfully added");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (trainList == null)
            trainList = new ArrayList<>();

        return trainList;
    }
}
