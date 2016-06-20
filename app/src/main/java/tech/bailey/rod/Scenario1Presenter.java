package tech.bailey.rod;

import android.support.annotation.NonNull;
import android.util.Log;


public class Scenario1Presenter implements IScenario1Presenter {

    private static final String TAG = Scenario1Presenter.class.getSimpleName();

    private final IScenario1View scenario1View;
    private final IScenario1Model scenario1Model;

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
        Log.i(TAG, "swtachPressed: " + swatchColor);

        // Transmit new swatch selection to model
        scenario1Model.setSwatchColor(swatchColor);

        // Alert view that model contents have changed
        String itemName = scenario1Model.getSwatchColor().getName();
        Log.i(TAG, "itemName: " + itemName);
        scenario1View.setItemText(itemName);
    }
}
