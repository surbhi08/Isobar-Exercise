package au.com.isobar.screens.core.dto;

public class ArriveList {

    public String departMelbourne;

    public String getDepartMelbourne() {
        return departMelbourne;
    }

    public void setDepartMelbourne(String departMelbourne) {
        this.departMelbourne = departMelbourne;
    }

    public String getArriveSydney() {
        return arriveSydney;
    }

    public void setArriveSydney(String arriveSydney) {
        this.arriveSydney = arriveSydney;
    }

    public String getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(String flightCost) {
        this.flightCost = flightCost;
    }

    public String getDiscountedCosts() {
        return discountedCosts;
    }

    public void setDiscountedCosts(String discountedCosts) {
        this.discountedCosts = discountedCosts;
    }

    public String arriveSydney;
    public String flightCost;
    public String discountedCosts;
}
