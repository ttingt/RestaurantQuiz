package ca.ubc.cs.cpsc210.quiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a route having a list of legs and a distance.
 * Created by Ting Ting Tai on 2014-10-22.
 */
public class Route {

    private List<Leg> legs;
    private int distance;

    public Route() {
        legs = new ArrayList<Leg>();
        distance = 0;
    }

    public void addLeg(Leg leg) {
        legs.add(leg);
        distance += leg.getDistance();
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public int getDistance() {
        return distance;
    }

}
