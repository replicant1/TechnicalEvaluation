package tech.bailey.rod.scenario1.event;

import android.support.annotation.NonNull;

import tech.bailey.rod.scenario1.NamedColor;

/**
 * Event that is posted by an IScenario1Model whenever it's "swatchColor" property
 * changes value. This lets subscribers (probably instances of IScenario1Presenter) to
 * communicate this change to their associated IScenario1View's.
 */
public class SwatchColorPropertyChangedEvent {

    private final NamedColor swatchColor;

    public SwatchColorPropertyChangedEvent(@NonNull NamedColor newSwatchColor) {
        this.swatchColor = newSwatchColor;
    }

    public NamedColor getSwatchColor() {
        return swatchColor;
    }
}
