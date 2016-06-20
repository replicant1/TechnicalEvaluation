package tech.bailey.rod.json;

/**
 * POJO equivalent of an element of the JSON array returned by the Optus server.
 * The JSON file retrieved by the server is an anonymous array of Destination JSON objects.
 */
public class Destination {
    public int id;

    public String name;

    public FromCentral fromcentral;

    public Location location;

    @Override
    public String toString() {
        return String.format("id=%d,name=\"%s\",fromcentral=[%s],location=[%s]", id, name, fromcentral, location);
    }
}
