package au.com.isobar.screens.core.services;

import com.google.gson.JsonObject;

/**
 * The Interface LoyaltyService.
 */
public interface CalculateFareService {

	
	/**
	 * This method is used to get the fare value for Sydney -> Melbourne.
	 */
	public JsonObject getFareSydToMelb();
	

	/**
	 * This method is used to get fare value for Mebourne -> Sydney .
	 */
	public JsonObject getfareMelbToSyd();
}
