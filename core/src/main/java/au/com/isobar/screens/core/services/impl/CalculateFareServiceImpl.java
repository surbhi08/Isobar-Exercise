package au.com.isobar.screens.core.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.isobar.screens.core.constants.FlightConstants;
import au.com.isobar.screens.core.helpers.CostCalculatorUtils;
import au.com.isobar.screens.core.services.CalculateFareService;

@Component(immediate = true, metatype = true)
@Service(value = CalculateFareService.class)
@Properties({ @Property(name = "serviceType", value = "calculateFareService") })
public class CalculateFareServiceImpl implements CalculateFareService {

    private static final Logger LOG = LoggerFactory.getLogger(CalculateFareServiceImpl.class);
    String club = null;


    String clubValue = FlightConstants.BOOKING_PASSENGER_CLUB_LEVEL;
    int numberOfAdultPassengers = FlightConstants.NUMBER_OF_ADULT_PASSENGER;
    int numberOfKidsPassengers = FlightConstants.NUMBER_OF_KIDS;
    int kidsAge = FlightConstants.KIDS_AGE;


    Gson gson = new Gson();

    @Override
    public JsonObject getFareSydToMelb() {

        String jsonData = FlightConstants.DEPARTURE_JSON_STR;
        JsonObject jdata = null;

        try {
            jdata = gson.fromJson(jsonData, JsonObject.class);
            if (!jdata.isJsonNull()) {
                JsonArray jarray = jdata.getAsJsonArray("Departure");
                for(int i = 0; i < jarray.size(); i++) {
                    club = jarray.get(0).getAsJsonObject().get("Cost").getAsString();
                    int intCost = Integer.parseInt(club);

                   String totalCost = CostCalculatorUtils.getcost(clubValue,kidsAge,intCost,numberOfAdultPassengers,numberOfKidsPassengers);
                   LOG.info("Cost after the clubs discounts being applied",totalCost);

                    jarray.add(new JsonPrimitive(totalCost));
                    jdata.add("discounted costs",jarray);

                    }


                }

        }catch (Exception e){
            e.printStackTrace();
        }

        return jdata;
    }

    @Override
    public JsonObject getfareMelbToSyd() {

        String jsonData = FlightConstants.ARRIVAL_JSON_STR;
        JsonObject jDataArrival= null;

        try {
            jDataArrival = gson.fromJson(jsonData, JsonObject.class);
            if (!jDataArrival.isJsonNull()) {
                JsonArray jarray = jDataArrival.getAsJsonArray("Arrival");
                for(int j= 0; j < jarray.size(); j++) {
                    club = jarray.get(0).getAsJsonObject().get("Cost").getAsString();
                    int intCostArrival = Integer.parseInt(club);

                    String totalCostArrival = CostCalculatorUtils.getcost(clubValue,kidsAge,intCostArrival,numberOfAdultPassengers,numberOfKidsPassengers);

                    jarray.add(new JsonPrimitive(totalCostArrival));
                    jDataArrival.add("discounted costs",jarray);

                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return jDataArrival;
    }
    
}