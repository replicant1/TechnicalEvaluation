package tech.bailey.rod.scenario1;

import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.EventLog;
import android.util.Log;

import com.squareup.otto.Produce;

import tech.bailey.rod.bus.EventBusSingleton;
import tech.bailey.rod.scenario1.event.FillColorPropertyChangedEvent;
import tech.bailey.rod.scenario1.event.PageNumberPropertyChangedEvent;
import tech.bailey.rod.scenario1.event.SwatchColorPropertyChangedEvent;

/**
 * Contains all the view state info about the Scenario 1 tab. This model broadcasts the following
 * events:
 * <ul>
 *     <li>FillColorPropertyChangedEvent</li>
 *     <li>SwatchColorPropertyChangedEvent</li>
 *     <li>PageNumberPropertyChangedEvent</li>
 * </ul>
 */
public class Scenario1Model implements IScenario1Model {

    private static final String TAG = Scenario1Model.class.getSimpleName();

    private NamedColor fillColor = NamedColor.NULL_COLOR;

    private int pageNumber = -1;

    private NamedColor swatchColor = NamedColor.NULL_COLOR;

    public Scenario1Model() {
        EventBusSingleton.getInstance().getBus().register(this);
    }

    @Nullable
    @Override
    public NamedColor getFillColor() {
        return fillColor;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Nullable
    @Override
    public NamedColor getSwatchColor() {
        return swatchColor;
    }

    @Produce
    public FillColorPropertyChangedEvent produceFillColorPropertyChangedEvent() {
        Log.i(TAG, "Producing FillColorPropertyChangeEvent based on fillColor of " + fillColor);
        return new FillColorPropertyChangedEvent(fillColor);
    }

    @Produce
    public PageNumberPropertyChangedEvent producePageNumberPropertyChangedEvent() {
        Log.i(TAG, "Producing PageNumberPropertyChangedEvent with pageNumber of " + pageNumber);
        return new PageNumberPropertyChangedEvent(pageNumber);
    }

    @Produce
    public SwatchColorPropertyChangedEvent produceSwatchColorPropertyChangedEvent() {
        Log.i(TAG, "Producing SwatchColorPropertyChangedEvent with swatchColor " + swatchColor);
        return new SwatchColorPropertyChangedEvent(swatchColor);
    }

    @Override
    public void setFillColor(@Nullable NamedColor fillColor) {
        this.fillColor = fillColor;
        EventBusSingleton.getInstance().getBus().post(new FillColorPropertyChangedEvent(fillColor));
    }

    @Override
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        EventBusSingleton.getInstance().getBus().post(new PageNumberPropertyChangedEvent(pageNumber));
    }

    @Override
    public void setSwatchColor(@Nullable NamedColor swatchColor) {
        Log.i(TAG, "Into setSwatchColor with swwatchColor=" + swatchColor);
        this.swatchColor = swatchColor;
        EventBusSingleton.getInstance().getBus().post(new SwatchColorPropertyChangedEvent(swatchColor));
    }
}
