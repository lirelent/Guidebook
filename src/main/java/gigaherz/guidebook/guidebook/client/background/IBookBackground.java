package gigaherz.guidebook.guidebook.client.background;

public interface IBookBackground
{
    /**
     * Layout of the pages on the background
     */
    enum Layout {
        /** Render two horizontally side by side pages on this background */
        TWO_PAGES,
        /** Only render a single page on this background */
        ONE_PAGE
    }

    /**
     * GuideBook version of {@link net.minecraft.client.gui.GuiScreen#drawScreen(int, int, float)} for this book
     * background
     *
     * @param partialTicks ticks since last draw
     * @param bookHeight the height of the book background in pixels
     * @param scalingFactor how much the book background should be scaled
     */
    void draw(float partialTicks, int bookHeight, float scalingFactor);

    /**
     * @return the {@link Layout} that the page render should use to render the pages above this background
     */
    Layout getLayout();

    /**
     * Indicate that the user has decided to close the book
     */
    void startClosing();

    /**
     * @return true iff the background is fully open and therefor we should start rendering the page contents
     */
    boolean isFullyOpen();

    /**
     * Update animation by a tick
     *
     * @return true iff book is "open" once we return false, then it's fully closed and can exit from the GUI
     */
    boolean update();

    /**
     * @return width of the book background in pixels
     */
    int getWidth();

    /**
     * @return height of the book background in pixels
     */
    int getHeight();

    /**
     * @return margin between text and binding of the book in pixels
     */
    int getInnerMargin();

    /**
     * @return margin between text and outside edge of the book in pixels
     */
    int getOuterMargin();

    /**
     * @return margin between text and top edge of the book in pixels
     */
    int getTopMargin();

    /**
     * @return margin between text and bottom edge of the book in pixels
     */
    int getBottomMargin();

    int getBookScaleMargin();
}
