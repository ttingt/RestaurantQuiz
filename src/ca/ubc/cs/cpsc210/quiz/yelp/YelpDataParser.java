package ca.ubc.cs.cpsc210.quiz.yelp;

import ca.ubc.cs.cpsc210.quiz.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ting Ting Tai on 2014-11-01.
 * Parser for JSON data returned by Yelp.
 */
public class YelpDataParser {

    public static List<Restaurant> parseRestaurantData(String response, String yelpCityFilter)
            throws JSONException {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        JSONObject jOResponse = new JSONObject(response);
        JSONArray totalRestaurants = jOResponse.getJSONArray("businesses");
        int size = totalRestaurants.length();

        for (int i=0; i<size; i++) {
            JSONObject curRestaurant = totalRestaurants.getJSONObject(i);
            JSONObject curLocation = curRestaurant.getJSONObject("location");

            if (curLocation.getString("city").equals(yelpCityFilter)) {

                if (!curRestaurant.getBoolean("is_closed")) {
                    // Name
                    String name = curRestaurant.getString("name");
                    // Categories
                    JSONArray curCategories = curRestaurant.getJSONArray("categories");
                    List<Category> categories = new ArrayList<Category>();
                    for (int k = 0; k < curCategories.length(); k++) {
                        JSONArray curCategory = curCategories.getJSONArray(k);
                        String categoryName = curCategory.getString(0);
                        String categoryAlias = curCategory.getString(1);
                        categories.add(new Category(categoryName, categoryAlias));
                    }
                    // Id
                    String id = curRestaurant.getString("id");
                    // Address
                    String address = curLocation.getJSONArray("address").getString(0);
                    // Postal code
                    String postalCode;
                    try {
                        postalCode = curLocation.getString("postal_code");
                    } catch (JSONException e) {
                        postalCode = null;
                    }
                    // Geographical area
                    GeoArea geographicalArea;
                    JSONArray curNbhd;
                    City curCity = new City(curLocation.getString("city"), curLocation.getString("state_code"),
                            curLocation.getString("country_code"));
                    try {
                        curNbhd = curLocation.getJSONArray("neighborhoods");
                        if (curNbhd.length() != 0) {
                            String nName = curNbhd.getString(0);
                            geographicalArea = new Neighbourhood(nName, curCity);
                        } else {
                            geographicalArea = curCity;
                        }
                    } catch (JSONException e) {
                        geographicalArea = curCity;
                    }

                    // Add to parsed list
                    restaurants.add(new Restaurant(name,categories,id,address,postalCode,geographicalArea));

                }
            }
        }

        return restaurants;
    }

}
