package tech.bailey.rod.scenario1.event;

import android.graphics.Color;
import android.support.annotation.NonNull;

import tech.bailey.rod.scenario1.NamedColor;

/**
 * Event that is posted by an IScenario1Model whenever it's "fillColor" property
 * changes value. This lets subscribers (probably instances of IScenario1Presenter) to
 * communicate this change to their associated IScenario1View's.
 */
public class FillColorPropertyChangedEvent {

    private final NamedColor fillColor;

    public FillColorPropertyChangedEvent(@NonNull NamedColor newFillColor) {
        this.fillColor = newFillColor;
    }

    public NamedColor getFillColor() {
        return fillColor;
    }
}
