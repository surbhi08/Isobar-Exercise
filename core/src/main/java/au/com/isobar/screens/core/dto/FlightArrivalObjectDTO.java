package au.com.isobar.screens.core.dto;

import java.util.List;

public class FlightArrivalObjectDTO {

    public List<ArriveList> getArrive() {
        return arrive;
    }

    public void setArrive(List<ArriveList> arrive) {
        this.arrive = arrive;
    }

    public List<ArriveList> arrive;
}
