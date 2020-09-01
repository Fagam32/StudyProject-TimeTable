package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Train implements Serializable {

    private String trainName;

    private String fromStation;

    private String toStation;

    private String departure;

    private String arrival;

    @JsonIgnore
    private String[] path;
    @JsonIgnore
    private String[] tickets;
    @JsonIgnore
    private Integer seatsNumber;

}
