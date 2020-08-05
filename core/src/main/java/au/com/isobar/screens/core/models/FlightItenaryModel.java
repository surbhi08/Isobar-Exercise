package au.com.isobar.screens.core.models;



import javax.annotation.PostConstruct;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.isobar.screens.core.dto.FlightArrivalObjectDTO;
import au.com.isobar.screens.core.dto.FlightDepartureObjectDTO;
import au.com.isobar.screens.core.services.CalculateFareService;

@Model(adaptables = { SlingHttpServletRequest.class,
        Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FlightItenaryModel {

    /*
    * This separates the application into three main classes that interact with each other to coordinate the entire application.
    * */

    private static final Logger LOG = LoggerFactory.getLogger(FlightItenaryModel.class);

    @Reference
    CalculateFareService calculateFareService;

    @PostConstruct
    private void init() {

        if (calculateFareService.getfareMelbToSyd() != null && calculateFareService.getfareMelbToSyd()!=null) {
            JsonObject FlightDepartureObject = calculateFareService.getFareSydToMelb();
            FlightDepartureObjectDTO flightDepartureObjectDTO = new Gson().fromJson(FlightDepartureObject, FlightDepartureObjectDTO.class);

            JsonObject FlightReturnObject = calculateFareService.getfareMelbToSyd();
            FlightArrivalObjectDTO flightArrivalObjectDTO = new Gson().fromJson(FlightReturnObject, FlightArrivalObjectDTO.class);


            // all the details of the flight can be retrieved through the DTO objects

            flightDepartureObjectDTO.getDepart();
            LOG.info("list of values for the departure", flightDepartureObjectDTO.getDepart());
            flightArrivalObjectDTO.getArrive();
            LOG.info("list of values for the Arrival", flightArrivalObjectDTO.getArrive());
            //from here we can take it to frontend level in whatever fashion we need to display the data.
        }
    }

}
