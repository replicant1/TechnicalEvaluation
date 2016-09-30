package tech.bailey.rod.scenario2;

/**
 * Created by rodbailey on 30/09/2016.
 */

public enum Destination {

    BLUE_MOUNTAINS("Blue Mountains"), //
    TARONGA_ZOO("taronga zoo"), //
    BONDI_BEACH("Bondi Beach"); //

    private final String jsonName;

    Destination(String str) {
        jsonName = str;
    }

    public String getJsonName() {
        return jsonName;
    }
}
