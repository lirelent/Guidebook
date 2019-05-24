package gigaherz.guidebook.guidebook.client.background;

import gigaherz.guidebook.guidebook.client.GuiGuidebook;
import gigaherz.guidebook.guidebook.elements.ElementImage;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class StaticImage1PageBackground extends StaticImage2PageBackground
{
    public StaticImage1PageBackground(GuiGuidebook gui, ElementImage image)
    {
        super(gui, image);
    }

    @Override
    public Layout getLayout()
    {
        return Layout.ONE_PAGE;
    }

    @Override
    public int getInnerMargin()
    {
        return 20;
    }

    /**
     * {@link IBookBackgroundFactory} for demo "gbook:gui/single_side_2d_background" background registered to
     * {@link gigaherz.guidebook.guidebook.client.BookRendering#BACKGROUND_FACTORY_MAP} during
     * {@link gigaherz.guidebook.GuidebookMod#preInit(FMLPreInitializationEvent)}
     */
    public static IBookBackgroundFactory oneSide2dBackgroundFactory = (gui, backgroundLocation) -> {
        ElementImage elementImage = new ElementImage(false, false);

        elementImage.textureLocation = backgroundLocation;
        elementImage.tx = 0;
        elementImage.ty = 0;
        elementImage.tw = 122;
        elementImage.th = 127;
        elementImage.w = 128;
        elementImage.h = 128;
        elementImage.scale = 1f;

        return new StaticImage1PageBackground(gui, elementImage);
    };
}
