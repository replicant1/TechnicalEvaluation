package tech.bailey.rod.scenario1;

/**
 * A page in the PageViewer of IScenario1View.
 * NOTE Page numbers are one-based.
 */
public class NumberedPage {

    private int pageNumber;

    /**
     * @param pageNumber The one-based number of this page. Note that indexes in ViewPagers are
     *                   zero-based.
     */
    public NumberedPage(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
