package model;

import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Data
@Named
@SessionScoped
public class Station implements Serializable {
    private String name;
}
