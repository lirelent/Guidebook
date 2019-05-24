package gigaherz.lirelent.guidebook.guidebook.client.background;

import gigaherz.lirelent.guidebook.guidebook.client.GuiGuidebook;
import net.minecraft.util.ResourceLocation;

/**
 * class factory for {@link IBookBackground}s. Register implementations of this by adding them to
 * {@link gigaherz.guidebook.guidebook.client.BookRendering#BACKGROUND_FACTORY_MAP}
 */
public interface IBookBackgroundFactory
{
    /**
     * Ask the class factory to create the appropriate IBookBackground that makes use of the provided
     *
     * {@link GuiGuidebook} and {@link ResourceLocation} to draw the open book background
     * @param gui {@link GuiGuidebook} to draw behind
     * @param backgroundLocation {@link ResourceLocation} of the resource for the background
     * @return {@link IBookBackground} implementation
     */
    IBookBackground create(GuiGuidebook gui, ResourceLocation backgroundLocation);
}
