package tech.bailey.rod.scenario1;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Basic presenter impl for Scenario 1 tab. Note that this presenter does the model's work for it.
 * Instead of simply notifying the model of the state change and letting the model fire events which
 * eventually result in view updates, this presenter makes the view updates itself, knowing that the
 * IScenario1Model it is associated with doesn't do event firing.</p>
 * I did this to save time and also to illustrate a shortcut that is possible when asynchrony is
 * not involved in the update cycle.
 */
public class Scenario1Presenter implements IScenario1Presenter {

    private static final String TAG = Scenario1Presenter.class.getSimpleName();

    private final IScenario1View scenario1View;

    private final IScenario1Model scenario1Model;

    /**
     * Constructs a Scenario1Presenter that coordinates the given model and view
     */
    public Scenario1Presenter(@NonNull IScenario1View view, @NonNull IScenario1Model model) {
        this.scenario1View = view;
        this.scenario1Model = model;
    }

    @Override
    public void fillColorButtonPressed(@NonNull NamedColor buttonColor) {
        // Transmit new fill color selection to model
        scenario1Model.setFillColor(buttonColor);

        // Alert view that model contents have changed
        NamedColor fillColor = scenario1Model.getFillColor();
        scenario1View.setFillColor(fillColor.getColorString());
    }

    @Override
    public void swatchPressed(@NonNull NamedColor swatchColor) {
        // Transmit new swatch selection to model
        scenario1Model.setSwatchColor(swatchColor);

        // Alert view that model contents have changed
        String itemName = scenario1Model.getSwatchColor().getName();
        scenario1View.setItemText(itemName);
    }
}
