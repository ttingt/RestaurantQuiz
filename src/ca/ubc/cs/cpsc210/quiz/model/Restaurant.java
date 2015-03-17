package ca.ubc.cs.cpsc210.quiz.model;

import java.util.List;

/**
 * Represents a restaurant having a street address, geographical area, postal code, name, id categories
 * and list of reviews.
 * Created by Ting Ting Tai on 2014-10-25.
 */
public class Restaurant {

    private String name;
    private List<Category> categories;
    private String id;
    private String address;
    private String postalCode;
    private GeoArea geographicalArea;

    public Restaurant(String name, List<Category> categories,
                      String id, String address, String postalCode,
                      GeoArea geographicalArea) {
        this.name = name;
        this.categories = categories;
        this.id = id;
        this.address = address;
        this.postalCode = postalCode;
        this.geographicalArea = geographicalArea;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getId() {
        return id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public GeoArea getGeographicalArea() {
        return geographicalArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Restaurant)) return false;

        Restaurant that = (Restaurant) o;

        if (!address.equals(that.address)) return false;
        if (!categories.equals(that.categories)) return false;
        if (!geographicalArea.equals(that.geographicalArea)) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!postalCode.equals(that.postalCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + categories.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + postalCode.hashCode();
        result = 31 * result + geographicalArea.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String restString = name + ", " +
                geographicalArea.getAllGeoStrings().get(0);
        if (postalCode != null) {
            restString = restString + ", " + postalCode;
        }
        return restString;
    }



}