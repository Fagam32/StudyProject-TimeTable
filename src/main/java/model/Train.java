package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Named
@SessionScoped
public class Train implements Serializable {

    private String trainName;

    private String fromStation;

    private String toStation;

    private String departure;

    private String arrival;

}
