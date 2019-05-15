package gigaherz.guidebook.guidebook.client;

public interface IBookBackground
{
    /**
     * Indicate that the user has decided to close the book
     */
    void startClosing();

    /**
     * @return true iff the background is fully open and therefor we should start rendering the page contents
     */
    boolean isFullyOpen();

    /**
     * Indicate to the {@link IBookBackground} to move to it's next tick of animation if applicable
     *
     * @return if true iff the book background is "in motion"
     */
    boolean update();

    /**
     * GuideBook version of {@link net.minecraft.client.gui.GuiScreen#drawScreen(int, int, float)} for this book
     * background
     *
     * @param partialTicks ticks since last draw
     * @param bookHeight the height of the book background in pixels
     * @param backgroundScale how much the book background should be scaled
     */
    void draw(float partialTicks, int bookHeight, float backgroundScale);

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
