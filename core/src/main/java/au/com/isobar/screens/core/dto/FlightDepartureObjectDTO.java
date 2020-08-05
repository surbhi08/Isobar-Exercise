package au.com.isobar.screens.core.dto;

import java.util.List;

public class FlightDepartureObjectDTO {

    public List<DepartList> getDepart() {
        return depart;
    }

    public void setDepart(List<DepartList> depart) {
        this.depart = depart;
    }

    public List<DepartList> depart;

}
