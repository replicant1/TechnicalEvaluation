package tech.bailey.rod.json;

/**
 * POJO equivalent of value of the the "fromcentral" property of a Destination JSON object
 */
public class FromCentral {
    public String car;

    public String train;

    @Override
    public String toString() {
        return String.format("car=\"%s\",train=\"%s\"", car, train);
    }
}
