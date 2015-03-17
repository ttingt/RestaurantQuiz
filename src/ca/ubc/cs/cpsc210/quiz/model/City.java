package ca.ubc.cs.cpsc210.quiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ting Ting Tai on 2014-10-25.
 * Represents a name having a name, province/state code and country code
 */
public class City implements GeoArea {

    private String city;
    private String province;
    private String countryCode;

    public City(String cityName, String province, String countryCode)
            throws IllegalArgumentException {
        if (province.length() != 2 || countryCode.length() != 2)
            throw new IllegalArgumentException("Invalid province or country code.");
        this.city = cityName;
        this.province = province;
        this.countryCode = countryCode;
    }

    public String getCityString() {
        return city + ", " + province + ", " + countryCode;
    }

    public List<String> getAllGeoStrings() {
        List<String> allGeoStrings = new ArrayList<String>();
        allGeoStrings.add(getCityString());
        return allGeoStrings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof GeoArea)) return false;

        if (o instanceof City) {
            City city1 = (City) o;

            if (!city.equals(city1.city)) return false;
            if (!countryCode.equals(city1.countryCode)) return false;
            if (!province.equals(city1.province)) return false;

            return true;
        }

        Neighbourhood neighbourhood1 = (Neighbourhood) o;

        return (neighbourhood1.getCity().equals(this));
    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + province.hashCode();
        result = 31 * result + countryCode.hashCode();
        return result;
    }
}
