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
}
