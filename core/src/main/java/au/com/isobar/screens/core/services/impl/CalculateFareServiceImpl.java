package au.com.isobar.screens.core.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
    String cost = null;
    String kidPassengerAge;
    double discountedCosts = 0;

    String clubValue = FlightConstants.BOOKING_PASSENGER_CLUB_LEVEL;
    int numberOfAdultPassengers = FlightConstants.NUMBER_OF_ADULT_PASSENGER;
    int numberOfKidsPassengers;
    int kidsAge;
    double totalCost;
    double adultsCost;

    Gson gson = new Gson();

    @Override
    public JsonObject getFareSydToMelb() {

        final String jsonData = FlightConstants.DEPARTURE_JSON_STR;
        JsonObject jdata = null;
        
        try {
            jdata = gson.fromJson(jsonData, JsonObject.class);
            if (!jdata.isJsonNull()) {
                final JsonArray jarray = jdata.getAsJsonArray("Departure");
                for(int i = 0; i < jarray.size(); i++) {
                    cost = jarray.get(i).getAsJsonObject().get("Cost").getAsString();
                    final int intCost = Integer.parseInt(cost);
                     adultsCost = numberOfAdultPassengers * intCost;
                    final JsonObject kidsData = gson.fromJson(FlightConstants.KIDSDETAILS, JsonObject.class);
                    final JsonArray kidsDataArray = kidsData.getAsJsonArray("kids-details");
                    for(int kidsIndex = 0; kidsIndex < kidsDataArray.size(); kidsIndex++){
                    kidsAge = kidsDataArray.get(kidsIndex).getAsJsonObject().get("age").getAsInt();

                    final double kidsDiscountedCost = CostCalculatorUtils.getcost(clubValue,kidsAge,intCost);
                    discountedCosts= discountedCosts+kidsDiscountedCost;
                    }

                    totalCost = totalCost+discountedCosts;
                    LOG.info("Cost after the clubs discounts being applied",totalCost);

                    jarray.add(new JsonPrimitive(totalCost));
                    jdata.add("discounted costs",jarray);
                    LOG.info("Final Array", jarray.toString());

                }
            }
        }catch (final Exception e){
            e.printStackTrace();
        }

        return jdata;
    }

    @Override
    public JsonObject getfareMelbToSyd() {

        final String jsonData = FlightConstants.ARRIVAL_JSON_STR;
        JsonObject jDataArrival = null;

        try {
            jDataArrival = gson.fromJson(jsonData, JsonObject.class);
            if (!jDataArrival.isJsonNull()) {
                final JsonArray jarray = jDataArrival.getAsJsonArray("Arrival");
                for (int j = 0; j < jarray.size(); j++) {
                    cost = jarray.get(j).getAsJsonObject().get("Cost").getAsString();
                    final int intCost = Integer.parseInt(cost);
                    adultsCost = numberOfAdultPassengers * intCost;
                    final JsonObject kidsData = gson.fromJson(FlightConstants.KIDSDETAILS, JsonObject.class);
                    final JsonArray kidsDataArray = kidsData.getAsJsonArray("kids-details");
                    for(int kidsIndex = 0; kidsIndex < kidsDataArray.size(); kidsIndex++){
                    kidsAge = kidsDataArray.get(kidsIndex).getAsJsonObject().get("age").getAsInt();

                    final double kidsDiscountedCost = CostCalculatorUtils.getcost(clubValue,kidsAge,intCost);
                    discountedCosts= discountedCosts+kidsDiscountedCost;
                    }

                    totalCost = totalCost+discountedCosts;
                    LOG.info("Cost after the clubs discounts being applied",totalCost);

                    jarray.add(new JsonPrimitive(totalCost));
                    jDataArrival.add("discounted costs",jarray);
                }

            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return jDataArrival;
    }
    
}