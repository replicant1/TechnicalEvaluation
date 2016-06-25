package tech.bailey.rod.scenario1;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.otto.Subscribe;

import tech.bailey.rod.bus.EventBusSingleton;
import tech.bailey.rod.scenario1.event.FillColorPropertyChangedEvent;
import tech.bailey.rod.scenario1.event.PageNumberPropertyChangedEvent;
import tech.bailey.rod.scenario1.event.SwatchColorPropertyChangedEvent;

/**
 * Basic presenter impl for Scenario 1 tab.</p>
 * This presenter subscribes to the following events:
 * <ul>
 *     <li>FillColorPropertyChangedEvent</li>
 *     <li>PageNumberPropertyChangedEvent</li>
 *     <li>SwatchColorPropertyChangedEvent</li>
 * </ul>
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
        EventBusSingleton.getInstance().getBus().register(this);
    }

    @Override
    public void fillColorButtonPressed(@NonNull NamedColor buttonColor) {
        // Transmit new fill color selection to model
        scenario1Model.setFillColor(buttonColor);
    }

    @Override
    public void pageSelected(int pageNumber) {
        // Transmit new page selection to mode
        scenario1Model.setPageNumber(pageNumber);
    }

    @Override
    public void swatchPressed(@NonNull NamedColor swatchColor) {
        Log.i(TAG, "Into swatchPressed with swatchColor=" + swatchColor);
        // Transmit new swatch selection to model
        scenario1Model.setSwatchColor(swatchColor);
    }

    @Subscribe
    public void onBusEvent(FillColorPropertyChangedEvent event) {
        // Alert view that model contents have changed
        NamedColor fillColor = event.getFillColor();
        Log.i(TAG, "event.getFillColor=" + event.getFillColor());

        if (fillColor != NamedColor.NULL_COLOR) {
            scenario1View.setFillColor(fillColor.getColorString());
        }
    }

    @Subscribe
    public void onBusEvent(PageNumberPropertyChangedEvent event) {
        Log.i(TAG, "Into onBusEvent(PageNumberPropertyChangedEvent) with event.pageNumber=" +
                event.getPageNumber());

        // Alert view that model contents have changed
        int newPageNumber = event.getPageNumber();

        if (newPageNumber != -1) {
            scenario1View.setPageNumber(newPageNumber);
        }
    }

    @Subscribe
    public void onBusEvent(SwatchColorPropertyChangedEvent event) {
        Log.i(TAG, "Into onBusEvent(SwatchColorPropertyChangedEvent) with swatchColor=" + event.getSwatchColor());
        // Alert view that model contents have changed
        NamedColor swatchColor = event.getSwatchColor();

        if (swatchColor != NamedColor.NULL_COLOR) {
            scenario1View.setSwatchColor(swatchColor.getColorString());
        }
    }
}
