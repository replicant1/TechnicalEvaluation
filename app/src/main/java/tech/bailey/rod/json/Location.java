package tech.bailey.rod.json;

/**
 * POJO equivalent of value of the the "location" property of a Destination
 */
public class Location {
    public float latitude;

    public float longitude;

    @Override
    public String toString() {
        return String.format("latitude=%.6f,longitude=%.6f", latitude, longitude);
    }
}
