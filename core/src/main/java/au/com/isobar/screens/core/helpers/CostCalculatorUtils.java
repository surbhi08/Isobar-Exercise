package au.com.isobar.screens.core.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.isobar.screens.core.constants.FlightConstants;


public class CostCalculatorUtils {
    private final static Logger LOG = LoggerFactory.getLogger(CostCalculatorUtils.class);

    public static double getcost(String clubLevel, int age, int cost) {

        double output = 0;
    

        //if bronze
        if (clubLevel.equals(FlightConstants.BRONZE)) {

            if (age <= 2) {
                output = (cost * 0.6);
            } else if (3 <= age && age >= 16) {
                output = (cost * 0.9);
            }
        }

        //if silver
        else if (clubLevel.equals(FlightConstants.SILVER)) {

            if (0 < age && age >= 8) {
                output = (cost * 0.8);
            } else if (9 <= age && age >= 16) {
                output = (cost * 0.9);
            }
        }

        //if gold
        else if (clubLevel.equals(FlightConstants.GOLD)) {

            if (0 < age && age >= 8) {
                output = (cost * 0.7);
            } else if (9 <= age && age >= 16) {
                output = (cost * 0.8);
            }

        }

        Double totalCost = output;
        LOG.info(totalCost.toString());
        
        return totalCost;
    }
}
