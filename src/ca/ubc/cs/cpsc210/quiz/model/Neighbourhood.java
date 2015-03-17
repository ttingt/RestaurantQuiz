package ca.ubc.cs.cpsc210.quiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ting Ting Tai on 2014-10-25.
 * Represents a neighbourhood having a name in a city
 */
public class Neighbourhood implements GeoArea {

    private String neighborhoodName;
    private City city;

    public Neighbourhood(String neighborhoodName, City city) {
        this.neighborhoodName = neighborhoodName;
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public String getCityString() {
        return city.getCityString();
    }

    public List<String> getAllGeoStrings() {
        String[] neighbourhoodNameSplit = neighborhoodName.split(" ");
        List<String> allGeoStrings = new ArrayList<String>();

        for (int i=0; i<neighbourhoodNameSplit.length; i++) {
            String curGeoString = "";
            for (int k=0; k<=i; k++) {
                if (curGeoString.equals("")) {
                    curGeoString = neighbourhoodNameSplit[k];
                } else {
                    curGeoString = curGeoString + " " + neighbourhoodNameSplit[k];
                }
            }
            curGeoString = curGeoString + ", " + getCityString();
            allGeoStrings.add(curGeoString);
        }
        return allGeoStrings;
    }

    /**
     * Two neighbourhoods are equal if they have the same city
     * When o is a City, this neighbourhood is equal to it, if it has the same city.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        // will have to revisit this if we add subtypes of GeoArea other than City and Neighborhood
        if (o == null || !(o instanceof GeoArea)) return false;

        // when o is a City, this Neighborhood is equal to o if the cities are equal
        if (o instanceof City)
            return city.equals(o);

        // must be a Neighborhood
        Neighbourhood other = (Neighbourhood) o;

        return (city.equals(other.city));
    }

    @Override
    public int hashCode() {
        return city != null ? city.hashCode() : 0;
    }
}
