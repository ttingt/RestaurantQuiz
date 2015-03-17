package ca.ubc.cs.cpsc210.quiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a leg that has an arbitrary number of points and a distance.  Part of a route.
 * Created by Ting Ting Tai on 2014-10-22.
 */
public class Leg {

    private List<LatLng> latLngs;
    private int distance;

    public Leg() {
        latLngs = new ArrayList<LatLng>();
        distance = 0;
    }

    public void addPoint(LatLng pt) {
        latLngs.add(pt);
    }

    public void addAllPoints(List<LatLng> pts) {
        for (LatLng pt: pts) {
            latLngs.add(pt);
        }
    }

    public List<LatLng> getPoints() {
        return latLngs;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

}
