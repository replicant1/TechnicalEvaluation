package tech.bailey.rod.scenario1;

/**
 * These color names/hex codes are taken from the standard X11 color set.
 */
public enum NamedColor {
    MEDIUM_SEA_GREEN("Medium Sea Green", "#3CB371"), //
    DEEP_SKY_BLUE("Deep Sky Blue", "#00BFFF"), //
    LIGHT_GOLDENROD_YELLOW("Light Goldenrod Yellow", "#FAFAD2"), //
    MEDIUM_ORCHID("Medium Orchid", "#BA55D3"), //
    DODGER_BLUE("Dodger Blue", "#1E90FF"), //
    RED("Red", "#FF0000"), //
    GREEN("Green", "#00FF00"), //
    BLUE("Blue", "#0000FF");

    private String name;

    private String colorString;

    NamedColor(String name, String colorString) {
        this.name = name;
        this.colorString = colorString;
    }

    public String getName() {
        return name;
    }

    public String getColorString() {
        return colorString;
    }
}
