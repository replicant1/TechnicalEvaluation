package tech.bailey.rod.scenario2;

/**
 * The known modes of transport that are used to measure the travel time FromCentral.
 *
 * @see tech.bailey.rod.json.FromCentral
 */
public enum ModeOfTransport {
    CAR("car"), //
    TRAIN("train");

    private String id;


    ModeOfTransport(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }
}
