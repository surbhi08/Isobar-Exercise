package au.com.isobar.screens.core.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.isobar.screens.core.constants.FlightConstants;


public class CostCalculatorUtils {
    private final static Logger LOG = LoggerFactory.getLogger(CostCalculatorUtils.class);

    public static String getcost(String clubLevel, int age, int cost, int noOfAdults, int noOfKids) {

        double output = 0;
        double adultsCost = noOfAdults * cost;

        //if bronze
        if (clubLevel.equals(FlightConstants.BRONZE)) {

            if (age >= 2) {
                output = noOfKids * (cost * 0.6) + adultsCost;
            } else if (3 <= age && age >= 16) {
                output = noOfKids * (cost * 0.9) + adultsCost;
            }
        }

        //if silver
        else if (clubLevel.equals(FlightConstants.SILVER)) {

            if (0 < age && age >= 8) {
                output = noOfKids * (cost * 0.8) + adultsCost;
            } else if (9 <= age && age >= 16) {
                output = noOfKids * (cost * 0.9) + adultsCost;
            }
        }

        //if gold
        else if (clubLevel.equals(FlightConstants.GOLD)) {

            if (0 < age && age >= 8) {
                output = noOfKids * (cost * 0.7) + adultsCost;
            } else if (9 <= age && age >= 16) {
                output = noOfKids * (cost * 0.8) + adultsCost;
            }

        }

        String totalCost = String.valueOf(output);
        LOG.info(totalCost);
        return totalCost;

    }
}
